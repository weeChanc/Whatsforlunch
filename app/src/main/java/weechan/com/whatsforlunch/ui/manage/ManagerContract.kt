package weechan.com.whatsforlunch.ui.manage

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.common.utils.showToast
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetch

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 18:45
 *
 */

interface ManagerContract {
    interface View : BaseView<Presenter> {
        fun showDailyData(map: Map<String, String>)
    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(ManagerApi::class.java)

        fun showDailyData() {
            mApi.dailyReport.fetch {
                view?.showDailyData(it?.data as Map<String, String>)
            }
        }

    }
}