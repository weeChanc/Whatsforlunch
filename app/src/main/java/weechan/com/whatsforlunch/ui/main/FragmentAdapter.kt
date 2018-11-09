package weechan.com.whatsforlunch.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import weechan.com.whatsforlunch.ui.manage.ManageFragment
import weechan.com.whatsforlunch.ui.orders.OrderFragment

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/1 14:01
 *
 */
  
class FragmentAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val fragments = listOf<androidx.fragment.app.Fragment>(MainFragment(),OrderFragment(),ManageFragment())

    override fun getItem(p0: Int): androidx.fragment.app.Fragment {
        return fragments.get(p0)
    }

    override fun getCount() = 3

}