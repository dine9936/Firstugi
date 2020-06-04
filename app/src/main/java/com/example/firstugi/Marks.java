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

import com.example.firstugi.Models.IntrnlmarkMll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Marks extends Fragment {
    private DatabaseReference reference;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marks, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("internalmark");

        recyclerView = (RecyclerView) view.findViewById(R.id.intrnl_mark_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<IntrnlmarkMll,IntrnlViewHolder>firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<IntrnlmarkMll, IntrnlViewHolder>(
                IntrnlmarkMll.class,
                R.layout.internal_makr_card,
                IntrnlViewHolder.class,
                reference) {
            @Override
            protected void populateViewHolder(IntrnlViewHolder viewHolder, IntrnlmarkMll model, int position) {

                viewHolder.setSbjctname(model.getSbjctname());
                viewHolder.setSsnlone(model.getSsnlone());
                viewHolder.setSsnltwo(model.getSsnltwo());
                viewHolder.setSsnlthree(model.getSsnlthree());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter1);
    }

    public static class IntrnlViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public IntrnlViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setSbjctname(String sbjctname) {
            TextView post_desc = mView.findViewById(R.id.text_sbjct);
            post_desc.setText(sbjctname);
        }

        public void setSsnlone(String ssnlone) {
            TextView post_desc = mView.findViewById(R.id.text_ssnlone);
            post_desc.setText(ssnlone);
        }

        public void setSsnltwo(String ssnltwo) {
            TextView post_desc = mView.findViewById(R.id.text_ssnltwo);
            post_desc.setText(ssnltwo);
        }

        public void setSsnlthree(String ssnlthree) {
            TextView post_desc = mView.findViewById(R.id.text_ssnlthree);
            post_desc.setText(ssnlthree);
        }


    }

}
