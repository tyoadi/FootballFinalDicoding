package com.sulistyolabs.footballschedule.ui.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.MatchAdapter
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Leaugues
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast


class LastMatchFragment : Fragment(), MatchContract.View {


    private var matchs: MutableList<Event> = mutableListOf()
    private lateinit var mPresenter : LastMatchPresenter
    lateinit var adapter: MatchAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        mPresenter = LastMatchPresenter(this, request, gson)
        mPresenter.getLeagueData()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun showAllLeague(data: Leaugues?) {

        val spinnerItem = data?.leagues?.map {
            it.strLeague
        }

        val spinnerId = data?.leagues?.map {
            it.idLeague
        }


        spinnerLastMatch?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem)
        spinnerLastMatch?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerId?.get(position)?.let { mPresenter.getMatchData(it) }

                swipeLast?.setOnRefreshListener {
                    spinnerId?.get(position)?.let { mPresenter.getMatchData(it) }
                }
            }

        }

    }

    override fun hideLoading() {
        progressLast?.invisible()

    }

    override fun showLoading() {
        progressLast?.visible()
    }

    override fun showListEvent(data: List<Event>) {
        swipeLast?.isRefreshing = false
        matchs.clear()
        matchs.addAll(data)
        adapter = MatchAdapter(data, context)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleLast?.layoutManager = layoutManager
        recycleLast?.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onFailure() {
        toast("Failed to get data, cek your internet connection and refress")
    }

}
