package weechan.com.whatsforlunch.data

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 11:07
 *
 */

data class DishesCategory(val category_id: Int,
                          val category_name: String,
                          val categoryType: Int? = null,
                          var isActived: Boolean = false)