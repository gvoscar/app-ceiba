package co.com.ceiba.mobile.pruebadeingreso.support;

import androidx.recyclerview.widget.RecyclerView;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowViewGroup;

/**
 * Created by gvoscar
 */
@Implements(value = RecyclerView.class)
public class ShadowRecyclerView extends ShadowViewGroup {
    private int smoothScrollPosition = -1;

    @Implementation
    public void smoothScrollToPosition(int position) {
        setSmoothScrollToPosition(position);
    }

    public int getSmoothScrollToPosition() {
        return smoothScrollPosition;
    }

    public void setSmoothScrollToPosition(int position) {
        smoothScrollPosition = position;
    }
}
