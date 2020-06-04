package com.example.firstugi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.firstugi.Models.Attndll;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class homea extends Fragment {
private Toolbar toolbar,toolbarnoti;

private TextView notice,noticea,noticeb;
    private RecyclerView recyclerViewnotice,recyclerViewevent,recyclerViewlife,recyclerViewplcmnts,notiRecycler;
    private DatabaseReference reference,referenceimage;
    private FirebaseDatabase database;
    private LinearLayout linearLayout,layoutThought;
    private ImageButton imageButton;
    private ViewFlipper viewFlipper;

    private FrameLayout frameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homea,container,false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("attendence");

        viewFlipper = (ViewFlipper)view.findViewById(R.id.view);
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    Intent myIntent = new Intent(getContext(), Placement.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    getContext().startActivity(myIntent);
                } else {
                    // Swap without transition
                    Intent intent = new Intent(getContext(),Placement.class);
                    startActivity(intent);
                }
            }
        });
        notice = (TextView)view.findViewById(R.id.notice);

        notice.setSelected(true);

notiRecycler = (RecyclerView)view.findViewById(R.id.noti_recycler);
notiRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
notiRecycler.setHasFixedSize(true);

linearLayout = (LinearLayout)view.findViewById(R.id.notificationll);
layoutThought = (LinearLayout)view.findViewById(R.id.thought_layout);

frameLayout = (FrameLayout)view.findViewById(R.id.notic);
frameLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            Intent myIntent = new Intent(getContext(), Notice.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getContext().startActivity(myIntent);
        } else {
            // Swap without transition
            Intent intent = new Intent(getContext(),Notice.class);
            startActivity(intent);
        }
    }
});
layoutThought.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            Intent myIntent = new Intent(getContext(), Thoughts.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getContext().startActivity(myIntent);
        } else {
            // Swap without transition
            Intent intent = new Intent(getContext(),Thoughts.class);
            startActivity(intent);
        }
    }
});

imageButton = (ImageButton)view.findViewById(R.id.about);
imageButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            Intent myIntent = new Intent(getContext(), About.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getContext().startActivity(myIntent);
        } else {
            // Swap without transition
            Intent intent = new Intent(getContext(),About.class);
            startActivity(intent);
        }
    }
});


        toolbar = (Toolbar)view.findViewById(R.id.toolbar_hom);
        toolbarnoti = (Toolbar)view.findViewById(R.id.notitoolbar);
        ((Home) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

toolbarnoti.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        toolbar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }
});
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Attndll, Attendence.AtndnceViewHolder>firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Attndll, Attendence.AtndnceViewHolder>(
                Attndll.class,
                R.layout.today_attendence_card,
                Attendence.AtndnceViewHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(Attendence.AtndnceViewHolder viewHolder, Attndll model, int position) {
                viewHolder.setSbjctname(model.getSbjctname());
            }
        };
        notiRecycler.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.home_toll_item,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                toolbar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                return true;
        }
        return false;

    }


}
