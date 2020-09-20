package com.example.snapechat.RecyclerViewFollow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.snapechat.R;
import com.example.snapechat.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;


public class FollowAdapter extends RecyclerView.Adapter<FollowViewHolders>{

    private List<FollowObject> usersList;
    private Context context;

    public FollowAdapter(List<FollowObject> usersList, Context context){
        this.usersList = usersList;
        this.context = context;
    }
    @Override
    public FollowViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_followers_item, null);
        FollowViewHolders rcv = new FollowViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final FollowViewHolders holder, int position) {
        holder.mEmail.setText(usersList.get(position).getEmail());

        if(UserInformation.listFollowing.contains(usersList.get(holder.getLayoutPosition()).getUid())){
            holder.mFollow.setText("following");
        }else{
            holder.mFollow.setText("follow");
        }

        holder.mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(!UserInformation.listFollowing.contains(usersList.get(holder.getLayoutPosition()).getUid())){
                    holder.mFollow.setText("following");
                    FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("following").child(usersList.get(holder.getLayoutPosition()).getUid()).setValue(true);
                }else{
                    holder.mFollow.setText("follow");
                    FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("following").child(usersList.get(holder.getLayoutPosition()).getUid()).removeValue();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.usersList.size();
    }
}

