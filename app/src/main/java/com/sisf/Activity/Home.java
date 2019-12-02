package com.sisf.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import androidx.navigation.ui.AppBarConfiguration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_slider;
import com.sisf.R;
import com.sisf.Utils.FixedSpeedScroller;
import com.sisf.Utils.ZoomOutPageTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<String> language;
    int Select_course_type;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3,
            R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3};


    boolean Select_course=false;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initilize all weidgets
        mPager = (ViewPager) findViewById(R.id.pager);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        spinner = (Spinner) navigationView.getMenu().findItem(R.id.nav_course).getActionView();

        //set navigation bar in item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.nav_story:
                        startActivity(new Intent(Home.this,Story.class));
                        Toast.makeText(Home.this, "Our Story",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_course:
                      //  startActivity(new Intent(Home.this,Course.class).putExtra("Select_course_type",Select_course_type));
                        Toast.makeText(Home.this, "Select Course",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_roots:
                        startActivity(new Intent(Home.this,Roots.class));
                        Toast.makeText(Home.this, "Roots",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_faq:
                        startActivity(new Intent(Home.this,FAQ.class));
                        Toast.makeText(Home.this, "FAQ",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_contactus:
                        startActivity(new Intent(Home.this,ContactUs.class));
                        Toast.makeText(Home.this, "Contactus",Toast.LENGTH_SHORT).show();break;
                    case R.id.nav_register:
                        startActivity(new Intent(Home.this,Register.class));
                        Toast.makeText(Home.this, "Contactus",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }

                drawer.closeDrawers();
                return true;

            }
        });


        //set All coures type in navigationbar
        Set_coursetype_nav();



        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {};

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //set Image slider
        init();

    }


    private void Set_coursetype_nav() {

        language = new ArrayList<String>();
        language.add("Select Course type  ");
        language.add("ACCA");
        language.add("ACCA PRO");
        language.add("iGRAD in Finance");
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.layout_text_course,language));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             //   Toast.makeText(Home.this,language.get(position),Toast.LENGTH_SHORT).show();
                if (position!=0)
                {
                    Select_course=true;
                    Select_course_type=position-1;
                    startActivity(new Intent(Home.this,Course.class).putExtra("Select_course_type",Select_course_type));
                    spinner.setSelection(0);
                }
                /*else if (Select_course_type!=0)
                {
                    Select_course=true;
                    Select_course_type=position;
                    startActivity(new Intent(Home.this,Course.class).putExtra("Select_course_type",Select_course_type));
                }else if (Select_course)
                {   Select_course=true;
                    Select_course_type=position;
                    startActivity(new Intent(Home.this,Course.class).putExtra("Select_course_type",Select_course_type));
                }*/

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_login:
                Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"SignUp",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager.setAdapter(new Adapter_slider(Home.this,ImagesArray));
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mPager.getContext(), mScroller);
            // scroller.setFixedDuration(5000);
            mScroller.set(mPager, scroller);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == IMAGES.length) {
                    currentPage = 0;
                }
                if (currentPage==0)
                {   currentPage=currentPage+1;
                    mPager.setCurrentItem(currentPage, false);
                }else{
                    currentPage=currentPage+1;
                    mPager.setCurrentItem(currentPage, true);
                }
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 3000);

    }

    public void Button_fb(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/sisfeducation"));
        startActivity(browserIntent);
    }

    public void Button_insta(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/sisfeducation"));
        startActivity(browserIntent);
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
