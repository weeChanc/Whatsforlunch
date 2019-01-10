package weechan.com.whatsforlunch.ui.register

import okhttp3.MediaType
import okhttp3.RequestBody
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.common.utils.showToast
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
        fun registerSuccess();

    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(RegisterApi::class.java)

        fun getCityList(): List<String> {
            return listOf("北京", "上海", "广州", "深圳")
        }

        fun register(shop_image: File, params: Map<String, Any>) {
            view?.showUploadDialog()
            val builder = Oks.buildForm();
            for ((k, v) in params) {
                builder.addFormDataPart(k, v.toString());
            }
            builder.addFormDataPart("shop_images", shop_image.name,
                    RequestBody.create(MediaType.parse("image/*"),shop_image))

            mApi.register(builder.build()).fetch {
                if (it?.code != 200) {
                    showToast("注册失败,该手机号已注册")
                } else {
                    view?.registerSuccess()
                }
                view?.closeUploadDialog()
            }
        }
    }
}