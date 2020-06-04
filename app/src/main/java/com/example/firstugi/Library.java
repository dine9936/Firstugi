package com.example.firstugi;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstugi.Models.LibrMddl;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class Library extends Fragment {
private EditText searchText;
private ImageButton searchButton;
private RecyclerView recyclerView;
private DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_library, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("library");
        recyclerView = (RecyclerView)view.findViewById(R.id.search_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchText =(EditText)view.findViewById(R.id.search_text);
        searchButton =(ImageButton)view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String serch = searchText.getText().toString().trim();


        Query query = reference.orderByChild("name").startAt(serch).endAt(serch+"\uf8ff");
        FirebaseRecyclerAdapter<LibrMddl,LibraryViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<LibrMddl, LibraryViewHolder>(LibrMddl.class,
                R.layout.books_card,
                LibraryViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(LibraryViewHolder viewHolder, LibrMddl model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setStatus(model.getStatus());
                viewHolder.setImage(getContext(),model.getImage());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
});

        return view;
    }


    public static class LibraryViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName (String name){
            TextView post_desc = mView.findViewById(R.id.book_name);
            post_desc.setText(name);
        }
        public void setStatus (String status){
            TextView post_desc = mView.findViewById(R.id.book_status);
            post_desc.setText(status);
        }
        public void setImage(Context ctx, String image){

            ImageView postImage = mView.findViewById(R.id.book_image);

            Picasso.with(ctx).load(image).into(postImage);

        }


    }


}
