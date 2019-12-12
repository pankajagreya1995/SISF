package com.sisf.Activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.sisf.Adapter.Adapter_slider;
import com.sisf.App_Controller;
import com.sisf.R;
import com.sisf.Utils.App_Utils;
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
    private static final Integer[] IMAGES = {R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3};

    boolean Select_course = false;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    Spinner spinner;
    TextView header_name;
    NavigationView navigationView;
    TabLayout tabLayout;
    Timer swipeTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //initilize all weidgets
        mPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tab_indictor);
        tabLayout.setupWithViewPager(mPager, true);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        header_name = headerView.findViewById(R.id.txt_header_name);
        if (App_Controller.SharedPre_getDefaults(App_Utils.USER_NAME, Home.this) != null) {
            String getname=App_Controller.SharedPre_getDefaults(App_Utils.USER_NAME, Home.this);
            String name=getname.substring(0, 1).toUpperCase() + getname.substring(1);
            header_name.setText("Welcome "+name);
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_home_drawer_logout);
        } else {
            header_name.setText("");
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_home_drawer);
        }

        //set navigation bar in item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_story:
                        startActivity(new Intent(Home.this, Story.class));
                  //      Toast.makeText(Home.this, "Our Story", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_course:
                        //  startActivity(new Intent(Home.this,Course.class).putExtra("Select_course_type",Select_course_type));
                        Toast.makeText(Home.this, "Select Course", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_roots:
                        startActivity(new Intent(Home.this, Roots.class));
                   //     Toast.makeText(Home.this, "Roots", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_faq:
                        startActivity(new Intent(Home.this, FAQ.class));
                        Toast.makeText(Home.this, "FAQ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_quiz:
                        if (App_Controller.SharedPre_getDefaults(App_Utils.USER_NAME, Home.this) != null) {
                            startActivity(new Intent(Home.this, Home_Quiz.class));
                         //   Toast.makeText(Home.this, "Quiz", Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            swipeTimer.cancel();
                            startActivity(new Intent(Home.this, Login.class));
                            Toast.makeText(Home.this, "Please login", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    case R.id.nav_contactus:
                        startActivity(new Intent(Home.this, ContactUs.class));
                        Toast.makeText(Home.this, "Contactus", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                        Show_logout_Dialog();
                        break;
                    case R.id.nav_login:
                        swipeTimer.cancel();
                        startActivity(new Intent(Home.this, Login.class));
                    //    Toast.makeText(Home.this, "Login", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                drawer.closeDrawers();
                return true;

            }
        });


        //set All coures type in navigationbar
        Set_coursetype_nav();


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
        };
        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //set Image slider
        init();

    }

    private void Show_logout_Dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Home.this);
        dialog.setTitle("Alert!");
        dialog.setIcon(R.drawable.app_logo);
        dialog.setMessage("Are you sure you want to logout?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Home.this, "Successfully logout.", Toast.LENGTH_SHORT).show();
                App_Controller.Sharedpre_Remove(Home.this);
                header_name.setText("");
                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.activity_home_drawer);
                Set_coursetype_nav();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }


    private void Set_coursetype_nav() {
        spinner = (Spinner) navigationView.getMenu().findItem(R.id.nav_course).getActionView();
        language = new ArrayList<String>();
        language.add("Select Course type  ");
        language.add("ACCA");
        language.add("ACCA PRO");
        language.add("iGRAD in Finance");
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_text_course, language));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    Select_course = true;
                    Select_course_type = position - 1;
                    startActivity(new Intent(Home.this, Course.class).putExtra("Select_course_type", Select_course_type));
                    spinner.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    /*
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
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager.setAdapter(new Adapter_slider(Home.this, ImagesArray));
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
                }if (currentPage == 0) {
                    mPager.setCurrentItem(currentPage, false);
                    currentPage = currentPage + 1;
                } else {
                    mPager.setCurrentItem(currentPage, true);
                    currentPage = currentPage + 1;
                }
            }
        };

            swipeTimer = new Timer();
            //Start new Timer
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
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
