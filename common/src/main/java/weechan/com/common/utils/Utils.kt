package weechan.com.common.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by jimji on 2017/9/15.
 */
class Utils {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var app: Application

        fun init(app: Application) {
            this.app = app
            ActivityManager.init()
        }
    }
}