package com.codingwithmitch.daggerpractice.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.codingwithmitch.BaseActivity
import com.codingwithmitch.daggerpractice.R
import com.codingwithmitch.daggerpractice.ui.main.posts.PostsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity: BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val TAG = "MainActivity"
    }
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        initNavigation()
    }
    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
            }
            android.R.id.home -> {
                closeDrawerIfOpen()
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun logout() : Boolean {
        sessionManager.logOut()
        return true
    }

    private fun closeDrawerIfOpen() : Boolean {
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, navOptions)
            }
            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen)
                }
            }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination: Int): Boolean {
        return  destination != Navigation.findNavController(this, R.id.nav_host_fragment)
            .currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout)
    }
}