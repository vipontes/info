package br.net.easify.info.View.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import br.net.easify.info.R
import br.net.easify.info.View.About.AboutFragment
import br.net.easify.info.View.Currency.CurrencyFragment
import br.net.easify.info.View.News.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        setupViewPager(pager)
        tabs.setupWithViewPager(pager)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun setupViewPager(pager: ViewPager?) {
        val adapter = Adapter(supportFragmentManager)

        val newsFragment = NewsFragment()
        val currencyFragment = CurrencyFragment()
        val aboutFragment = AboutFragment()

        adapter.addFragment(newsFragment, "News")
        adapter.addFragment(currencyFragment, "Currency")
        adapter.addFragment(aboutFragment, "About")

        pager?.adapter = adapter
    }

    private class Adapter(manager: FragmentManager): FragmentPagerAdapter(manager) {

        val fragments = ArrayList<Fragment>()
        val titles = ArrayList<String>()

        override fun getItem(position: Int): Fragment = fragments.get(position)

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence? = titles.get(position)

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }
    }
}
