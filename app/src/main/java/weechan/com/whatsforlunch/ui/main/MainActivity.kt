package weechan.com.whatsforlunch.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.data.Order

class MainActivity : AppCompatActivity() {



    private lateinit var adapter : FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        adapter = FragmentAdapter(supportFragmentManager)

        main_fragment_viewpager.adapter = adapter

  
//        main_fragment_viewpager.
        main_fragment_viewpager.addOnPageChangeListener(object: androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(page: Int) {
                with(bottom_nav){
                    when(page){
                        0 -> selectedItemId = R.id.home
                        1 -> selectedItemId = R.id.orders
                        2 -> selectedItemId = R.id.manage
                    }
                }
            }
        })


        bottom_nav.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.home -> run{
                    main_fragment_viewpager.setCurrentItem(0)
                }
                R.id.orders -> run{
                    main_fragment_viewpager.setCurrentItem(1)
                }
                R.id.manage -> run{
                    main_fragment_viewpager.setCurrentItem(2)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
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
            R.id.action_settings -> run{
//                RetrofitClient.switch()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
