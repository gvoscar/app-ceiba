package co.com.ceiba.mobile.pruebadeingreso.users;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersActivity;

@RunWith(AndroidJUnit4.class)
public class UsersActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<UsersActivity> activityScenarioRule = new ActivityScenarioRule<UsersActivity>(UsersActivity.class);

    @Test
    public void editTextSearchChanges() {
        onView(withId(R.id.editTextSearch))
                .perform(typeText("gvoscar"), closeSoftKeyboard());
        onView(withId(R.id.editTextSearch))
                .check(matches(withText("gvoscar")));
    }
}
