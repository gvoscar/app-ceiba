package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Application;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowApplicationPackageManager;

public abstract class BaseTest {
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        if (ApplicationProvider.getApplicationContext() !=null){
            ShadowApplication shadowApplication = Shadows.shadowOf((Application) ApplicationProvider.getApplicationContext());
            shadowApplication.grantPermissions("android.permission.INTERNET");
        }
    }
}
