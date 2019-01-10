package weechan.com.whatsforlunch.ui.orders

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Order
import weechan.com.whatsforlunch.net.baseURL

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 13:15
 *
 */

class OrderAdapter(data: MutableList<Order>) :
        BaseItemDraggableAdapter<Order, BaseViewHolder>(R.layout.item_order, data) {

    init {
        multiTypeDelegate = object : MultiTypeDelegate<Order>() {
            override fun getItemType(t: Order?): Int {
                return t!!.type
            }
        }

        multiTypeDelegate.registerItemType(Order.NORMAL, R.layout.item_order)
        multiTypeDelegate.registerItemType(Order.DETAILS, R.layout.item_detail)
    }


    override fun convert(helper: BaseViewHolder, item: Order) {
        when (helper.itemViewType) {
            Order.DETAILS -> {
                helper.setText(R.id.price, "单价" + item.price.toString())
                helper.setText(R.id.name, "菜名: " +item.product_name)
                Glide.with(mContext).load(baseURL + item.product_icon).into(helper.getView(R.id.image))
                return;
            }

            Order.NORMAL -> {
                val orderId = helper.getView<TextView>(R.id.order_id)
                val buyerName = helper.getView<TextView>(R.id.buyer_name)
                val content = helper.getView<TextView>(R.id.create_time)
                orderId.setText("订单号" + item.orderId)
                buyerName.setText("用户: " + item.userName)
                content.setText(item.createTime.toString())
                if(item.order_status == "new"){
                    helper.getView<TextView>(R.id.status).setText("待处理")
                }else{
                    helper.getView<TextView>(R.id.status).setText("已完成")
                }

                return;
            }


        }

    }
}