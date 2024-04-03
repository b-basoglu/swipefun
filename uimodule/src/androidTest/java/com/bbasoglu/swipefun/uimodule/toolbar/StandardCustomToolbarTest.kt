package com.bbasoglu.swipefun.uimodule.toolbar

import android.content.Context
import android.view.Gravity
import android.view.View.VISIBLE
import androidx.test.core.app.ApplicationProvider
import com.bbasoglu.swipefun.uimodule.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class StandardCustomToolbarTest {

    private lateinit var context: Context
    private lateinit var standardCustomToolbar: StandardCustomToolbar
    private lateinit var toolbarData: StandardToolbarData

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        standardCustomToolbar = StandardCustomToolbar(context)
        toolbarData = StandardToolbarData(
            title = "Test Title",
            titleColorId = R.color.black,
            isTitleCenter = true,
            leftIconResourceId = com.google.android.material.R.drawable.ic_arrow_back_black_24,
            leftIconColorId = R.color.white,
            textAnimationEnabled = false,
            leftIconText = "Back",
            rightIconResourceId = R.drawable.ic_settings,
            rightIconColorId = R.color.white
        )
    }

    @Test
    fun `testSetToolbar`() {
        val mockToolbarIconClick = mock(StandardCustomToolbar.ToolbarIconClick::class.java)
        val mockToolbarIconClickRight = mock(StandardCustomToolbar.ToolbarIconClick::class.java)

        toolbarData.toolbarLeftIconClick = mockToolbarIconClick
        toolbarData.toolbarRightIconClick = mockToolbarIconClickRight

        standardCustomToolbar.setToolbar(toolbarData)

        assertEquals(standardCustomToolbar.visibility, VISIBLE)
        assertEquals(standardCustomToolbar.binding.toolbarTitleTV.text.toString(), "Test Title")
        assertEquals(standardCustomToolbar.binding.toolbarTitleTV.gravity, Gravity.CENTER)

        // Verify left icon click listener is set
        standardCustomToolbar.binding.toolbarLeftIconIV.performClick()
        // Verify that toolbarIconClick's onClick method is called
        verify(mockToolbarIconClick).onClick()

        // Verify right icon click listener is set
        standardCustomToolbar.binding.toolbarRightIconIV.performClick()
        // Verify that toolbarIconClick's onClick method is called
        verify(mockToolbarIconClickRight).onClick()
    }

    // Write more tests for other methods as needed
}
