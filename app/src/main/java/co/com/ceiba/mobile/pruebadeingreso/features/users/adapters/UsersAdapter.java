package co.com.ceiba.mobile.pruebadeingreso.features.users.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> implements Filterable {

    private static final String TAG = UsersAdapter.class.getSimpleName();
    private List<User> users;
    private UsersAdapterListener listener;

    private List<User> data;

    public UsersAdapter(List<User> users, UsersAdapterListener listener) {
        // this.users = new ArrayList<>();
        this.users = users;
        this.listener = listener;
        // this.data = new ArrayList<>();
        this.data = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());
        holder.setUsersAdapterListener(user, listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setData(List<User> data) {
        this.data = data;
        addAll(data);
    }

    public void addAll(List<User> data) {
        users.addAll(data);
        notifyDataSetChanged();
    }

    public void add(User User) {
        users.add(User);
        notifyDataSetChanged();
    }

    public void remove(User user) {
        users.remove(user);
        notifyDataSetChanged();
    }

    public void clear() {
        users.clear();
        notifyDataSetChanged();
    }

    public void notFound() {

    }

    public void getFilter(CharSequence constraint) {
        Log.d(TAG, "getFilter().    FILTRAR : " + constraint);
        clear();
        if (constraint.length() <= 0) {
            Log.d(TAG, "getAutocomplete().    RESTABLECER ");
            addAll(data);
            return;
        }

        for (User user : data) {
            if (user.getName().trim().toLowerCase().startsWith(constraint.toString())) {
                add(user);
            }
        }

        Log.d(TAG, "getAutocomplete().    COINCIDENCIAS : " + getItemCount());
        if (getItemCount() <= 0) {
            // notFound();
        }
    }

    @Override
    public Filter getFilter() {

        Log.d(TAG, "getFilter().    INICIAR FILTRADO");
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null) {
                    List<User> autocomplete = new ArrayList<>();
                    for (User user : users) {
                        if (user.getName().trim().toLowerCase().startsWith(constraint.toString())) {
                            autocomplete.add(user);
                        }
                    }

                    users = autocomplete;
                    if (autocomplete.size() > 0) {
                        results.values = autocomplete;
                        results.count = autocomplete.size();
                    }
                }

                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count > 0) {
                    Log.d(TAG, "publishResults().   Hay Resultados.");
                    // addAll((List<User>) results.values);
                    notifyDataSetChanged();
                } else {
                    Log.d(TAG, "publishResults().   No hay Resultados.");
                    // addAll(data);
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.btn_view_post)
        Button btn_view_post;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setUsersAdapterListener(User user, UsersAdapterListener listener) {
            btn_view_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });
        }
    }
}
