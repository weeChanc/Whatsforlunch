package weechan.com.whatsforlunch.ui.manage;


import java.util.Map;

import okhttp3.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weechan.com.whatsforlunch.data.BaseResponse;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/5 19:16
 */

public interface ManagerApi {
    @GET("/report/daily")
    rx.Observable<BaseResponse<Map>> getDailyReport();

}
