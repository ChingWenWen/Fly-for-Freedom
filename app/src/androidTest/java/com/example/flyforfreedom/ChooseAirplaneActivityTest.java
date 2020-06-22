package com.example.flyforfreedom;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

public class ChooseAirplaneActivityTest {
    @Rule
    public IntentsTestRule<ChooseAirplaneActivity> intentsTestRule = new IntentsTestRule<>(ChooseAirplaneActivity.class);
    public ActivityTestRule<ChooseAirplaneActivity> activityTestRule = new ActivityTestRule<>(ChooseAirplaneActivity.class);

    @Test
    public void testNextIntend(){
        String name="空中巴士A350";
        String speed="912";
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeRight());
        onView(withId(R.id.viewPager)).perform(swipeRight());
        onView(withId(R.id.viewPager)).perform(swipeRight());


        onView(withId(R.id.next)).perform(click());
//        intended(hasComponent(ChooseAirplaneActivity.class.getName()));
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("speed",speed);
        i.putExtras(bundle);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK,i);
        intending(toPackage(MapsActivity.class.getName())).respondWith(result);
    }
}