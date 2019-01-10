package weechan.com.whatsforlunch.data

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 13:20
 *
 */

data class Order (

        @SerializedName("order_id")
        val orderId: String? = null,
        @SerializedName("order_amount")
        val orderAmount: Double? = null,
        @SerializedName("order_status")
        val order_status: String? = null,
        @SerializedName("create_time")
        val createTime: Date? = null,
        @SerializedName("phone")
        val phone: String? = null,
        @SerializedName("username")
        val userName: String? = null,

        val product_name: String? = null,
        val price: Double? = null,
        val product_description: String? = null,
        val product_icon: String? = null,
        val spec: String? = null,


        var type: Int = 0,
        var expand : Boolean = false
) {

    companion object {
        val DETAILS = 1;
        val NORMAL = 0;
    }

}
