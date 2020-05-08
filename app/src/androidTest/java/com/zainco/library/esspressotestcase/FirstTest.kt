package com.zainco.library.esspressotestcase

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.zainco.library.espressotest.EsspressoMainActivity
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//tun test cases ascendingly based on the name of the test case
class FirstTest {
    @get:Rule
    var activityRule = ActivityTestRule(EsspressoMainActivity::class.java)

    @Before
    fun setText() {

    }

    //this is a test case
    @Test
    fun test1ChatId() {
        // Click on button to open activity
        onView(withText("TextViewZain")).check(matches(withText("TextViewZain")))
    }
}