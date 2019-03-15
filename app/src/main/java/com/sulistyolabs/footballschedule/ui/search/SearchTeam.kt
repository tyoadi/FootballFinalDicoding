package com.sulistyolabs.footballschedule.ui.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.MatchAdapter
import com.sulistyolabs.footballschedule.adapter.TeamsAdapter
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeam : AppCompatActivity(), SearchContract.ViewTeam {


    private lateinit var mPresenter: SearchTeamPresenter
    private var listSearch: MutableList<Team> = mutableListOf()
    private lateinit var adapter: TeamsAdapter
    private var menuItem: Menu? = null
    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.title = "Search Team"

        query = intent.getStringExtra("query")
        Log.e("Query hasil :", query)

        adapter = TeamsAdapter(listSearch, this)
        rv_team_search.layoutManager = LinearLayoutManager(this)
        rv_team_search.adapter = adapter

        val gson = Gson()
        val request = ApiRepository()
        mPresenter = SearchTeamPresenter(this, request, gson)
        mPresenter.getQueryTeam(query)
    }

    override fun showLoading() {
        pb_search_team.visible()

    }

    override fun hideLoading() {
        pb_search_team.invisible()
    }

    override fun showQueryTeam(data: List<Team>) {
        listSearch.clear()
        for (item in data) {
            if (item.strSport.equals("Soccer")) {
                listSearch.add(item)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        menuItem = menu

        val searchView = menuItem?.findItem(R.id.action_search)?.actionView as SearchView?
        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { mPresenter.getQueryTeam(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_search -> {
                val searchView = menuItem?.findItem(R.id.action_search)?.actionView as SearchView?

                searchView?.queryHint = "Search matches"
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let { mPresenter.getQueryTeam(it) }
                        return false
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
