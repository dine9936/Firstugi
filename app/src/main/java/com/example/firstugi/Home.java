package com.example.firstugi;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
private BottomNavigationView bottomNavigationView;
private Toolbar toolbar;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        loadFragment(new homea());


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        //CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        //layoutParams.setBehavior(new BottomNavigationBehavior());


    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragmenta =null;
        switch (menuItem.getItemId()){
            case R.id.action_home:
                fragmenta = new homea();
                break;
            case R.id.action_bus:
                fragmenta = new bus();
                break;
            case R.id.action_erp:
                fragmenta = new erp();
                break;
            case R.id.action_student:
                fragmenta = new student();
                break;
            case R.id.action_profile:
                fragmenta = new Study();
                break;
        }
        return loadFragment(fragmenta);
    }
    private boolean loadFragment(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
