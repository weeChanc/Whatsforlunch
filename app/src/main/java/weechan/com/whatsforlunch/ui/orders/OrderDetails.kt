package weechan.com.whatsforlunch.ui.orders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.test.view.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Order

class OrderDetails : AppCompatActivity() {

    companion object {
        public val details : MutableList<Order> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        val adapter = OrderAdapter(details);
        detail_recycler.adapter = adapter;
        detail_recycler.layoutManager = LinearLayoutManager(this)


    }
}
