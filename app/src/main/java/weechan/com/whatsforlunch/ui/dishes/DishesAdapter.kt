package weechan.com.whatsforlunch.ui.dishes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_dishes.view.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.net.baseURL
import weechan.com.whatsforlunch.utils.defaultInto
import java.util.*

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 19:38
 *
 */

class DishesAdapter(dishes: MutableList<Dishes>) : BaseQuickAdapter<Dishes, BaseViewHolder>(R.layout.item_dishes, dishes) {
    override fun convert(helper: BaseViewHolder, item: Dishes?) {
        val image = helper.getView<ImageView>(R.id.dishes_image)
        val name = helper.getView<TextView>(R.id.dishes_name)
        val price = helper.getView<TextView>(R.id.dishes_price)
        val description = helper.getView<TextView>(R.id.dishes_description);

        item?.let {
            name.setText(it.productName)
            price.setText("售价 " + item.min_price +"元起")
//            price?.setText("${it.productPrices[0].price}元起")
            Glide.with(mContext).load(Uri.parse(baseURL + it.productIcon)).into(image)

            description.setText(it.productDescription)
//                val bytes = android.util.Base64.decode(it.productIcon.toString(),Base64.DEFAULT)
//                Glide.with(mContext).load(bytes).defaultInto(image)

        }

    }


}