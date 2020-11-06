package co.com.ceiba.mobile.pruebadeingreso.features.posts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapter;
import co.com.ceiba.mobile.pruebadeingreso.features.users.adapters.UsersAdapterListener;
import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private static final String TAG = PostAdapter.class.getSimpleName();
    private List<Post> posts;
    private PostAdapterListener listener;

    public PostAdapter(PostAdapterListener listener) {
        this.posts = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
        holder.setAdapterListener(post, listener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addAll(List<Post> data) {
        posts.addAll(data);
        notifyDataSetChanged();
    }

    public void add(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }

    public void remove(Post post) {
        posts.remove(post);
        notifyDataSetChanged();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contentCard)
        LinearLayout contentCard;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.body)
        TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setAdapterListener(Post post, PostAdapterListener listener) {
            contentCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(post);
                }
            });
        }
    }
}
