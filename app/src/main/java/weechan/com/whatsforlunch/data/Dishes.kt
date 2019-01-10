package weechan.com.whatsforlunch.data

import com.google.gson.annotations.SerializedName

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 19:26
 *
 */

data class Dishes(
        @SerializedName("product_name")
        val productName: String,
        @SerializedName("product_description")
        val productDescription: String,
        val productLabel: String,
        @SerializedName("product_icon")
        val productIcon: String,
        var min_price: Double = -1.0 /*只用在返回显示 , */)

data class DishesPrice(val price: Double, val spec: String)