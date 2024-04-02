package com.bbasoglu.swipefun.uimodule.circleimageview

import android.net.Uri
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bbasoglu.swipefun.uimodule.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CircleImageViewTest {

    private lateinit var scenario: ActivityScenario<CircleImageTestActivity>

    @Before
    fun setUp() {
        // Launch the activity under test
        scenario = ActivityScenario.launch(CircleImageTestActivity::class.java)
    }

    @Test
    fun testSetUrlImage() {
        val imageUrl = "https://example.com/image.jpg"

        onView(withId(R.id.circleImageView)).perform(setUrlImage(imageUrl))

        onView(withId(R.id.circleImageView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSetUriImage() {
        val uri = Uri.parse("android.resource://com.bbasoglu.swipefun/drawable/back_button")

        onView(withId(R.id.circleImageView)).perform(setUriImage(uri))

        onView(withId(R.id.circleImageView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSetImageResource() {
        val imageResource = R.drawable.ic_settings

        onView(withId(R.id.circleImageView)).perform(setImageResource(imageResource))

        onView(withId(R.id.circleImageView))
            .check(matches(isDisplayed()))
    }

    private fun setUrlImage(url: String) = object : ViewAction {
        override fun getDescription(): String = "Set URL image"

        override fun getConstraints(): Matcher<View> = allOf(isDisplayed(), isAssignableFrom(CircleImageView::class.java))

        override fun perform(uiController: UiController?, view: View?) {
            (view as? CircleImageView)?.setUrlImage(url)
        }
    }

    private fun setUriImage(uri: Uri) = object : ViewAction {
        override fun getDescription(): String = "Set URI image"

        override fun getConstraints(): Matcher<View> = allOf(isDisplayed(), isAssignableFrom(CircleImageView::class.java))

        override fun perform(uiController: UiController?, view: View?) {
            (view as? CircleImageView)?.setUriImage(uri)
        }
    }

    private fun setImageResource(resourceId: Int) = object : ViewAction {
        override fun getDescription(): String = "Set image resource"

        override fun getConstraints(): Matcher<View> = allOf(isDisplayed(), isAssignableFrom(CircleImageView::class.java))

        override fun perform(uiController: UiController?, view: View?) {
            (view as? CircleImageView)?.setImageResource(resourceId)
        }
    }
}
