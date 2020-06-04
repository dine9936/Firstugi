package com.example.firstugi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.firstugi.Models.NoticeMdll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notice extends AppCompatActivity {
    private Toolbar toolbar;
private DatabaseReference reference;
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        reference = FirebaseDatabase.getInstance().getReference().child("Notice");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_notice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        toolbar = (Toolbar)findViewById(R.id.toolbar_notice);
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

        FirebaseRecyclerAdapter<NoticeMdll,NoticeViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<NoticeMdll, NoticeViewHolder>(
                NoticeMdll.class,
                R.layout.notice_card,
                NoticeViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(NoticeViewHolder viewHolder, NoticeMdll model, int position) {
                viewHolder.setNotice(model.getNotice());
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNotice (String notice){
            TextView post_desc = mView.findViewById(R.id.text_notice);
            post_desc.setText(notice);
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
