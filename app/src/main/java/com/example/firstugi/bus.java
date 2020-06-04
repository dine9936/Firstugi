package com.example.firstugi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class bus extends Fragment {





private Toolbar toolbar,toolbarnoti;
    private LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bus, container, false);




        linearLayout = (LinearLayout)view.findViewById(R.id.notificationll);

         toolbar = (Toolbar)view.findViewById(R.id.toolbar_bus);
         toolbarnoti = (Toolbar)view.findViewById(R.id.notitoolbar);
        ((Home)getActivity()).setSupportActionBar(toolbar);
        ((Home) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

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
