package weechan.com.whatsforlunch.ui.main

import android.os.Handler
import android.os.Looper
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.App
import weechan.com.whatsforlunch.data.Order
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetch
import weechan.com.whatsforlunch.utils.fetchEntity
import kotlin.concurrent.thread

interface MainContract {
    interface View : BaseView<Presenter>{
        fun showTodoOrders(msg: List<Order>)
        fun showDetails(d : List<Order>)
    }

    class Presenter(view : View?): BasePresenter<View>(view) {

        private val mApi = RetrofitClient.create(MainApi::class.java)
        fun getTodoOrder(){
            mApi.todoOrder.fetchEntity {
                view?.showTodoOrders(it!!)
            }
        }

        fun getOrderDetails(id : String){
            mApi.getOrderDetails(id).fetchEntity {
                view?.showDetails(it!!)
            }
        }

        fun finsihOrder(id : String){
            mApi.finishOrder(id).fetch {

            }
        }

    }
}