package weechan.com.common.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import kotlin.reflect.KClass

/**
 * Created by jimji on 2017/9/16.
 */
object ActivityManager {


    private var time: Long = 0
    private val actList = mutableListOf<Activity>()

    fun init() {
        Utils.app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity?) {

            }

            override fun onActivityResumed(p0: Activity?) {
            }

            override fun onActivityStarted(p0: Activity?) {

            }

            override fun onActivityDestroyed(p0: Activity) {
                ActivityManager.remove(p0)
            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
            }

            override fun onActivityStopped(p0: Activity?) {
            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                ActivityManager.add(p0)
            }
        })
    }

    @Synchronized
    fun remove(act: Activity) = actList.remove(act)

    @Synchronized
    fun add(act: Activity) = actList.add(act)

    @Synchronized
    fun kill(clazz: KClass<*>) {
        actList.forEach {
            if (it::class == clazz) it.finish()
        }
    }

    @Synchronized
    fun killAll() {
        actList.forEach { it.finish() }
        actList.clear()
    }

    @Synchronized
    fun peek() = actList.get(actList.size - 1)

    fun doubleExit(delay: Long = 2000, title: String = "再按一次退出", action: () -> Unit = {}) {
        if (System.currentTimeMillis() - time > delay) {
            time = System.currentTimeMillis()
            title.toast()
        } else {
            action()
            //杀死当前进程
            for (i in actList.size downTo 0) {
                actList.get(i).finish()
            }
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }

}