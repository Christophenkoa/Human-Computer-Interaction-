package com.example.bepresent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.bepresent.appLocker.MainActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    BePresentOpenHelper dbHelper;
    SQLiteDatabase db;
    static Account mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        dbHelper = new BePresentOpenHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String receiveEmail = intent.getStringExtra(LoginForm.emailTransaction);
        mAccount = Account.getInstance();

        mAccount = getUser(receiveEmail);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_lock_app :
                startActivity(new Intent(MainActivity1.this , MainActivity.class));
                break;
            case R.id.nav_events_available :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UpcomingEventsFragment()).commit();
                break;
            case R.id.nav_achievements :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AchievementsFragment()).commit();
                break;
            case R.id.nav_break_moments :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BreakPeriodFragment()).commit();
                break;
            case R.id.nav_profile :
                startActivity(new Intent(MainActivity1.this , Profile.class));
                break;
            case R.id.nav_share :
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public Account getUser(String email) {
        Account userAccount = Account.getInstance();
        db = dbHelper.getReadableDatabase();
        try {

            Cursor c = dbHelper.searchUsers(email, db);

            if (c != null ) {

                    if(c.moveToFirst()) {

                        userAccount.setEmail(email);
                        String fullName = c.getString(0);
                        userAccount.setFullName(fullName);
                        String userName = c.getString(1);
                        userAccount.setUserName(userName);
                        String userLocation = c.getString(2);
                        userAccount.setLocation(userLocation);

                    }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            dbHelper.close();
            return userAccount;
        }
    }
}