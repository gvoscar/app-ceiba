package co.com.ceiba.mobile.pruebadeingreso.rest;

import android.os.Build;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.BaseTest;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class JSONPlaceholderTest extends BaseTest {
    private JSONPlaceholderService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        JSONPlaceholderClient client = new JSONPlaceholderClient();
        service = client.getJsonPlaceholderService();
    }

    @Test
    public void doSearch_getUserByIdFromBackend() throws Exception {
        Call<User> call = service.getUserById("1");

        Response<User> response = call.execute();
        Assert.assertTrue(response.isSuccessful());

        User user = response.body();
        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId());
    }

    @Test
    public void doSearch_getUsersFromBackend() throws Exception {
        Call<List<User>> call = service.getUsers();

        Response<List<User>> response = call.execute();
        Assert.assertTrue(response.isSuccessful());

        Assert.assertNotNull(response.body());
    }
}
