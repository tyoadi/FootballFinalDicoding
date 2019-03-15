package com.sulistyolabs.footballschedule.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.R.id.*
import com.sulistyolabs.footballschedule.ui.favorite.FavTab
import com.sulistyolabs.footballschedule.ui.match.MatchFragment
import com.sulistyolabs.footballschedule.ui.search.SearchMatch
import com.sulistyolabs.footballschedule.ui.search.SearchTeam
import com.sulistyolabs.footballschedule.ui.team.TeamFragment
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.home_main.*
import org.jetbrains.anko.startActivity

class BottomNavActivity : AppCompatActivity() {

    var menuItem : Menu? = null
    var matchesFrag = MatchFragment()
    var teamsFrag = TeamFragment()
    var favoritesFrag = FavTab()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_main)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {

                nav_match -> {
                    showSearch(true)
                    loadFragment(matchesFrag)
                    return@setOnNavigationItemSelectedListener true
                }

                nav_team -> {
                    showSearch(true)
                    loadFragment(teamsFrag)
                    return@setOnNavigationItemSelectedListener true
                }

                nav_favorites -> {
                    showSearch(false)
                    loadFragment(favoritesFrag)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        bottom_navigation.selectedItemId = nav_match
    }

    private fun loadFragment(fragment : Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frag_container , fragment)
                .commit()
    }

    private fun showSearch(status: Boolean) {
        menuItem?.getItem(0)?.isVisible = status
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchView = menuItem?.findItem(R.id.action_search)?.actionView as SearchView?

                searchView?.queryHint = "Search Matches"
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        when(bottom_navigation.selectedItemId) {
                            R.id.nav_match -> {
                                startActivity<SearchMatch>("query" to query)
                            }
                            R.id.nav_team -> {
                                startActivity<SearchTeam>("query" to query)
                            }
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }

                })
            }

        }
        return super.onOptionsItemSelected(item)
    }


}
