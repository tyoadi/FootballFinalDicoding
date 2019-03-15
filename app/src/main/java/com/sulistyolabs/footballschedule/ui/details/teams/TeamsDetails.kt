package com.sulistyolabs.footballschedule.ui.details.teams

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.FragmentPagerAdapter
import com.sulistyolabs.footballschedule.db.FavoriteTeam
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.ui.desc.DescTeamFragment
import com.sulistyolabs.footballschedule.ui.players.PlayerFragment
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.activity_teams_details.*
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamsDetails : AppCompatActivity(), TeamsDetailsContract.View {

    lateinit var pagesAdapter: FragmentPagerAdapter
    lateinit var mPrenter: TeamsDetailsPresenter

    lateinit var team: Team
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_details)
        setSupportActionBar(tool_bar)

        team = intent.getParcelableExtra("teams")

        val args = Bundle()
        args.putParcelable("team",team)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        pagesAdapter = FragmentPagerAdapter(supportFragmentManager)
        val overview = DescTeamFragment()
        val player = PlayerFragment()
        overview.arguments = args
        player.arguments = args
        pagesAdapter.addFragment(overview, "Overview")
        pagesAdapter.addFragment(player, "Players")
        viewpagerTeam.adapter = pagesAdapter
        tabsTeam.setupWithViewPager(viewpagerTeam)

        mPrenter = TeamsDetailsPresenter(this)

        id = team.idTeam
        favoriteState()
        initData(team)

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id)
            val favorite = result.column(FavoriteTeam.JSON_DATA).parseList(StringParser)
            isFavorite = favorite.isNotEmpty()

        }
        setFavorite()

    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun initData(team: Team) {
        dt_team_name.text = team.strTeam
        dt_team_year.text = team.intFormedYear
        dt_team_stadium.text = team.strStadium
        Picasso.get().load(team.strTeamBadge).into(dt_team_bagde)
        hideLoading()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) {
                    mPrenter.removeFavorite(team.idTeam, this)
                    toast("Remove from FavoriteTeam")
                } else {
                    mPrenter.addFavorite(team, this)
                    toast("Added FavoriteTeam")
                }
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun hideLoading() {
        progressDTeam.invisible()

    }

    override fun showLoading() {
        progressDTeam.visible()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("overview", team)
    }

}
