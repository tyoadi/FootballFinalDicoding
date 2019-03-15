package com.sulistyolabs.footballschedule

import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.sulistyolabs.footballschedule.R.id.*
import com.sulistyolabs.footballschedule.ui.main.BottomNavActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchFragmentTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(BottomNavActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        delay()
        onView(withId(nav_match))
                .perform(click())
        delay()
        onView(withId(recycleNext))
                .check(matches(isDisplayed()))
        onView(withId(recycleNext))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(recycleNext))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()
        onView(withId(add_to_favorite))
                .perform(click())
        pressBack()

        delay()
        onView(withId(nav_team))
                .perform(click())
        delay()
        onView(withId(recycleTeam))
                .check(matches(isDisplayed()))
        delay()
        onView(withId(recycleTeam))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        delay()
        onView(withId(recycleTeam))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        delay()
        delay()
        delay()
        onView(withId(add_to_favorite))
                .perform(click())
        delay()
        pressBack()

        delay()
        onView(withId(nav_favorites))
                .perform(click())
        delay()
        onView(withId(recycleFav))
                .check(matches(isDisplayed()))

        onView(withId(pagerTab))
                .perform(swipeLeft())

        delay()

    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}