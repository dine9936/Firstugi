package com.example.firstugi;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class Subject extends AppCompatActivity {





private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);




        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("subjectNm");

        tabLayout = (TabLayout)findViewById(R.id.tab_subject);
        tabLayout.addTab(tabLayout.newTab().setText("Notes"));
        tabLayout.addTab(tabLayout.newTab().setText("Others"));
        toolbar = (Toolbar)findViewById(R.id.toolbar_subject);

        toolbar.setTitle(message);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
       final ViewPager viewPager = (ViewPager)findViewById(R.id.subject_page);

       final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());


        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        int numberTabs;
        public PagerAdapter(FragmentManager fm, int numberofTabs) {
            super(fm);
            this.numberTabs  = numberofTabs;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){

                case  0:
                    Notes notes = new Notes();
                    return notes;
                case  1:
                    Others others = new Others();
                    return others;

                default:
                    return null;

            }

        }

        @Override
        public int getCount() {
            return numberTabs;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
