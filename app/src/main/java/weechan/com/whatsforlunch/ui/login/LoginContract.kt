package weechan.com.whatsforlunch.ui.login

import android.util.Log
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.common.utils.Maps
import weechan.com.common.utils.showToast
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetch
import weechan.com.whatsforlunch.utils.fetchMain

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 19:34
 *
 */

interface LoginContract {
    interface View : BaseView<Presenter> {
        fun startLogin()
        fun endLogin()
    }

    class Presenter(view: View) : BasePresenter<View>(view) {

        private val mApi: LoginApi = RetrofitClient.create(LoginApi::class.java)

        fun login(account: String, password: String) {
            view?.startLogin()
            mApi.login(Maps.buildMap<String> {
                "type" - "1"
                "phone" - account
                "password" - password })
                    .doOnError {}
                    .fetch {
                        if(it?.code != 200) {
                            showToast("用户名或密码错误")
                        }else{
                            Log.e("[LOGIN SUCCESS]",it.data.toString())
//                            RetrofitClient.addToken(it.data.token)
                            RetrofitClient.token = it.data.token
                            view?.endLogin()
                        }

                    }
        }
    }
}