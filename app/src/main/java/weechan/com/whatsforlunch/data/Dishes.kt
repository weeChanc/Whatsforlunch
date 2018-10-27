package weechan.com.whatsforlunch.data

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 19:26
 *
 */
  
data class Dishes(val productName : String,
                  val productPrices:List<DishesPrice>,
                  val productStock: Int,
                  val productDescription : String,
                  val productLabel: String,
                  val productIcon : String,
                  val productStatus : Int)

data class DishesPrice(val price : Double, val spec : String)