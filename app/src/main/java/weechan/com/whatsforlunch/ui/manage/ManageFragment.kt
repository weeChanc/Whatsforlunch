package weechan.com.whatsforlunch.ui.manage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_manage.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.ui.dishes.DishesActivity
import java.math.BigDecimal

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/30 16:42
 *
 */

class ManageFragment : androidx.fragment.app.Fragment(), ManagerContract.View {
    override fun showDailyData(map: Map<String, String>) {
        order.setText(map.get("count").toString()+"单")
        money.setText(map.get("amount").toString() + "元")

        val money_gap =  map.get("amount_gap").toString().toDouble()
        val order_gap = map.get("order_gap").toString().toDouble()

        if(money_gap < 0){
            moneyAdd.setText("下降" + (-money_gap) + "元")
        }else{
            moneyAdd.setText("增长" + money_gap + "元")
        }

        if(order_gap < 0){
            orderAdd.setText("下降${-order_gap.toInt()}单")
        }else{
            orderAdd.setText("上升${order_gap.toInt()}单")
        }

    }

    override lateinit var presenter: ManagerContract.Presenter
    private lateinit var order : TextView
    private lateinit var orderAdd : TextView
    private lateinit var money : TextView
    private lateinit var moneyAdd : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = ManagerContract.Presenter(this)
        val view = inflater.inflate(R.layout.fragment_manage, container, false)
        order = view.findViewById(R.id.order)
        orderAdd = view.findViewById(R.id.order_add)
        money = view.findViewById(R.id.money)
        moneyAdd = view.findViewById(R.id.money_add)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manage_menu.setOnClickListener {
            startActivity(Intent(this.activity, DishesActivity::class.java))
        }
        presenter.showDailyData()

    }
}