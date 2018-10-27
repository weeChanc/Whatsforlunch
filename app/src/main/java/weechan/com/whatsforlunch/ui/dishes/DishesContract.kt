package weechan.com.whatsforlunch.ui.dishes

import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.data.DishesCategory
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetch

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 10:59
 *
 */
interface DishesContract {
    interface View : BaseView<Presenter> {
        fun showCategories(data: List<DishesCategory>)
        fun showDishes(data: List<Dishes>)
    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(DishesApi::class.java)

        fun getDishesCategories() {
            mApi.getDishesCategories().fetch { result ->
                if (result?.data != null) {
                    view?.showCategories(result.data)
                }
            }
        }

        fun getDishes(type: Int) {
            mApi.getDishes(type).fetch { result ->
                if (result?.data != null) {
                    view?.showDishes(result.data)
                }
            }
        }
    }
}