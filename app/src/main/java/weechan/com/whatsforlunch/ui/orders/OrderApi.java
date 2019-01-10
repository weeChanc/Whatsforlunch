package weechan.com.whatsforlunch.ui.orders;

import android.content.Intent;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.observers.Observers;
import weechan.com.whatsforlunch.data.BaseResponse;
import weechan.com.whatsforlunch.data.Order;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 13:46
 */

public interface OrderApi {

    @GET("order")
    public Observable<BaseResponse<List<Order>>> getOrderList();

    @GET("order/details")
    public Observable<BaseResponse<List<Order>>> getOrderDetails(@Query("orderId") String id);
}
