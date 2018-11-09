package weechan.com.common.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Created by jimji on 2017/9/15.
 */
object O {
    val mainThreadHandler: Handler by lazy { Handler(Looper.getMainLooper()) }//主线程
    var mToast: Toast? = null
    var mMsg: Any? = null
}

fun inUiThread(run: () -> Unit) = O.mainThreadHandler.post(run)

fun showToast(msg : String) {

    inUiThread {
        if (null == O.mToast || msg !== O.mMsg!!.toString()) {
            if (null == O.mToast) {
                O.mToast = Toast.makeText(Utils.app, msg, Toast.LENGTH_SHORT)
            } else {
                O.mToast!!.setText(msg)
            }
            O.mMsg = msg
        }
        O.mToast?.show()
    }
}

fun showToast(number : Number) {
    val msg = number.toString()
    showToast(msg)
}

fun showToast(bool : Boolean) {
    val msg = bool.toString()
    showToast(msg)
}

fun String.toast() = showToast(this)

