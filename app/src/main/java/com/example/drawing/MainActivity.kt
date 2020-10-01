package com.example.drawing

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.CartFragment
import com.OrdersFragment
import com.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import  androidx.appcompat.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import java.util.ArrayList


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    lateinit var homeFragment: HomeFragment
    lateinit var cartFragment: CartFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var ordersFragment: OrdersFragment

    val list: MutableList<String> = ArrayList()
    val list2= arrayOf<String>("C++","Java","Coa","Google","Facebook","Kotlin","Javascript")

   var result=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val drawerToggle: ActionBarDrawerToggle= object :ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            (R.string.navigation_drawer_open),
            (R.string.navigation_drawer_close)
        )
        {

        }
      drawerToggle.isDrawerIndicatorEnabled=true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        homeFragment= HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        list.addAll(list2)
        var adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        autocomplete.threshold=1
        autocomplete.setAdapter(adapter)

        autocomplete.setOnItemClickListener { adapterView, view, i, l ->
             result=autocomplete.text.toString()
            val toast=Toast.makeText(applicationContext,"You've selected "+result,Toast.LENGTH_SHORT)
            toast.show()
            hidekeyboard()
        }
        autocomplete.setOnEditorActionListener { textView, i, keyEvent ->

            result=autocomplete.text.toString()
            val toast=Toast.makeText(applicationContext,"You've selected "+result,Toast.LENGTH_SHORT)
            toast.show()
            hidekeyboard()
            return@setOnEditorActionListener true
        }

    }


        fun hidekeyboard()
        {
            val view=this.currentFocus
            if(view!=null)
            {
                val hideme= getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
                hideme.hideSoftInputFromWindow(view.windowToken,0)
            }
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }






    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home ->{
                homeFragment= HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.nav_cart ->{
                cartFragment= CartFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,cartFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.nav_profile ->{
                profileFragment= ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,profileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

            }
            R.id.nav_orders ->{
                ordersFragment= OrdersFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,ordersFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else
        {
            super.onBackPressed()

        }
    }


}