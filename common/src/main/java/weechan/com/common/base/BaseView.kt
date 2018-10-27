package weechan.com.common.base

import android.arch.lifecycle.LifecycleOwner

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @onCreate 2018/10/21 16:30
 *
 */

interface BaseView<out P : IPresenter> : LifecycleOwner {
    val presenter : P
}


