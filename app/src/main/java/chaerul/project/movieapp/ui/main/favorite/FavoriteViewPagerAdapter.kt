package chaerul.project.movieapp.ui.main.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FavoriteViewPagerAdapter(supportFragmentManager: FragmentManager): FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}