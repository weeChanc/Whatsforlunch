package weechan.com.whatsforlunch.ui.dishes

import android.graphics.Color
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.DishesCategory

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 14:24
 *
 */
  
class DishesCategoryAdapter(data : MutableList<DishesCategory>) :
        BaseQuickAdapter<DishesCategory, BaseViewHolder>(R.layout.item_dishes_category,data) {
    override fun convert(helper: BaseViewHolder, item: DishesCategory) {
        val categoryName = helper.getView<TextView>(R.id.dishes_category_name)
        categoryName.text = item.categoryName
        if(item.isActived){
            categoryName.setTextColor(Color.rgb(234, 32, 0))
        }else{
            categoryName.setTextColor(Color.rgb(39, 40, 34))
        }
    }
}
