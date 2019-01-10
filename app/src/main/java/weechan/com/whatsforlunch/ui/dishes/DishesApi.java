package weechan.com.whatsforlunch.ui.dishes;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import weechan.com.whatsforlunch.data.BaseResponse;
import weechan.com.whatsforlunch.data.Dishes;
import weechan.com.whatsforlunch.data.DishesCategory;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/3 17:29
 */

public interface  DishesApi {
    @GET("/seller/category")
    Observable<BaseResponse<List<DishesCategory>>> getDishesCategories();
    @GET("/seller/product")
    Observable<BaseResponse<List<Dishes>>> getDishes(@Query("category_id") int categoryId);
    @POST("/seller/category")
    Observable<BaseResponse<DishesCategory>> addCategory(@Body DishesCategory category);
    @POST("/seller/product")
    Observable<BaseResponse<Object>> addProduct(@Query("category_id") int id , @Body RequestBody body);
    @DELETE("/seller/category")
    Observable<BaseResponse<Object>> removeCategoryAt(@Query("category_id") int id );
}
