package com.example.firstugi;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostViewHolder extends RecyclerView.ViewHolder{
    View mView;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setDesc(String desc){
        TextView post_desc = mView.findViewById(R.id.post_description);
        post_desc.setText(desc);
    }

    public void setImageUrl(Context ctx, String imageUrl){

        ImageView  postImage = mView.findViewById(R.id.image_post);

        Picasso.with(ctx).load(imageUrl).into(postImage);

    }
    public void setUserName(String userName){
        TextView postUserName = mView.findViewById(R.id.post_user);
        postUserName.setText(userName);
    }
}
