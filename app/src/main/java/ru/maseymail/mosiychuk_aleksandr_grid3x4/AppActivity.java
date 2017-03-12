package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class AppActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Context context;

    AppViewAdapter adapter;

    RecyclerView.LayoutManager recyclerViewLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        setContentView(R.layout.app_activity);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

       adapter = new AppViewAdapter(pm, packages);

        recyclerView.setAdapter(adapter);


    }
}