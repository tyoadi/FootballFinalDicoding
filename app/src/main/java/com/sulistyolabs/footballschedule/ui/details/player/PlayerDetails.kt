package com.sulistyolabs.footballschedule.ui.details.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.model.Player
import kotlinx.android.synthetic.main.activity_player_details.*
import kotlinx.android.synthetic.main.player_info.*

class PlayerDetails : AppCompatActivity() {

    lateinit var players: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        setSupportActionBar(toolbar)

        players = intent.getParcelableExtra("players")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = players.strPlayer

        initData(players)
    }

    private fun initData(players: Player?) {
        Picasso.get().load(players?.strCutout).into(img_player)
        player_weight.text = players?.strWeight
        player_height.text = players?.strHeight
        player_position.text = players?.strPosition
        player_desc.text = players?.strDescriptionEN
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
