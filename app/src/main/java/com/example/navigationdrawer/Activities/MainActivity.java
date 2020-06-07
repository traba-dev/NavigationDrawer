package com.example.navigationdrawer.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.navigationdrawer.Fragments.AlertFragment;
import com.example.navigationdrawer.Fragments.EmailFragment;
import com.example.navigationdrawer.Fragments.InfoFragment;
import com.example.navigationdrawer.R;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navView);
        setToolbar();
        setFragmentDefault();
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //inflate icon menu
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //Open NavigationDrawer
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setFragmentDefault() {
        MenuItem item = navigationView.getMenu().getItem(0);
        changeFragment(new EmailFragment(),item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.menu_mail:
                fragment = new EmailFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_alert:
                fragment = new AlertFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_info:
                fragment = new InfoFragment();
                fragmentTransaction = true;
                break;
            case R.id.menu_op1:
                Toast.makeText(MainActivity.this,"Opción 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_op2:
                Toast.makeText(MainActivity.this,"Opcion 2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_op3:
                Toast.makeText(MainActivity.this,"Opción 3",Toast.LENGTH_SHORT).show();
                break;
        }
        if (fragmentTransaction) {
           changeFragment(fragment,item);
           drawerLayout.closeDrawers();
        }

        return fragmentTransaction;
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {
        Toast.makeText(getApplicationContext(),"Opened",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        Toast.makeText(getApplicationContext(),"Closed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
