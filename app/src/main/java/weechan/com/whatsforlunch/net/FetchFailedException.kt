package weechan.com.whatsforlunch.net

import java.lang.RuntimeException

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/2 9:54
 *
 */
  
class FetchFailedException(val code : Int,  override val message: String?) : RuntimeException(){

}