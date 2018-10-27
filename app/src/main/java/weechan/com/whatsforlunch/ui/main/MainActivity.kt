package weechan.com.whatsforlunch.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import weechan.com.whatsforlunch.R

class MainActivity : AppCompatActivity(),MainContract.View {
    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override lateinit var presenter: MainContract.Presenter

    private val fragmentManager : FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter = MainContract.Presenter(this)

        fragmentManager.beginTransaction().replace(R.id.main_fragment_container,MainFragment()).addToBackStack(null).commit()

        bottom_nav.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.home -> return@setOnNavigationItemSelectedListener true
                R.id.orders -> return@setOnNavigationItemSelectedListener true
                R.id.setting -> return@setOnNavigationItemSelectedListener true
                else -> {
                    return@setOnNavigationItemSelectedListener  true
                }
            }
        }
//
//        Gson().fromJson("{ code: 200, message: \"success\", data:[ {categoryName: \"粤港风味\",categoryType: 1}, " +
//                "{categoryName: \"潮汕风味\",categoryType: 2}, {categoryName: \"c的口味\",categoryType: 3}] }", BaseResponse<DishesCategory>)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
