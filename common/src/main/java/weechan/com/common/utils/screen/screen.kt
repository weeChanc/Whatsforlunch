package weechan.com.common.utils.screen

import android.content.Context
import android.util.DisplayMetrics
import weechan.com.common.utils.Utils.Companion.app
import weechan.com.common.utils.windowManager

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/3 10:43
 *
 */

fun dp2px(dp: Number) = (dp.toFloat() * app.resources.displayMetrics.density + 0.5f).toInt()

fun sp2px(sp: Number) = (sp.toFloat() * app.resources.displayMetrics.scaledDensity + 0.5f).toInt()

fun px2dp(px: Number) = (px.toFloat() / app.resources.displayMetrics.density + 0.5f).toInt()

fun px2sp(px: Number) = (px.toFloat() / app.resources.displayMetrics.scaledDensity + 0.5f).toInt()
