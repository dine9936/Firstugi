package com.example.firstugi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firstugi.Models.CllModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Class extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        reference = FirebaseDatabase.getInstance().getReference().child("college");

        View view = inflater.inflate(R.layout.fragment_class,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);



        return view;

    }
    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<CllModel,PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CllModel, PostViewHolder>(
                CllModel.class,
                R.layout.post_card,
                PostViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, CllModel model, int position) {
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImageUrl(getContext(),model.getImageUrl());
                viewHolder.setUserName(model.getUsername());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
