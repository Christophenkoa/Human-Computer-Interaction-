package com.example.bepresent.appLocker;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bepresent.R;

import java.util.ArrayList;
import java.util.List;

public class LockAppActivity extends AppCompatActivity {



    private List<Model> appList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListofAppAdapter mAdapter;


    Apply_password_on_AppDatabase db = new Apply_password_on_AppDatabase(this);
    Password_Database pass_db = new Password_Database(this);

    List<String> lock = new ArrayList<>();

    String pass="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lockapp_activity);



        Cursor res = db.getAllData();
        Cursor res2 = pass_db.getAllData();


        while (res.moveToNext()) {
            lock.add(res.getString(0));

        }

        while (res2.moveToNext()) {

            pass = res2.getString(0);
        }




        recyclerView = (RecyclerView) findViewById(R.id.installed_app_list);

        mAdapter = new ListofAppAdapter(appList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getInstalledApps();
        mAdapter.notifyDataSetChanged();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {


            @Override
            public void onClick(View view, int position) {

                final Model model = appList.get(position);
                if(model.getLocked() == R.drawable.lock){
                    db.deleteData(model.getPackage_name());
                    Toast.makeText(LockAppActivity.this,   "App UnLocked", Toast.LENGTH_LONG).show();
                    model.setLocked(R.drawable.unlock);
                }
                else{
                    db.insertData(model.getPackage_name(),pass);
                    Toast.makeText(LockAppActivity.this,   "App Lock Successfully ", Toast.LENGTH_LONG).show();
                    model.setLocked(R.drawable.lock);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        })
        );




    }




    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);

        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

          System.out.println("size = "+packs.size());
            if(lock.contains(packs.get(i).packageName)){

                if ((isSystemPackage(p) == false)) {
                    String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                    appList.add(new Model(appName, icon,R.drawable.lock,packs.get(i).packageName) );
                }

            }

            else{
                if ((isSystemPackage(p) == false)) {
                    String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                    appList.add(new Model(appName, icon,R.drawable.unlock,packs.get(i).packageName) );
                }
            }





        }
        return res;
    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(LockAppActivity.this,MainActivity.class));
        finish();

    }
}
