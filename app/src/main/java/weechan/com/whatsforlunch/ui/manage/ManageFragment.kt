package weechan.com.whatsforlunch.ui.manage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_manage.*
import weechan.com.whatsforlunch.R

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/30 16:42
 *
 */
  
class ManageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_manage,container,false)
    }
}