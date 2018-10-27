package weechan.com.whatsforlunch.ui.orders

import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 1:20
 *
 */
  
interface OrderContract{
    interface View : BaseView<Presenter>{

    }

    class Presenter(view: View): BasePresenter<View>(view) {

    }
}