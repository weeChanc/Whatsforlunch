package weechan.com.whatsforlunch.config

import androidx.lifecycle.Lifecycle
import weechan.com.common.base.BaseView
import weechan.com.common.utils.ActivityManager
import weechan.com.common.utils.net.NetStatusMonitor
import weechan.com.common.utils.showToast

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/3 11:38
 *
 */

fun configApp(){
    configNetwork()
}
  
fun configNetwork(){
    NetStatusMonitor.setNetStatusListener(object: NetStatusMonitor.Listener {
        var lostTime = 0L
        override fun onLost() {
            lostTime = System.currentTimeMillis()
        }

        override fun onAvailable() {
            with(ActivityManager.peek() as BaseView<*>){
                if(this.lifecycle.currentState == Lifecycle.State.RESUMED){
                    // 获取ForegroundActivity进行刷新
                    // 断线时间超过30秒重连再刷新一次
                    if(System.currentTimeMillis() - lostTime > 1000 * 30){
                        this.presenter.refresh()
                    }
                }
            }
        }

        override fun onNetStateChange(oldState: Int, newState: Int) {
            if(newState == NetStatusMonitor.MOBILE){
                showToast("正在使用移动网络")
            }
        }
    })
}