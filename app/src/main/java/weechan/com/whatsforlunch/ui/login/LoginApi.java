package weechan.com.whatsforlunch.ui.login;


import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import weechan.com.whatsforlunch.data.BaseResponse;
import weechan.com.whatsforlunch.data.User;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 19:48
 */

interface LoginApi {
    @POST("login")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<BaseResponse<User>> login(@Body Map body);
}
