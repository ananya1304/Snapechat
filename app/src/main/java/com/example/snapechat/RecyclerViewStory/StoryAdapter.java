package com.example.snapechat.RecyclerViewStory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snapechat.R;

import java.util.List;


public class StoryAdapter extends RecyclerView.Adapter<StoryViewHolders>{

    private List<StoryObject> usersList;
    private Context context;

    public StoryAdapter(List<StoryObject> usersList, Context context){
        this.usersList = usersList;
        this.context = context;
    }
    @Override
    public StoryViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_story_item, null);
        StoryViewHolders rcv = new StoryViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final StoryViewHolders holder, int position) {
        holder.mEmail.setText(usersList.get(position).getEmail());
        holder.mEmail.setTag(usersList.get(position).getUid());

        holder.mLayout.setTag(usersList.get(position).getCharOrStory());
    }

    @Override
    public int getItemCount() {
        return this.usersList.size();
    }
}
