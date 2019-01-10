package weechan.com.whatsforlunch.net

import android.util.Log
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import weechan.com.common.utils.net.NetStatusMonitor
import weechan.com.whatsforlunch.App
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 19:55
 *
 */

val baseURL = "http://192.168.43.133:3000"
val timeout = 10000L


object RetrofitClient {
    public var token: String? = null
    private var okhttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor {
                if (token != null) {
                    val req = it.request();
                    return@addInterceptor it.proceed(req.newBuilder().addHeader("Authorization", token).build())
                } else {
                    return@addInterceptor it.proceed(it.request())
                }
            }
            .addInterceptor(CacheInterceptor())
            .addNetworkInterceptor(CacheNetworkInterceptor())
            .cache(Cache(File(App.app.externalCacheDir, "ok-cache"), 1024 * 1024 * 30L))
            .build()

    private val mockOkClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(MockInterceptor())
            .addNetworkInterceptor(CacheNetworkInterceptor())
            .cache(Cache(File(App.app.externalCacheDir, "ok-cache"), 1024 * 1024 * 30L))
            .build()

    class MockInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request()
            val builder = request.newBuilder()
            val oldUrl = request.url()
            val requestUrl: String = "/" + oldUrl.pathSegments().joinToString("/")

            val newUrl = HttpUrl.Builder().scheme(oldUrl.scheme())
                    .host("result.eolinker.com")
                    .addPathSegments("xI7UEir31bb0ba7787da922392d7802652dc937dfbff047")
                    .port(oldUrl.port()).addQueryParameter("uri", requestUrl).build()
            return chain.proceed(builder.url(newUrl).build())
        }

    }

    class CacheNetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            //无缓存,进行缓存
            return chain.proceed(chain.request()).newBuilder()
                    .removeHeader("Pragma")
                    //对请求进行最大60秒的缓存
                    .addHeader("Cache-Control", "max-age=60")
                    .build();
        }

    }

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val resp: Response
            val req: Request
            if (NetStatusMonitor.available) {
                //有网络,检查 10 秒内的缓存
                req = chain.request().newBuilder()
                        .cacheControl(CacheControl.Builder().maxAge(10, TimeUnit.SECONDS).build())
                        .build()
            } else {
                //无网络,检查30天内的缓存
                req = chain.request().newBuilder()
                        .cacheControl(CacheControl.Builder()
                                .onlyIfCached()
                                .maxStale(30, TimeUnit.DAYS)
                                .build())
                        .build()
            }
            resp = chain.proceed(req)
            return resp.newBuilder()
                    .removeHeader("Pragma")
                    .build()
        }

    }


    var retrofit = Retrofit.Builder().baseUrl(baseURL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mockOkClient)
            .build()

    var retrofit2 = Retrofit.Builder().baseUrl(baseURL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient)
            .build()

    fun <T> create(clazz: Class<T>): T {
        return retrofit2.create(clazz)
//        if (yes) {
//            return retrofit.create(clazz)
//        } else {
//            return retrofit2.create(clazz)
//        }

    }

    var yes = true

    fun switch() {
        yes = !yes
    }


}