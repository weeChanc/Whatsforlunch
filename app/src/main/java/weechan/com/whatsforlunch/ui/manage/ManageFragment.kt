package weechan.com.whatsforlunch.ui.manage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_manage.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.ui.dishes.DishesActivity

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/30 16:42
 *
 */
  
class ManageFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_manage,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manage_menu.setOnClickListener {
            startActivity(Intent(this.activity,DishesActivity::class.java))
        }

    }
}