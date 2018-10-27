package weechan.com.whatsforlunch.ui.dishes

import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_dishes.view.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Dishes

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 19:38
 *
 */
  
class DishesAdapter(dishes: MutableList<Dishes>) : BaseQuickAdapter<Dishes, BaseViewHolder>(R.layout.item_dishes,dishes) {
    override fun convert(helper: BaseViewHolder, item: Dishes?) {
        val image = helper.getView<ImageView>(R.id.dishes_image)
        val name = helper.getView<TextView>(R.id.dishes_name)
        val price = helper.getView<TextView>(R.id.dishes_price)

        item?.let {
            name?.setText(it.productName)
            price?.setText("${it.productPrices[0].price}元起")
        }

    }

}