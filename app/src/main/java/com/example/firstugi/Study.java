package com.example.firstugi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstugi.Models.StudybookMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class Study extends Fragment {
    private Toolbar toolbar;
    private ImageView imageTimetable;
    private RecyclerView recyclerSbject;
    private DatabaseReference reference;


    private  String subjectName;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_study, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Syllabus").child("Books");

        recyclerSbject = (RecyclerView)view.findViewById(R.id.subject_recycler);
        recyclerSbject.setHasFixedSize(true);
        recyclerSbject.setLayoutManager(new GridLayoutManager(getContext(),3));



        toolbar = (Toolbar)view.findViewById(R.id.toolbar_student);
        ((Home) getActivity()).setSupportActionBar(toolbar);
        ((Home) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<StudybookMdll,BookStuViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<StudybookMdll, BookStuViewHolder>(
                StudybookMdll.class,
                R.layout.subject_card,
                BookStuViewHolder.class,
                reference

        ) {
            @Override
            protected void populateViewHolder(BookStuViewHolder viewHolder, final StudybookMdll model, int position) {
                viewHolder.setImage(model.getImage(),getContext());


                viewHolder.setName(model.getName());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            // Apply activity transition
                            Intent intent = new Intent(getContext(), Subject.class);

                            intent.putExtra("subjectNm",model.getName());

                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            getContext().startActivity(intent);
                        } else {
                            // Swap without transition
                            Intent intent = new Intent(getContext(),Subject.class);
                            intent.putExtra("subjectNm",model.getName());

                            startActivity(intent);
                        }
                    }
                });

            }
        };
        recyclerSbject.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BookStuViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BookStuViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName (String name){
            TextView post_desc = mView.findViewById(R.id.book_name_study);
            post_desc.setText(name);
        }
        public void setImage (String image, Context context){
            ImageView post_desc = mView.findViewById(R.id.book_image_study);
            Picasso.with(context).load(image).into(post_desc);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_toll_item,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    Intent myIntent = new Intent(getContext(), Notificatio.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getContext().startActivity(myIntent);
                } else {
                    // Swap without transition
                    Intent intent = new Intent(getContext(),Notificatio.class);
                    startActivity(intent);
                }
                return true;
        }
        return false;
    }
}