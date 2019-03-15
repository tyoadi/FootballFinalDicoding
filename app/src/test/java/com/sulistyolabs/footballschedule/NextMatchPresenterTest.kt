package com.sulistyolabs.footballschedule

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Events
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import com.sulistyolabs.footballschedule.ui.match.MatchContract
import com.sulistyolabs.footballschedule.ui.match.NextMatchPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private
    lateinit var mView: MatchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var presnter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presnter = NextMatchPresenter(mView, apiRepository, gson)
    }

    @Test
    fun testGetNextMatch() {
        val match: MutableList<Event> = mutableListOf()
        val response = Events(match)
        val idLeauge = "4328"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository
                    .doRequest(DBSportApi.getNextMatch(idLeauge))
                    .await(),
                    Events::class.java
            )).thenReturn(response)

            presnter.getMatchData(idLeauge)

            Mockito.verify(mView).showLoading()
            Mockito.verify(mView).showListEvent(match)
            Mockito.verify(mView).hideLoading()
        }

    }
}