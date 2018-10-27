package weechan.com.whatsforlunch.data

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/21 20:20
 *
 */
  
data class BaseResponse<T>(val code: Int, val message: String, val data: T)