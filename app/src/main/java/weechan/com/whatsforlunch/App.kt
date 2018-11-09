package weechan.com.whatsforlunch

import android.app.Application
import androidx.lifecycle.Lifecycle
import com.squareup.leakcanary.LeakCanary
import weechan.com.common.base.BaseView
import weechan.com.common.utils.ActivityManager
import weechan.com.common.utils.Utils
import weechan.com.common.utils.net.NetStatusMonitor
import weechan.com.common.utils.showToast
import weechan.com.whatsforlunch.config.configApp
import weechan.com.whatsforlunch.config.configNetwork
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

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this)
        //setup for project
//        configApp()

    }
}