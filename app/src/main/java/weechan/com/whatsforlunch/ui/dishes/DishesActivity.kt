package weechan.com.whatsforlunch.ui.dishes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_dishes.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.data.DishesCategory

class DishesActivity : AppCompatActivity(), DishesContract.View {

    override lateinit var presenter: DishesContract.Presenter
    private val categoryAdapter = DishesCategoryAdapter(mutableListOf())
    private val dishesAdapter = DishesAdapter(mutableListOf())

    private var preActivedpos = 0;


    override fun showDishes(data: List<Dishes>) {
        dishesAdapter.data.clear()
        dishesAdapter.data.addAll(data)
        dishesAdapter.notifyDataSetChanged()
    }

    override fun showCategories(data: List<DishesCategory>) {
        categoryAdapter.data.clear()
        categoryAdapter.data.addAll(data)
        categoryAdapter.notifyDataSetChanged()
        if(data.size > 0 ){
            presenter.getDishes(data[0].categoryType)
            data[0].isActived = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(dishes_toolbar)
        setContentView(R.layout.activity_dishes)
        this.presenter = DishesContract.Presenter(this)


        dishes_category_list.adapter = categoryAdapter
        dishes_category_list.layoutManager = LinearLayoutManager(this)
        dishes_list.adapter = dishesAdapter
        dishes_list.layoutManager = LinearLayoutManager(this)

        presenter.getDishesCategories()

        categoryAdapter.setOnItemClickListener { adapter, view, position ->
            val item = categoryAdapter.getItem(position)
            if (item != null) {
                item.isActived = true
                categoryAdapter.data.get(preActivedpos)?.isActived = false
                categoryAdapter.notifyItemChanged(position)
                categoryAdapter.notifyItemChanged(preActivedpos)
                preActivedpos = position
                presenter.getDishes(item.categoryType)
            }
        }
    }
}
