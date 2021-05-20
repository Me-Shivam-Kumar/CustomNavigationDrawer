package com.ncidious.customnavigationdrawer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mainActivityFrameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerView: DrawerLayout
    private val HOME_FRAGMENT = 0
    private val MYORDERS_FRAGMENT = 1

    private var currentFragment = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)


        navigationView=findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        drawerView=findViewById(R.id.drawer_layout)

        mainActivityFrameLayout=findViewById(R.id.main_frame_layout)

        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerView,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerView.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = Color.parseColor("#FFFFFF")
        toggle.syncState()
        drawerView.setScrimColor(Color.parseColor("#3700B3"))
        setFragment(HomeFragment(), HOME_FRAGMENT)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when(id){
            R.id.Home-> setFragment(HomeFragment(), HOME_FRAGMENT)
            R.id.nav_my_orders-> setFragment(MyOrdersFragment(), MYORDERS_FRAGMENT)
        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun setFragment(fragment: Fragment, fragmentNo: Int) {
        if (fragmentNo != currentFragment) {
            currentFragment = fragmentNo
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(mainActivityFrameLayout.getId(), fragment)
            fragmentTransaction.commit()
        }
    }
}