package com.sulistyolabs.footballschedule.adapter

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.ui.details.matches.MatchDetails
import com.sulistyolabs.footballschedule.utils.*
import kotlinx.android.synthetic.main.item_match.view.*
import org.jetbrains.anko.startActivity

class MatchAdapter(val eventList: List<Event>, val context: Context?)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHoder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MatchViewHoder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: MatchViewHoder, position: Int) {
        holder.bindItem(eventList[position])
    }


    inner class MatchViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(eventList: Event) {

            val date: String = DateFormat.formatDate(eventList.dateEvent!!)
            val time = changeTimezone(eventList.strTime)
            val dateCal: String = DateFormat.formatDateCal(eventList.dateEvent!!)

            val cal  = dateCal +", "+ time

            if (eventList.intAwayScore == null && eventList.intHomeScore == null) {

                itemView.notify.visibility = View.VISIBLE

            } else {

                itemView.notify.visibility = View.GONE
            }

            itemView.strHomeTeam.text = eventList.strHomeTeam
            itemView.intHomeScore.text = eventList.intHomeScore
            itemView.strAwayTeam.text = eventList.strAwayTeam
            itemView.intAwayScore.text = eventList.intAwayScore
            itemView.dateEvent.text = date
            itemView.timeEvent.text = time

            itemView.matchClick.setOnClickListener {
                itemView.context.startActivity<MatchDetails>("match" to eventList)
            }

            itemView.notify.setOnClickListener {
                addToCalender(eventList, cal)
            }
        }

        private fun addToCalender(eventList: Event, cal: String) {
            val i = Intent(Intent.ACTION_EDIT)
            i.type = "vnd.android.cursor.item/event"
            i.putExtra(CalendarContract.Events.TITLE, eventList.strEvent)
            i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, getMilliseconds(cal, true))
            i.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, getMilliseconds(cal, false))
            context?.startActivity(i)

        }
    }
}

