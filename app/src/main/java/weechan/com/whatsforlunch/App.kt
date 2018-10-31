package weechan.com.whatsforlunch

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.mobile.utils.Utils
import org.jetbrains.anko.toast
import java.io.File

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @onCreate 2018/10/21 16:48
 *
 */

class App : Application() {

    companion object {
        lateinit var app : App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Utils.init(this)
        File(app.externalCacheDir,"ok-cache").mkdir()
    }
}