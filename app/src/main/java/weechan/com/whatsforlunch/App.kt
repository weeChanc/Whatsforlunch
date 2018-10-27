package weechan.com.whatsforlunch

import android.app.Application

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
    }
}