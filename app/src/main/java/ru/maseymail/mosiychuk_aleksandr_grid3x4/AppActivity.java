package ru.maseymail.mosiychuk_aleksandr_grid3x4;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

public class AppActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Context context;
    private AppViewAdapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        setContentView(R.layout.app_activity);
        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(textWatcher);
        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        adapter = new AppViewAdapter(pm, packages);
        recyclerView.setAdapter(adapter);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            adapter.getFilter().filter(s.toString());
        }
    };
}