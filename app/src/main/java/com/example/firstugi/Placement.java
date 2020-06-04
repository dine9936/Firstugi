package com.example.firstugi;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstugi.Models.CompnyMdll;
import com.example.firstugi.Models.PlacedStMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Placement extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerViewStudent,recyclerViewCompany;
    private DatabaseReference reference,referencecom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        reference = FirebaseDatabase.getInstance().getReference().child("Company").child("Airtel");
        recyclerViewStudent = (RecyclerView)findViewById(R.id.selected_students);
        recyclerViewStudent.setHasFixedSize(true);
        recyclerViewStudent.setLayoutManager(new LinearLayoutManager(this));


        referencecom = FirebaseDatabase.getInstance().getReference().child("CompanyList");
        recyclerViewCompany = (RecyclerView)findViewById(R.id.company_recycler);
        recyclerViewCompany.setHasFixedSize(true);
        recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        toolbar = (Toolbar)findViewById(R.id.toolbar_placement);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PlacedStMdll,PlacedStuViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PlacedStMdll, PlacedStuViewHolder>(
                PlacedStMdll.class,
                R.layout.place_student_card,
                PlacedStuViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(PlacedStuViewHolder viewHolder, PlacedStMdll model, int position) {
                viewHolder.setBranch(model.getBranch());
                viewHolder.setCollegename(model.getCollegename());
                viewHolder.setCompanynames(model.getCompanyname());
                viewHolder.setCourse(model.getCourse());
                viewHolder.setName(model.getName());
                viewHolder.setImage(model.getImage(),Placement.this);
            }
        };
        recyclerViewStudent.setAdapter(firebaseRecyclerAdapter);

        final FirebaseRecyclerAdapter<CompnyMdll,CompanyViewHolder>firebaseRecyclerAdapter1 = new FirebaseRecyclerAdapter<CompnyMdll, CompanyViewHolder>(
                CompnyMdll.class,
                R.layout.company_card,
                CompanyViewHolder.class,
                referencecom
        ) {
            @Override
            protected void populateViewHolder(CompanyViewHolder viewHolder, CompnyMdll model, int position) {
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(model.getImage(),Placement.this);
                viewHolder.setMonth(model.getMonth());
                viewHolder.setYear(model.getYear());
                viewHolder.setName(model.getName());
            }
        };

    recyclerViewCompany.setAdapter(firebaseRecyclerAdapter1);
    }

    public static class PlacedStuViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public PlacedStuViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setBranch (String branch){
            TextView post_desc = mView.findViewById(R.id.student_branch);
            post_desc.setText(branch);
        }
        public void setCompanynames (String ttlclass){
            TextView post_desc = mView.findViewById(R.id.company_name);
            post_desc.setText(ttlclass);
        }
        public void setCourse (String ttlpsnt){
            TextView post_desc = mView.findViewById(R.id.student_course);
            post_desc.setText(ttlpsnt);
        }
        public void setName (String ttlabsnt){
            TextView post_desc = mView.findViewById(R.id.student_name);
            post_desc.setText(ttlabsnt);
        }
        public void setCollegename (String prsntprcnt){
            TextView post_desc = mView.findViewById(R.id.student_college);
            post_desc.setText(prsntprcnt);
        }
        public void setImage (String prsntprcnt, Context context){
            ImageView post_desc = mView.findViewById(R.id.student_image);
            Picasso.with(context).load(prsntprcnt).into(post_desc);
        }


    }



    public static class CompanyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDate (String branch){
            TextView post_desc = mView.findViewById(R.id.date);
            post_desc.setText(branch);
        }
        public void setMonth (String ttlclass){
            TextView post_desc = mView.findViewById(R.id.month);
            post_desc.setText(ttlclass);
        }
        public void setYear (String ttlpsnt){
            TextView post_desc = mView.findViewById(R.id.year);
            post_desc.setText(ttlpsnt);
        }
        public void setName (String ttlabsnt){
            TextView post_desc = mView.findViewById(R.id.company_name);
            post_desc.setText(ttlabsnt);
        }

        public void setImage (String prsntprcnt, Context context){
            ImageView post_desc = mView.findViewById(R.id.company_image);
            Picasso.with(context).load(prsntprcnt).into(post_desc);
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
