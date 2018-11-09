package weechan.com.whatsforlunch.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.ui.dishes.DishesActivity

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/26 1:26
 *
 */
  
class MainFragment : androidx.fragment.app.Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener(){
        main_dishes_btn.setOnClickListener {
            startActivity(Intent(this.activity,DishesActivity::class.java))
        }
    }
}