package com.bbasoglu.swipefun.core

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.bbasoglu.swipefun.data.readString
import com.bbasoglu.swipefun.data.writeString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class DataStoreTest {

    @Test
    fun testWriteAndReadString() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val key = "test_key"
        val value = "test_value"

        runBlocking {
            context.writeString(key, value)
            launch {
                val result = context.readString(key).first()
                assertEquals(value, result)
            }.join() // This ensures that the coroutine is completed before the test ends
        }
    }
}
