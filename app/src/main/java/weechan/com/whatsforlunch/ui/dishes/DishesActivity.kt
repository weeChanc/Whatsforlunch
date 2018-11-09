package weechan.com.whatsforlunch.ui.dishes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_dishes.*
import org.jetbrains.anko.*
import weechan.com.common.utils.screen.dp2px
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Dishes
import weechan.com.whatsforlunch.data.DishesCategory

class DishesActivity : AppCompatActivity(), DishesContract.View {

    override lateinit var presenter: DishesContract.Presenter
    private val categoryAdapter = DishesCategoryAdapter(mutableListOf())
    private val dishesAdapter = DishesAdapter(mutableListOf())
    private var activedpos = 0;


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

    override fun updateCategory(category: DishesCategory) {
        categoryAdapter.data.add(category)
        categoryAdapter.notifyItemInserted(categoryAdapter.data.size-1)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(dishes_toolbar)
        setContentView(R.layout.activity_dishes)
        this.presenter = DishesContract.Presenter(this)


        dishes_category_list.adapter = categoryAdapter
        dishes_category_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        dishes_list.adapter = dishesAdapter
        dishes_list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        presenter.getDishesCategories()

        categoryAdapter.setOnItemClickListener { _, _, position ->
            if(activedpos == position) return@setOnItemClickListener
            val item = categoryAdapter.getItem(position)
            if (item != null) {
                item.isActived = true
                categoryAdapter.data.get(activedpos)?.isActived = false
                categoryAdapter.notifyItemChanged(position)
                categoryAdapter.notifyItemChanged(activedpos)
                activedpos = position
                presenter.getDishes(item.categoryType)
            }
        }

        dishes_add.setOnClickListener{
            dishes_add.animate().rotation(180f).setDuration(300L).start()
            selector("添加类型", listOf("类目","菜品")){
                dialogInterface, i ->
                when(i){
                    0 -> run {
                        alert() {
                            customView{
                                verticalLayout {
                                    val gap =  dp2px(16)
                                    val title = textView("新建类目"){
                                        textSize = 20f
                                    }
                                    val editText = editText(){
                                        hint = "类目名称"
                                    }
                                    editText.lparams(width = matchParent,height = wrapContent){
                                        marginStart = gap
                                        marginEnd = gap

                                    }
                                    title.lparams(wrapContent, wrapContent){
                                        leftMargin = gap
                                        topMargin = gap
                                        bottomMargin = gap
                                    }

                                    lparams(width = matchParent, height = wrapContent)
                                    yesButton { presenter.addCategory(editText.text.toString()) }
                                }
                            }
                        }.show()
                    }
                    1 -> presenter.addDishAt(activedpos);
                }
            }
        }
    }
}
