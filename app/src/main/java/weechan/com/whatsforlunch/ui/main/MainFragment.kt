package weechan.com.whatsforlunch.ui.main

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import kotlinx.android.synthetic.main.fragment_main.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Order
import weechan.com.whatsforlunch.ui.orders.OrderAdapter
import weechan.com.whatsforlunch.ui.orders.OrderDetails
import kotlin.concurrent.thread

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 1:26
 *
 */
  
class MainFragment : androidx.fragment.app.Fragment() ,MainContract.View{

    val datas : MutableList<Order> = mutableListOf();
    val adapter = OrderAdapter(datas)

    override fun showTodoOrders(msg: List<Order>) {
        datas.clear();
        datas.addAll(msg)
        adapter.notifyDataSetChanged()
    }

    override fun showDetails(d: List<Order>) {
        OrderDetails.details.clear()
        OrderDetails.details.addAll(d.map { it.type = Order.DETAILS ; it })
        val intent = Intent(this.context, OrderDetails::class.java)
        startActivity(intent)
    }

    override lateinit var presenter: MainContract.Presenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = MainContract.Presenter(this)
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todo.adapter = adapter;
        adapter.enableSwipeItem();
        todo.layoutManager = LinearLayoutManager(activity)
        adapter.setOnItemClickListener { adapter, _, position ->
            Log.e("OrderFragment",position.toString())
            val order = this@MainFragment.adapter.getItem(position)
            if(order?.price == null && !order?.expand!! ){
                presenter.getOrderDetails(order.orderId!!)
            }
        }
        adapter.setOnItemLongClickListener { adapter, view, position ->
            presenter.finsihOrder(this@MainFragment.adapter.getItem(position)?.orderId!!)
            datas.removeAt(position)
            adapter.notifyItemRemoved(position)
            true
        }
        adapter.setOnItemSwipeListener(object: OnItemSwipeListener {
            override fun clearView(p0: RecyclerView.ViewHolder?, p1: Int) {
                Log.e("MainFragment",p1.toString())
            }

            override fun onItemSwiped(p0: RecyclerView.ViewHolder?, p1: Int) {
            }

            override fun onItemSwipeStart(p0: RecyclerView.ViewHolder?, p1: Int) {
            }

            override fun onItemSwipeMoving(p0: Canvas?, p1: RecyclerView.ViewHolder?, p2: Float, p3: Float, p4: Boolean) {
            }
        })

        presenter.getTodoOrder()

        thread {
            while (true){
                Thread.sleep(10000)
                presenter.getTodoOrder()
            }
        }
    }

}