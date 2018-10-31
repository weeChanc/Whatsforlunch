package weechan.com.whatsforlunch.net

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.mobile.utils.showToast
import weechan.com.whatsforlunch.App
import kotlin.properties.Delegates


/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/30 11:49
 *
 */

object NetStatus{

    val WIFI = 1;
    val MOBILE = 2;
    val WIFI_MOBILE = 3;
    val UNKNOW = 0

    var available = false
    var netState : Int by Delegates.observable(UNKNOW){
        property, oldValue, newValue ->
        onNetStateChange?.invoke(oldValue,newValue)
    }

    var onAvailable: (()->Unit)? = null
    var onLost: (()->Unit)? = null
    var onNetStateChange: ((oldState:Int,newState:Int)->Unit)? = null

    init {
        val cm =  App.app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        fun setType(){
            val activeNetwork = cm.activeNetworkInfo
            val isMobile = activeNetwork.type == ConnectivityManager.TYPE_MOBILE
            val isWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable
            if (isWifi && isMobile)
                netState = WIFI_MOBILE
            else if (isWifi && !isMobile)
                netState = WIFI
            else if (isMobile && !isWifi)
                netState = MOBILE
            else
                netState = UNKNOW
        }

        cm.requestNetwork( NetworkRequest.Builder().build(),  object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network?) {
                available = true
                setType()
                onAvailable?.invoke()
            }

            override fun onLost(network: Network?) {
                available = false
                onLost?.invoke()
            }
        })
    }

    fun normalSetup(){
        onLost = {
            showToast("网络出事了")
        }

        onNetStateChange = {
            oldState, newState ->
            if(newState == MOBILE){
                showToast("注意,正在使用2G/3G/4G网络")
            }
        }
    }
}