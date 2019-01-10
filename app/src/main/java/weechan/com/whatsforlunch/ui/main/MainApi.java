package weechan.com.whatsforlunch.ui.main;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import weechan.com.whatsforlunch.data.BaseResponse;
import weechan.com.whatsforlunch.data.Order;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 15:30
 */

public interface MainApi {

    @GET("order/todo")
    public Observable<BaseResponse<List<Order>>> getTodoOrder();

    @GET("order/details")
    public Observable<BaseResponse<List<Order>>> getOrderDetails(@Query("orderId") String id);

    @GET("order/finsih")
    public Observable<BaseResponse<Object>> finishOrder(@Query("orderId") String id);
}
