package com.ky.plan

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.ArrayMap
import android.view.Menu
import android.view.MenuItem
import com.ky.plan.fragment.PlanFragment
import com.ky.plan.fragment.WorkedFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val mFragments = ArrayMap<Int, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initFragmentList()
    }

    private fun initFragmentList() {
        mFragments[R.id.nav_plan] = PlanFragment()
        mFragments[R.id.nav_worked] = WorkedFragment()

        val transaction = supportFragmentManager.beginTransaction()

        for (fragmentMap in mFragments) {
            transaction.add(R.id.fl_container, fragmentMap.value)
        }
        transaction.commit()

        showFragment(R.id.nav_plan, mFragments)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        showFragment(item.itemId, mFragments)
        when (item.itemId) {
            R.id.nav_plan -> {
                // Handle the camera action

            }
            R.id.nav_worked -> {

            }
            R.id.nav_finished -> {

            }
            R.id.nav_unfinished -> {

            }
            R.id.nav_blog -> {

            }
            R.id.nav_github -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    private var mCurrentFragmentId = -1

    private fun showFragment(fragmentId: Int, fragments: ArrayMap<Int, Fragment>) {
        if (mCurrentFragmentId == fragmentId)
            return

        mCurrentFragmentId = fragmentId

        val transaction = supportFragmentManager.beginTransaction()

          for (mapFragment in fragments) {
              if (mapFragment.key != fragmentId) {
                  transaction.hide(mapFragment.value)
              } else transaction.show(mapFragment.value)
          }
       // transaction.replace(R.id.fl_container, fragments.valueAt(fragments.indexOfKey(mCurrentFragmentId)))
        transaction.commit()
    }
}
