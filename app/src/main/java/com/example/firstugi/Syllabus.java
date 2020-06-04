package com.example.firstugi;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstugi.Models.SubjectMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Syllabus extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private FirebaseDatabase database;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("syllabus");

        View view = inflater.inflate(R.layout.fragment_syllabus,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_syllabus);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<SubjectMdll,SubjectViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SubjectMdll, SubjectViewHolder>(
                SubjectMdll.class,
                R.layout.syllabus_subjects_card,
                SubjectViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(SubjectViewHolder viewHolder, SubjectMdll model, int position) {
                viewHolder.setSbjctname(model.getSbjctname());
                viewHolder.setTchrname(model.getTchrname());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class SubjectViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTchrname(String tchrname){
            TextView tchr_name = mView.findViewById(R.id.tchrname);
            tchr_name.setText(tchrname);
        }
        public void setSbjctname(String sbjctname){
            TextView sbjct_name = mView.findViewById(R.id.subject_name);
            sbjct_name.setText(sbjctname);
        }
    }
}
