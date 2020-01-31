package com.zainco.library.dagger.mitch.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.zainco.library.R
import com.zainco.library.dagger.mitch.BaseActivity
import kotlinx.android.synthetic.main.activity_mitch_dagger_main.*

class MitchDaggerMainActivity : BaseActivity(),
    AppBarConfiguration.OnNavigateUpListener, NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mitch_dagger_main)
        setupNavigation() //setup navigation
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun setupNavigation() {
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)


        /*   val sideNavView = findViewById<NavigationView>(R.id.navigationView)
           sideNavView?.setupWithNavController(navController)
           val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)

           //fragments load from here but how ?
           appBarConfiguration = AppBarConfiguration(
               setOf(R.id.profileFragment, R.id.postsFragment),
               drawerLayout
           )*/
    }

    //enables opening nav drawer but not closing it and it enables the back arrow (2 things)
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.my_nav_host_fragment)
            , drawer_layout
        )

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out_menu -> {
                sessionManager.logOut()
                return true
            }
            android.R.id.home -> {
                // references the top left button of any fragment/activity you are in
                if (drawer_layout.isDrawerOpen(GravityCompat.START))//hamburger top left icon first click opens the nav drawer and second click closes it
                    drawer_layout.closeDrawer(GravityCompat.START)
                else return false
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true

        drawer_layout.closeDrawers()

        when (menuItem.itemId) {
            R.id.profileFragment -> {
                // nav options to clear backstack

                val navOptions = NavOptions.Builder()
                    //destinationId : to popup untill , clearing all interventing destinations
                    // .setPopUpTo(destinationId, inclusive)
                    .setPopUpTo(R.id.dagger_navigation, true)
                    .build()
                navController.navigate(
                    R.id.profileFragment,
                    null,
                    navOptions
                )
            }
            R.id.postsFragment -> navController.navigate(R.id.postsFragment)
        }
        return true

    }
}
