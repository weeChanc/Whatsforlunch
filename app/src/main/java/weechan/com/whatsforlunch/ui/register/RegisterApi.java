package weechan.com.whatsforlunch.ui.register;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;
import weechan.com.whatsforlunch.data.BaseResponse;
import weechan.com.whatsforlunch.data.User;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/31 20:05
 */

interface RegisterApi {


    @POST("seller")
    Observable<BaseResponse<Object>> register(@Body RequestBody body);

}
