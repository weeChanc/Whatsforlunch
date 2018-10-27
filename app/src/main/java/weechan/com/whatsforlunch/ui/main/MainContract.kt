package weechan.com.whatsforlunch.ui.main

import android.os.Handler
import android.os.Looper
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.App
import kotlin.concurrent.thread

interface MainContract {
    interface View : BaseView<Presenter>{
        fun showToast(msg: String)
    }

    class Presenter(view : View?): BasePresenter<View>(view) {

        fun costTimeOperation(){
            view?.showToast("开始")
            thread {
                Thread.sleep(10000)
                Handler(Looper.getMainLooper()).post{
                    view?.showToast("NICE JOB ")
                }
            }
        }

    }
}