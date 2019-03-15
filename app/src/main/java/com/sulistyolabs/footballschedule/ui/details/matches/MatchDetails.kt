package com.sulistyolabs.footballschedule.ui.details.matches

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.R.id.add_to_favorite
import com.sulistyolabs.footballschedule.R.menu.detail_menu
import com.sulistyolabs.footballschedule.db.FavoriteMatch
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.utils.DateFormat
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.activity_match_details.*
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class MatchDetails : AppCompatActivity(), MatchDetailsContract.View {

    lateinit var event: Event
    lateinit var mPresenterMatch: MatchDetailsPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun hideLoading() {
        progressImgTeam.invisible()

    }

    override fun showLoading() {
        progressImgTeam.visible()

    }

    override fun displayHomeTeamBadge(team: List<Team>?) {
            Picasso
                    .get()
                    .load(team?.get(0)?.strTeamBadge)
                    .into(homeImg)

    }

    override fun displayAwayTeamBadge(team: List<Team>?) {
            Picasso
                    .get()
                    .load(team?.get(0)?.strTeamBadge)
                    .into(awayImg)
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        event = intent.getParcelableExtra("match")
        id = event.idEvent

        val toolbar = supportActionBar
        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.title = event.strEvent


        val request = ApiRepository()
        val gson = Gson()
        mPresenterMatch = MatchDetailsPresenter(this, request, gson)
        mPresenterMatch.getHomeTeamsBadge(event.idHomeTeam)
        mPresenterMatch.getAwayTeamsBadge(event.idAwayTeam)

        favoriteState()

        initData(event)

    }

    private fun initData(event: Event) {

        dateEvent.text = event.dateEvent?.let { DateFormat.formatDate(it) }
        strHomeTeam.text = event.strHomeTeam
        strAwayTeam.text = event.strAwayTeam

        intHomeScore.text = event.intHomeScore
        intAwayScore.text = event.intAwayScore

        strHomeGoal.text = event.strHomeGoalDetails
        strAwayGoal.text = event.strAwayGoalDetails

        strHomeDefense.text = event.strHomeLineupDefense
        strAwayDefense.text = event.strAwayLineupDefense

        strHomeMid.text = event.strHomeLineupMidfield
        strAwayMid.text = event.strAwayLineupMidfield

        strHomeForw.text = event.strHomeLineupForward
        strAwayForw.text = event.strAwayLineupForward

        strHomeSub.text = event.strHomeLineupSubstitutes
        strAwaySub.text = event.strAwayLineupSubstitutes

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                    .whereArgs("(MATCH_ID = {id})",
                            "id" to id)
            val favorite = result.column(FavoriteMatch.JSON_DATA).parseList(StringParser)
            isFavorite = favorite.isNotEmpty()

        }
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            add_to_favorite -> {
                if (isFavorite) {
                    mPresenterMatch.removeFavorite(event.idEvent, this)
                    toast("Remove from FavoriteMatch")
                } else {
                    mPresenterMatch.addFavorite(event, this)
                    toast("Added FavoriteMatch")
                }
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}
