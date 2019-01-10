package weechan.com.whatsforlunch.ui.dishes

import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import weechan.com.common.base.BasePresenter
import weechan.com.common.base.BaseView
import weechan.com.common.utils.Maps
import weechan.com.common.utils.showToast
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.data.DishesCategory
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.utils.fetch
import weechan.com.whatsforlunch.utils.fetchEntity
import java.io.File
import java.util.*

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
        fun updateCategory(category: DishesCategory)
    }

    class Presenter(view: View) : BasePresenter<View>(view) {
        private val mApi = RetrofitClient.create(DishesApi::class.java)

        fun removeCategory(id: Int){
            mApi.removeCategoryAt(id).fetch {
                showToast("删除成功")
            }
        }

        fun getDishesCategories() {
            mApi.getDishesCategories().fetch { result ->
                if (result?.data != null) {
                    view?.showCategories(result.data)
                }
            }
        }

        fun getDishes(categoryId: Int) {
            mApi.getDishes(categoryId).fetch { result ->
                if (result?.data != null) {
                    view?.showDishes(result.data)
                }
            }
        }

        fun addDishAt(id: Int, dish: Dishes, specs: Map<*, *>) {
            val rb = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("name", dish.productName)
                    .addFormDataPart("description", dish.productDescription)
                    .addFormDataPart("icon", File(dish.productIcon).name,
                            RequestBody.create(MediaType.parse("image/*"), File(dish.productIcon)))
                    .addFormDataPart("category_id", id.toString())

            for ((k, v) in specs) {
                rb.addFormDataPart(k.toString(), v.toString())
            }

            mApi.addProduct(id, rb.build())
                    .fetch {
                        if (it?.code == 200) {
                            showToast("添加成功")
                        } else {
                            showToast("添加失败")
                        }
                    }

        }

        fun addCategory(categoryName: String) {
            mApi.addCategory(DishesCategory(-1, categoryName, -1, false))
                    .fetch {
                        if (it?.code == 200) {
                            getDishesCategories()
                        }
                    }
        }

        override fun refresh() {
            Log.e("Presenter", "check")
            showToast("界面更新中!!")
            this.getDishesCategories()
        }
    }
}