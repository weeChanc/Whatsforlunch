package weechan.com.common.base

import android.app.Activity
import kotlin.reflect.KClass

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/3 10:15
 *
 */

object ActivityManager {

    private var time: Long = 0

    private val actList = mutableListOf<Activity>()

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

    /**
     * action为在结束应用前要做的事
     */
    fun doubleExit(delay: Long = 2000, title: String = "再按一次退出", action: () -> Unit = {}) {
        if (System.currentTimeMillis() - time > delay) {
            time = System.currentTimeMillis()
        } else {
            action()
            //杀死当前进程
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }

}