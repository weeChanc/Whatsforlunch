package weechan.com.whatsforlunch.ui.register

import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.net.RetrofitClient

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 21:07
 *
 */
  
interface RegisterContract {
    interface View : BaseView<Presenter> {


    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(RegisterApi::class.java)

        fun getCityList(): List<String> {
            return listOf("北京","上海","广州","深圳")
        }
    }
}