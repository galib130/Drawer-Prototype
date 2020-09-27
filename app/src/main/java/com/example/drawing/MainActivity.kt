package com.example.drawing

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
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
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        val manager= getSystemService(Context.SEARCH_SERVICE)as SearchManager
        val searchItem= menu?.findItem(R.id.action_search)
        val searchView =searchItem?.actionView as SearchView


            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty())
                    {
                       list.clear()
                        val search=newText.toLowerCase()
                        list2.forEach {
                                if(it.toLowerCase().contains(search))
                                {
                                     list.add(it)

                                }
                        }
                    }
                    else
                    {


                    }
                    return true
                }

            })




        return super.onCreateOptionsMenu(menu)
    }
}