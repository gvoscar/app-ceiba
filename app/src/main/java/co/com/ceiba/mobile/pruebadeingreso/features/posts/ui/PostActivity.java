package co.com.ceiba.mobile.pruebadeingreso.features.posts.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.adapters.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.adapters.PostAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.presenters.PostPresenter;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.presenters.PostPresenterImpl;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.presenters.UsersPresenterImpl;
import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class PostActivity extends AppCompatActivity implements PostView, PostAdapterListener {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.recyclerViewPostsResults)
    RecyclerView recyclerViewPostsResults;

    private static final String TAG = PostActivity.class.getSimpleName();
    private PostPresenter presenter;
    private PostAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        this.presenter = new PostPresenterImpl(this);


        this.adapter = new PostAdapter(this);

        this.recyclerViewPostsResults.setLayoutManager(new LinearLayoutManager(this));
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this.recyclerViewPostsResults);
        this.recyclerViewPostsResults.setAdapter(this.adapter);

        this.presenter.onCreate();

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras().getBundle("user");
            if (bundle != null) {
                user = new User();
                user.setId(bundle.getInt("id"));
                user.setName(bundle.getString("name"));
                user.setPhone(bundle.getString("phone"));
                user.setEmail(bundle.getString("email"));
            }
        }

        loadUser();
    }

    private void loadUser() {
        if (user != null) {
            name.setText(user.getName());
            phone.setText(user.getPhone());
            email.setText(String.valueOf(user.getEmail()));
//            if (post.isFavorite()) {
//                imgStar0.setVisibility(View.GONE);
//                imgStar1.setVisibility(View.VISIBLE);
//            } else {
//                imgStar0.setVisibility(View.VISIBLE);
//                imgStar1.setVisibility(View.GONE);
//            }

            presenter.getAllUserPosts(String.valueOf(user.getId()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNotFound(String message) {
        //
    }

    @Override
    public void onDataLoaded(List<Post> posts) {
        adapter.clear();
        adapter.addAll(posts);
    }

    @Override
    public void onItemClick(Post post) {
        Log.d(TAG, "onItemClick().  POST : " + post.getTitle());
    }
}
