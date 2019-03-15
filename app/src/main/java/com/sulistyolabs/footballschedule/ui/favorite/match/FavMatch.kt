package com.sulistyolabs.footballschedule.ui.favorite.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.MatchAdapter
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_match.*

class FavMatch : Fragment(), FavMatchContract.View {

    private var listFavMatch: MutableList<Event> = mutableListOf()
    private lateinit var mPresenter: FavMatchPresenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = FavMatchPresenter(this)
        swiperefreshFav?.setOnRefreshListener {
            mPresenter.getFootballMatchData(requireContext())
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_match, container, false)
    }

    override fun hideLoading() {
        progressFav?.invisible()

    }

    override fun showLoading() {
        progressFav?.visible()

    }

    override fun onResume() {
        super.onResume()
        mPresenter.getFootballMatchData(requireContext())

    }

    override fun displayFavoriteMatch(matchList: List<Event>) {
        swiperefreshFav?.isRefreshing = false
        listFavMatch.clear()
        listFavMatch.addAll(matchList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleFav.layoutManager = layoutManager
        recycleFav.adapter = MatchAdapter(matchList, context)

    }

}
