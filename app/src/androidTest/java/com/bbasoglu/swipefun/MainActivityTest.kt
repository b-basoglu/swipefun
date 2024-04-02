package com.bbasoglu.swipefun

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bbasoglu.swipefun.main.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testBottomNavigation() {
        // Verify bottom navigation
        Espresso.onView(ViewMatchers.withId(R.id.feed_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.feed_compose_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.profile_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Perform action to navigate to another fragment
        Espresso.onView(ViewMatchers.withId(R.id.feed_compose_nav))
            .perform(ViewActions.click())

        // Verify the navigation bottom navigation is not gone
        Espresso.onView(ViewMatchers.withId(R.id.feed_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.profile_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        // Perform action to navigate to another fragment
        Espresso.onView(ViewMatchers.withId(R.id.profile_nav))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.feed_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.feed_compose_nav))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}