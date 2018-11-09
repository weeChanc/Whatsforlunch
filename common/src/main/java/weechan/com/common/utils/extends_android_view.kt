package weechan.com.common.utils

import android.view.View

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/8 12:38
 *
 */
  
fun View.postDelayed(time : Long , action : () -> Unit){
    this.postDelayed(action,time)
}