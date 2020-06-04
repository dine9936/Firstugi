package com.example.firstugi;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstugi.Models.Attndll;
import com.example.firstugi.Models.Ttlattndcll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Attendence extends Fragment {
private DatabaseReference reference,reference2;
private RecyclerView recyclerView,recyclerView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendence,container,false);

        reference = FirebaseDatabase.getInstance().getReference().child("attendence");
        reference2 = FirebaseDatabase.getInstance().getReference().child("ttattendence");

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_attendence_today);
        recyclerView2 = (RecyclerView)view.findViewById(R.id.recycler_attendence_total);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
FirebaseRecyclerAdapter<Ttlattndcll,TotalatndnceViewHolder>firebaseRecyclerAdapter2 = new FirebaseRecyclerAdapter<Ttlattndcll, TotalatndnceViewHolder>(
        Ttlattndcll.class,
        R.layout.total_attendence_card,
        TotalatndnceViewHolder.class,
        reference2
) {
    @Override
    protected void populateViewHolder(TotalatndnceViewHolder viewHolder, Ttlattndcll model, int position) {
        viewHolder.setSbjctname(model.getSbjctname());
        viewHolder.setTtlclass(model.getTtlclass());
        viewHolder.setTtlpsnt(model.getTtlpsnt());
        viewHolder.setTtlabsnt(model.getTtlabsnt());
        viewHolder.setPrsntprcnt(model.getPrsntprcnt());
    }
};
        FirebaseRecyclerAdapter<Attndll,AtndnceViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attndll, AtndnceViewHolder>(
                Attndll.class,
                R.layout.today_attendence_card,
                AtndnceViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(AtndnceViewHolder viewHolder, Attndll model, int position) {
                viewHolder.setSbjctname(model.getSbjctname());

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView2.setAdapter(firebaseRecyclerAdapter2);
    }

    public static class AtndnceViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public AtndnceViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setSbjctname (String sbjctname){
            TextView post_desc = mView.findViewById(R.id.subject_attndnc);
            post_desc.setText(sbjctname);
        }


    }


    public static class TotalatndnceViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TotalatndnceViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setSbjctname (String sbjctname){
            TextView post_desc = mView.findViewById(R.id.sbjct_name);
            post_desc.setText(sbjctname);
        }
        public void setTtlclass (String ttlclass){
            TextView post_desc = mView.findViewById(R.id.total_class);
            post_desc.setText(ttlclass);
        }
        public void setTtlpsnt (String ttlpsnt){
            TextView post_desc = mView.findViewById(R.id.total_prsnt);
            post_desc.setText(ttlpsnt);
        }
        public void setTtlabsnt (String ttlabsnt){
            TextView post_desc = mView.findViewById(R.id.total_absnt);
            post_desc.setText(ttlabsnt);
        }
        public void setPrsntprcnt (String prsntprcnt){
            TextView post_desc = mView.findViewById(R.id.prsnt_prcnt);
            post_desc.setText(prsntprcnt);
        }


    }
}
