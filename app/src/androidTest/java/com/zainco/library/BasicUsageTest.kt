/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zainco.library

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BasicUsageTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun observableFieldsActivity_likes() {
        // Click on button to open activity
        onView(withId(R.id.observable_fields_activity_button)).perform(click())

        // Click Like 5 times
        repeat(5) {
            onView(withId(R.id.like_button)).perform(click())
        }

        // Check that the number of likes is displayed
        onView(withId(R.id.likes)).check(matches(withText("5")))
    }

    @Test
    fun viewmodelActivity_likes() {
        // Click on button to open activity
        onView(withId(R.id.viewmodel_activity_button)).perform(click())

        // Click Like 5 times
        repeat(5) {
            onView(withId(R.id.like_button)).perform(click())
        }

        // Check that the number of likes is displayed and the progressBar appeared
        onView(withId(R.id.likes)).check(matches(withText("5")))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }
}
