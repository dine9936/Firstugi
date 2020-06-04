package com.example.firstugi;

import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;


public class erp extends Fragment {
    private Toolbar toolbar,toolbarnoti;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CircleImageView circleImageView;
    private LinearLayout linearLayout,linearLayoutnoti;
    private ImageButton moreDetails,lessDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_erp,container,false);
        tabLayout = (TabLayout)view.findViewById(R.id.tab_erp);

        linearLayout = (LinearLayout)view.findViewById(R.id.mrdtlslayout);
        linearLayoutnoti = (LinearLayout)view.findViewById(R.id.notificationll);


        moreDetails = (ImageButton)view.findViewById(R.id.more_details);
        lessDetails = (ImageButton)view.findViewById(R.id.less_details);
        lessDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreDetails.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }
        });
        moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreDetails.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("Attendence"));
        tabLayout.addTab(tabLayout.newTab().setText("Marks"));
        tabLayout.addTab(tabLayout.newTab().setText("Fees"));
        tabLayout.addTab(tabLayout.newTab().setText("Library"));

        toolbar = (Toolbar)view.findViewById(R.id.toolbar_erp);
        toolbarnoti  =(Toolbar)view.findViewById(R.id.notitoolbar);
        toolbar.setTitle(null);
        ((Home) getActivity()).setSupportActionBar(toolbar);
        ((Home) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);

        toolbarnoti.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                linearLayoutnoti.setVisibility(View.GONE);
            }
        });
        final Toolbar toolbar = (Toolbar)view.findViewById(R.id.profile_toolbar);

        final CoordinatorLayout layout = (CoordinatorLayout)view.findViewById(R.id.profileaaa);
        appBarLayout = (AppBarLayout)view.findViewById(R.id.appbar);

        circleImageView = (CircleImageView)view.findViewById(R.id.toolbar_profilea);

        viewPager = (ViewPager)view.findViewById(R.id.erp_pager);

        final PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appBarLayout.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);




            }
        });
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appBarLayout.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
            }
        });
        return view;
    }
public class PagerAdapter extends FragmentPagerAdapter{
int numberTabs;
    public PagerAdapter(FragmentManager fm,int numberofTabs) {
        super(fm);
        this.numberTabs  = numberofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case  0:
                Attendence attendence = new Attendence();
                return attendence;
            case  1:
                Marks marks = new Marks();
                return marks;
            case  2:
                Fees fees = new Fees();
                return fees;
            case  3:
                Library library = new Library();
                return library;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_toll_item,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification:
                appBarLayout.setVisibility(View.GONE);
                viewPager.setVisibility(View.GONE);
                linearLayoutnoti.setVisibility(View.VISIBLE);
                return true;
        }
        return false;
    }
}
