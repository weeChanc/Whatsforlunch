package weechan.com.whatsforlunch.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.net.RetrofitClient
import weechan.com.whatsforlunch.ui.manage.ManageFragment

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

        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container,MainFragment())
                .addToBackStack(null)
                .commit()

        bottom_nav.setOnNavigationItemSelectedListener {
            item ->
            when(item.itemId){
                R.id.home -> run{

                }
                R.id.orders -> run{

                }
                R.id.setting -> run{
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_fragment_container, ManageFragment())
                            .addToBackStack(null)
                            .commit()
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
                showToast("swithc")
                RetrofitClient.switch()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
