package com.sulistyolabs.footballschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.model.Player
import com.sulistyolabs.footballschedule.ui.details.player.PlayerDetails
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.startActivity

class PlayersAdapter(val playerList: List<Player>, val context: Context?)
    : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player,parent, false))

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(playerList[position])
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(playerList: Player) {

            itemView.player_name.text = playerList.strPlayer
            itemView.player_position.text = playerList.strPosition
            Picasso.get().load(playerList.strCutout).into(itemView.player_badge)

            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerDetails>("players" to playerList)
            }
        }

    }


}