package com.sulistyolabs.footballschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.ui.details.teams.TeamsDetails
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class TeamsAdapter(val teamList: List<Team>, val context: Context?)
    : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TeamsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(teamList[position])

    }

    inner class TeamsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItem(teamList: Team) {
            itemView.team_name.text = teamList.strTeam
            Picasso.get().load(teamList.strTeamBadge).into(itemView.team_badge)

            itemView.setOnClickListener {
                itemView.context.startActivity<TeamsDetails>("teams" to teamList)

            }
        }
    }
}