package com.sulistyolabs.footballschedule.ui.desc


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.model.Team
import kotlinx.android.synthetic.main.fragment_desc_team.*


class DescTeamFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desc_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (arguments != null) {
            val team: Team? = arguments?.getParcelable("team")
            overview.text = team?.strDescriptionEN
        } else {
            overview.text = "No Decription parsing"
        }

    }

}
