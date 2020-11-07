package co.com.ceiba.mobile.pruebadeingreso.features.users.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.app.base.MyApp;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.ui.PostActivity;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.features.users.di.UsersComponent;
import co.com.ceiba.mobile.pruebadeingreso.features.users.presenters.UsersPresenter;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class UsersActivity extends AppCompatActivity implements UsersView, UsersAdapterListener {


    @BindView(R.id.editTextSearch)
    EditText editTextSearch;
    @BindView(R.id.textInputLayoutSearch)
    TextInputLayout textInputLayoutSearch;
    @BindView(R.id.recyclerViewSearchResults)
    RecyclerView recyclerViewSearchResults;
    @BindView(R.id.content)
    RelativeLayout content;

    private static final String TAG = UsersActivity.class.getSimpleName();
    @BindView(R.id.contentEmpty)
    RelativeLayout contentEmpty;

    // @Inject
    private UsersAdapter adapter;
    // @Inject
    private UsersPresenter presenter;
    private UsersComponent component;

    public UsersActivity() {

    }

    private Handler handler;
    long delay = 500; // 1 seconds after user stops typing
    long last_text_edit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupInjection();

        // iniciar oyente de texto modificado
        initTextChangedListener();

        // iniciar oyente de acción
        initEditorActionListener();


        // this.presenter = new UsersPresenterImpl(this);


        // this.adapter = new UsersAdapter(this);

        this.recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(this.recyclerViewSearchResults);
        this.recyclerViewSearchResults.setAdapter(this.adapter);

        this.adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                super.onChanged();


                int itemCount = adapter.getItemCount();

                //Log.d(TAG, "onChanged : " + itemCount);
                if (itemCount <= 0) {
                    empty(true);
                } else {
                    empty(false);
                }
            }
        });

        this.presenter.onCreate();


    }

    private void empty(boolean sw) {
        if (sw) {
            recyclerViewSearchResults.setVisibility(View.GONE);
            contentEmpty.setVisibility(View.VISIBLE);
        } else {
            contentEmpty.setVisibility(View.GONE);
            recyclerViewSearchResults.setVisibility(View.VISIBLE);
        }
    }

    private void setupInjection() {
        MyApp app = (MyApp) getApplication();
        // app.getUsersComponent(this, this).inject(this);
        component = app.getUsersComponent(this, this);
        component.inject(this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public UsersAdapter getAdapter() {
        return component.getAdapter();
    }

    public UsersPresenter getPresenter() {
        return component.getPresenter();
    }

    private void initTextChangedListener() {
        handler = new Handler();
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeCallbacks(runSearch);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    last_text_edit = System.currentTimeMillis();
                    handler.postDelayed(runSearch, delay);
                    Log.d(TAG, "Termino de escribir: " + s.toString());
                } else {
                    handler.postDelayed(runSearch, delay);
                }
            }
        });
    }

    private Runnable runSearch = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() > (last_text_edit + delay - 500)) {

                if (editTextSearch != null) {
                    String parametros = editTextSearch.getText().toString().trim().toLowerCase();
                    // adapter.getFilter().filter(parametros);
                    adapter.getFilter(parametros);
                }
            }
        }
    };

    private void initEditorActionListener() {
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String parametros = editTextSearch.getText().toString().trim().toLowerCase();
                    int index = parametros.trim().length();
                    // adapter.getFilter().filter(parametros);
                    adapter.getFilter(parametros);

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.presenter.onStart();
    }

    @Override
    protected void onStop() {
        this.presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onNotFound(String message) {
        //
    }

    @Override
    public void onDataLoaded(List<User> users) {
        adapter.clear();
        adapter.setData(users);
    }

    @Override
    public void onItemClick(User user) {
        Log.d(TAG, "onItemClick() USER NAME: " + user.getId());
        Bundle bundle = new Bundle();
        bundle.putInt("id", user.getId());
        bundle.putString("name", user.getName());
        bundle.putString("phone", user.getPhone());
        bundle.putString("email", user.getEmail());

        Intent intent = new Intent(this, PostActivity.class);
        intent.putExtra("user", bundle);
        startActivity(intent);
    }
}