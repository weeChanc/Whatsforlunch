package weechan.com.whatsforlunch.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
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
            .build()

    private val mockOkClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(MockInterceptor())
            .build()

    class MockInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val request = chain.request()
            val builder = request.newBuilder()
            val oldUrl = request.url()
            val requestUrl: String = "/" + oldUrl.pathSegments().joinToString("/")

            val newUrl = oldUrl.newBuilder()
                    .scheme(oldUrl.scheme())
                    .host("result.eolinker.com")
                    .apply {
                        for (i in 0 until oldUrl.pathSegments().size) {
                            removePathSegment(i)
                        }
                    }
                    .addPathSegments("xI7UEir31bb0ba7787da922392d7802652dc937dfbff047")
                    .port(oldUrl.port()).addQueryParameter("uri", requestUrl).build()
            return chain.proceed(builder.url(newUrl).build())
        }

    }


    val retrofit = Retrofit.Builder().baseUrl(baseURL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(mockOkClient)
            .build()

    fun <T> create(clazz: Class<T>) = retrofit.create(clazz)


}