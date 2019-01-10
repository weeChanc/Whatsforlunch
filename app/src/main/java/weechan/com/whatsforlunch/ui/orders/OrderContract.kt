package weechan.com.whatsforlunch.ui.orders

import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.data.Order
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetchEntity

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 1:20
 *
 */

interface OrderContract{
    interface View : BaseView<Presenter>{
        fun showOrders(orders : List<Order> )
        fun showDetails(o : List<Order> )
    }

    class Presenter(view: View): BasePresenter<View>(view) {
        private val mApi: OrderApi = RetrofitClient.create(OrderApi::class.java)
        fun getOrderList(){
            mApi.orderList.fetchEntity {
                if(it != null)
                    view?.showOrders(it)
            }
        }

        fun getOrderDetails(id : String){
            mApi.getOrderDetails(id).fetchEntity {
                view?.showDetails(it!!)
            }
        }
    }
}