package weechan.com.whatsforlunch.ui.orders

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_orders.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Order

class OrderFragment() : androidx.fragment.app.Fragment(), OrderContract.View {
    override fun showDetails(o: List<Order>) {
        OrderDetails.details.clear()
        OrderDetails.details.addAll(o.map { it.type = Order.DETAILS ; it })
        val intent = Intent(this.context,OrderDetails::class.java)
        startActivity(intent)
    }

    override lateinit var presenter: OrderContract.Presenter
    lateinit var adapter: OrderAdapter

    override fun showOrders(orders: List<Order>) {
        this.datas.clear();
        this.datas.addAll(orders)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = OrderContract.Presenter(this)

        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    private val datas = mutableListOf<Order>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OrderAdapter(datas);
        adapter.setOnItemClickListener { adapter, view, position ->
            Log.e("OrderFragment",position.toString())
            val order = this@OrderFragment.adapter.getItem(position)
            if(order?.price == null && !order?.expand!! ){
                presenter.getOrderDetails(order.orderId!!)
            }
        }
        order_recycler.adapter = adapter;
        order_recycler.layoutManager = LinearLayoutManager(activity)
        presenter.getOrderList()

    }
}
