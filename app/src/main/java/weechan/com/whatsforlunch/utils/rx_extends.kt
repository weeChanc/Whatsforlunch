package weechan.com.whatsforlunch.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import weechan.com.whatsforlunch.App

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 21:04
 *
 */

fun <T> Observable<T>.fetchIO(onNext: (T) -> Unit = {},
                              onError: (Throwable) -> Unit = {},
                              onComplete: () -> Unit = {}) =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete)

fun <T> Observable<T>.fetchNew(onNext: (T) -> Unit = {},
                               onError: (Throwable) -> Unit = {},
                               onComplete: () -> Unit = {}) =
        subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete)


fun <T> Observable<T>.fetchMain(onNext: (T) -> Unit = {},
                                onError: (Throwable) -> Unit = {},
                                onComplete: () -> Unit = {}) =
        subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete)

fun <T> Observable<T>.fetchComputation(onNext: (T) -> Unit = {},
                                       onError: (Throwable) -> Unit = {},
                                       onComplete: () -> Unit = {}) =
        subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(onNext, onError, onComplete)

fun <T> Observable<T>.fetch(block: (param: T?) -> Unit) {
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            object : Observer<T> {
                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Toast.makeText(App.app, e?.message ?: "unknown", Toast.LENGTH_SHORT).show()
                }

                override fun onNext(t: T) {
                    block(t)
                }

                override fun onCompleted() {

                }
            }
    )
}

