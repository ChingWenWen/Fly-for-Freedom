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
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MapsActivityTest {
    @Rule
    public IntentsTestRule<MapsActivity> intentsTestRule = new IntentsTestRule<>(MapsActivity.class);
    public ActivityTestRule<MapsActivity> activityTestRule = new ActivityTestRule<>(MapsActivity.class);

    @Test
    public void mapsIntent(){
        String name="空中巴士A350";
        String speed="912";
        onView(withId(R.id.startingPoint)).perform(typeText("taiwan"));
        onView(withId(R.id.endingPoint)).perform(typeText("usa"));


    }


}