package weechan.com.whatsforlunch.ui.dishes

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import weechan.com.whatsforlunch.data.BaseResponse
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.data.DishesCategory

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 11:06
 *
 */

interface DishesApi {
    @GET("/category")
    fun  getDishesCategories() : Observable<BaseResponse<List<DishesCategory>>>

    @GET("/product")
    fun getDishes(@Query("categoryType") categoryType : Int) : Observable<BaseResponse<List<Dishes>>>
}