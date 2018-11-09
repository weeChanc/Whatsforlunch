package weechan.com.whatsforlunch.ui.register

import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.Oks
import weechan.com.whatsforlunch.utils.fetch
import java.io.File

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 21:07
 *
 */
  
interface RegisterContract {
    interface View : BaseView<Presenter> {

        fun showUploadDialog()
        fun closeUploadDialog()

    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(RegisterApi::class.java)

        fun getCityList(): List<String> {
            return listOf("北京","上海","广州","深圳")
        }

        fun register(yyzz: File, sfz: File, params: Map<String,String>){
            view?.showUploadDialog()
            mApi.register(Oks.formFilePart("businessLincence",yyzz),
                    Oks.formFilePart("idcard",sfz),params).fetch {
                view?.closeUploadDialog()
            }
        }
    }
}