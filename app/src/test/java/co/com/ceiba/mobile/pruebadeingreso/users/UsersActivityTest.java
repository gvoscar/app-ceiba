package co.com.ceiba.mobile.pruebadeingreso.users;

import android.os.Build;

import androidx.recyclerview.widget.RecyclerView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

import co.com.ceiba.mobile.pruebadeingreso.BaseTest;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.features.users.presenters.UsersPresenter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersActivity;
import co.com.ceiba.mobile.pruebadeingreso.features.users.ui.UsersView;
import co.com.ceiba.mobile.pruebadeingreso.libs.base.ImageLoader;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import co.com.ceiba.mobile.pruebadeingreso.support.ShadowRecyclerView;
import co.com.ceiba.mobile.pruebadeingreso.support.ShadowRecyclerViewAdapter;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1}, shadows = {ShadowRecyclerView.class, ShadowRecyclerViewAdapter.class})
public class UsersActivityTest  extends BaseTest {
    @Mock
    private UsersAdapter adapter;

    @Mock
    private UsersPresenter presenter;

    @Mock
    private ImageLoader imageLoader;

    private UsersView view;
    private UsersActivity activity;
    private UsersAdapterListener listener;

    private ShadowActivity shadowActivity;
    private ActivityController<UsersActivity> controller;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        UsersActivity usersActivity = new UsersActivity(){
            @Override
            public void setTheme(int resid) {
                super.setTheme(R.style.AppTheme);
            }

            @Override
            public UsersAdapter getAdapter() {
                return adapter;
            }

            @Override
            public UsersPresenter getPresenter() {
                return presenter;
            }
        };

        controller = ActivityController.of(usersActivity).create().visible();
        activity = controller.get();
        shadowActivity = Shadows.shadowOf(activity);

        view = (UsersView) activity;
        listener  = (UsersAdapterListener) activity;
    }

    @Test
    public void onActivityCreated() {
        verify(presenter).onCreate();
    }

    @Test
    public void onActivityDestroy() {
        controller.destroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void getUsersFromPresenter_setInAdapter() {
        List<User> userList = Arrays.asList(new User[]{
                new User(),
                new User(),
                new User()});

        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        adapter.addAll(userList);
        verify(adapter).addAll(argumentCaptor.capture());
        Assert.assertEquals(userList.size(), argumentCaptor.getValue().size());
    }
}
