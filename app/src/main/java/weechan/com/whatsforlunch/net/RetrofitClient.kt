package weechan.com.whatsforlunch.net

import com.mobile.utils.showToast
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

val baseURL = "http://result.eolinker.com/"
val timeout = 10000L

object RetrofitClient {

    private val okhttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(CacheInterceptor())
            .cache(Cache(File(App.app.externalCacheDir, "ok-cache"), 1024 * 1024 * 30L))
            .build()

    private val mockOkClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(MockInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .cache(Cache(File(App.app.externalCacheDir, "ok-cache"), 1024 * 1024 * 30L))
            .build()

    class MockInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            showToast(NetStatus.available)

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

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val resp: Response
            val req: Request
            if (NetStatus.available) {
                req = chain.request().newBuilder()
                        .cacheControl(CacheControl.Builder().maxAge(2, TimeUnit.DAYS).build())
                        .build()
            } else {
                req = chain.request().newBuilder()
                        .cacheControl(CacheControl.Builder().onlyIfCached().build())
                        .build()
            }
            resp = chain.proceed(req)
            return resp.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public,max-age=${60 * 60 * 24 * 2}")
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
        if(yes){
            return retrofit.create(clazz)
        }else{
            return retrofit2.create(clazz)
        }

    }

    var yes = true

    fun switch(){
        yes = !yes
    }


}