package com.example.practicaltestpythonserver.mvvm

import androidx.core.util.Predicate.not
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.runner.AndroidJUnit4
import com.example.practicaltestpythonserver.R
import dagger.hilt.android.testing.HiltAndroidTest

@HiltAndroidTest
@MediumTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    @Before
    fun setup() {
        // Launch the fragment in the container
        launchFragmentInContainer<LoginFragment>()
    }

    @Test
    fun testEmailNotEmpty() {
        // Leave email empty and type a valid password
        onView(withId(R.id.edtUserName)).perform(typeText(""))
        onView(withId(R.id.edtPassword)).perform(typeText("validPassword"))
    }

    @Test
    fun testEmailMinLength() {
        // Type an email with less than 5 characters
        onView(withId(R.id.edtUserName)).perform(typeText("a@b.c"))
        onView(withId(R.id.edtPassword)).perform(typeText("validPassword"))
    }

    @Test
    fun testEmailValidRegex() {
        // Type an invalid email
        onView(withId(R.id.edtUserName)).perform(typeText("invalidEmail"))
        onView(withId(R.id.edtPassword)).perform(typeText("validPassword"))

        // Type a valid email
        onView(withId(R.id.edtUserName)).perform(clearText(), typeText("valid@example.com"))
        onView(withId(R.id.edtPassword)).perform(typeText("validPassword"))
    }

    @Test
    fun testPasswordNotEmpty() {
        // Leave password empty and type a valid email
        onView(withId(R.id.edtUserName)).perform(typeText("valid@example.com"))
        onView(withId(R.id.edtPassword)).perform(typeText(""))
    }

    @Test
    fun testAllConditionsMet() {
        // Type a valid email and password
        onView(withId(R.id.edtUserName)).perform(typeText("valid@example.com"))
        onView(withId(R.id.edtPassword)).perform(typeText("validPassword"))
    }
}
