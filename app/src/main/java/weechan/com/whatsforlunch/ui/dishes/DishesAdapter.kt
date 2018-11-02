package weechan.com.whatsforlunch.ui.dishes

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_dishes.view.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.utils.defaultInto
import java.util.*

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
            if(it.productIcon != null){
                val bitmapArray = Base64.decode(it.productIcon,Base64.DEFAULT);
                val bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size);
                Glide.with(mContext).load(bitmap).defaultInto(image)
            }else{
                Glide.with(mContext).load("").defaultInto(image)
            }

        }

    }



}