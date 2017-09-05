package harsh.recyclerviewdemo.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import harsh.recyclerviewdemo.Model.ChatModel;
import harsh.recyclerviewdemo.R;

/**
 * Created by ngamacmini17 on 05/09/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatModel> chatlist;


    public ChatAdapter(List<ChatModel> chatModels) {
        this.chatlist = chatModels;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_users_recycler_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        final ChatModel model=chatlist.get(position);
        holder.tvname.setText(model.getName());
        holder.tvphone.setText(model.getPhone());
        Glide.with(holder.imageView.getContext())
                .load(model.getPhoto())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname, tvphone;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.tv_name);
            tvphone = (TextView) itemView.findViewById(R.id.tv_phone);
            imageView = (ImageView) itemView.findViewById(R.id.ivImage);


        }
    }
}
