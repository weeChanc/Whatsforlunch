package weechan.com.whatsforlunch.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import weechan.com.whatsforlunch.ui.manage.ManageFragment
import weechan.com.whatsforlunch.ui.orders.OrderFragment

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/1 14:01
 *
 */
  
class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments = listOf<Fragment>(MainFragment(),OrderFragment(),ManageFragment())

    override fun getItem(p0: Int): Fragment {
        return fragments.get(p0)
    }

    override fun getCount() = 3

}