package com.acme.a3csci3130;

/**
 * Created by rainb on 2017/7/9.
 * test create contact function
 */
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;



public class CRUDtest {
    private String testName;
    private String testEmail;
    private String testBusinessNum;
    private String testPrimaryBusiness;
    private String testAddress;
    private String testProvince;

    private String testEditName;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initString(){
        /**
         * use series of new data to test
         * add family name to test update(edit)
         */
        testName = "wang";
        testEmail = "wang@123.com";
        testBusinessNum = "987654123";
        testPrimaryBusiness = "fisher";
        testAddress = "6321 south st";
        testProvince = "ns";

        testEditName = " lang";

    }

    /**
     * test for CREATE, UPDATE and DELETE
     * the list will show up when the app is opened
     */
    @Test    //Create
    public void findText(){

        onView(withId(R.id.submitButton)).perform(click());//click the CREATE CONTACT button

        /**
         * input the testing contact information
         */
        onView(withId(R.id.name)).perform(typeText(testName));
        onView(withId(R.id.email)).perform(typeText(testEmail));
        onView(withId(R.id.BusinessNumber)).perform(typeText(testBusinessNum));
        onView(withId(R.id.PrimaryBusiness)).perform(typeText(testPrimaryBusiness));
        onView(withId(R.id.Address)).perform(typeText(testAddress));
        onView(withId(R.id.Province)).perform(typeText(testProvince)).perform(closeSoftKeyboard());

        SystemClock.sleep(1500);
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test     //Update
    public void editTest(){
        onView(withId(R.id.button4)).perform(click());//click the EDIT button

        onView(withId(R.id.textView)).check(matches(withText("select a record that you want to edit")));//check if jump into the edit contact page

        onData(anything()).inAdapterView(withId(R.id.list2)).atPosition(0).perform(click());//click the first line of the list

        onView(withId(R.id.name)).perform(typeText(testEditName)).perform(closeSoftKeyboard());//edit the name

        SystemClock.sleep(1500);
        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test       //Delete
    public void deleteTest(){
        onView(withId(R.id.button3)).perform(click());//click the DELETE button

        onView(withId(R.id.textView2)).check(matches(withText("select a record that you want to delete")));

        SystemClock.sleep(1500);

        onData(anything()).inAdapterView(withId(R.id.list3)).atPosition(0).perform(click());
    }

    @Test       //Read
    public void readTest(){
        SystemClock.sleep(1500);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
    }

}
