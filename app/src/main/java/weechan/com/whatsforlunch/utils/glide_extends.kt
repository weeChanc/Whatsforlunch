package weechan.com.whatsforlunch.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import weechan.com.whatsforlunch.App
import weechan.com.whatsforlunch.R

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/1 23:17
 *
 */
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun RequestBuilder<Drawable>.defaultInto(view : ImageView){
    this.apply(RequestOptions().error(R.drawable.ic_menu_send)).transition(withCrossFade()).into(view)
}
