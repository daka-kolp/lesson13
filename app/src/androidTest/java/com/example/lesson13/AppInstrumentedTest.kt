package com.example.lesson13

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppInstrumentedTest {
    @Rule
    fun activityRule(): ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkInitialBitcoinInfo() {
        Espresso.onView(ViewMatchers.withId(R.id.value)).check(
            ViewAssertions.matches(ViewMatchers.withText("Value"))
        )
    }

    @Test
    fun checkButton() {
        Espresso.onView(ViewMatchers.withId(R.id.make_request)).check(
            ViewAssertions.matches(ViewMatchers.withText("Make a Request"))
        )
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.lesson13", appContext.packageName)
    }
}
