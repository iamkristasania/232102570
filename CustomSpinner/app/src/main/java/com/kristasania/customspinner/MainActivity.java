package com.kristasania.customspinner;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner _spinner1;
    private Spinner spinnerApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _spinner1 = findViewById(R.id.spinner1);

        List<String> negaraList = new ArrayList<>();
        negaraList.add("Indonesia");
        negaraList.add("France");
        negaraList.add("Korea");
        negaraList.add("USA");
        negaraList.add("England");

        NegaraAdapter na = new NegaraAdapter(this, negaraList);
        _spinner1.setAdapter(na);

        // Juan Winardi Toda (232102624)
        spinnerApps = findViewById(R.id.spinnerApps);

        PackageManager pm = getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);

        List<ApplicationInfo> userApps = new ArrayList<>();

        for (ResolveInfo info : resolveInfos) {
            userApps.add(info.activityInfo.applicationInfo);
        }

        userApps.sort((ApplicationInfo a, ApplicationInfo b) -> {
            String nameA = pm.getApplicationLabel(a).toString();
            String nameB = pm.getApplicationLabel(b).toString();
            return nameA.compareToIgnoreCase(nameB);
        });

        AppAdapter appAdapter = new AppAdapter(this, userApps);
        spinnerApps.setAdapter(appAdapter);

        spinnerApps.setDropDownWidth(ViewGroup.LayoutParams.MATCH_PARENT);
    }
}