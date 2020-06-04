package com.example.firstugi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.firstugi.Models.Thought;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Thoughts extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoughts);

        reference = FirebaseDatabase.getInstance().getReference().child("Thoughts");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_thought);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHasFixedSize(true);
        toolbar = (Toolbar)findViewById(R.id.toolbar_thoughts);
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

        FirebaseRecyclerAdapter<Thought,ThoughtViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Thought, ThoughtViewHolder>(
                Thought.class,
                R.layout.thought_card,
                ThoughtViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(ThoughtViewHolder viewHolder, Thought model, int position) {
                viewHolder.setThought(model.getThought());
                viewHolder.setWriter(model.getWriter());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ThoughtViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ThoughtViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setThought (String thought){
            TextView post_desc = mView.findViewById(R.id.text_thought);
            post_desc.setText(thought);
        }

        public void setWriter (String writer){
            TextView post_desc = mView.findViewById(R.id.writer);
            post_desc.setText(writer);
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
