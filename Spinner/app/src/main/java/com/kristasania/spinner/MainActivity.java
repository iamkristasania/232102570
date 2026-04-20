package com.kristasania.spinner;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.graphics.Color;
import android.widget.AdapterView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner _spinner1, _spinner2, _spinner3, _spinner4, _spinner5, _spinner6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initSpinner1();
        initSpinner2();
        initSpinner3();
        initSpinner4();
        initSpinner5();
        initSpinner6();
        initColorSpinner();
    }

    private void initSpinner1() {
        _spinner1 = findViewById(R.id.spinner1);

        List<String> nameList = new ArrayList<>();
        nameList.add("Gennaro");
        nameList.add("Pasquale");
        nameList.add("Nicola");
        nameList.add("Renato");
        nameList.add("Giorgio");

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        _spinner1.setAdapter(arrayAdapter1);
    }

    private void initSpinner2() {
        _spinner2 = findViewById(R.id.spinner2);

        List<String> numberList = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            numberList.add("Element " + i);
        }

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numberList);
        _spinner2.setAdapter(arrayAdapter2);
    }

    private void initSpinner3() {
        _spinner3 = findViewById(R.id.spinner3);

        List<String> numberList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            numberList.add("Element " + i);
        }

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numberList);
        _spinner3.setAdapter(arrayAdapter3);
    }

    private void initSpinner4() {
        _spinner4 = findViewById(R.id.spinner4);

        List<String> evenList = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            evenList.add("Element " + (i * 2));
        }

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, evenList);
        _spinner4.setAdapter(arrayAdapter4);
    }

    private void initSpinner5() {
        _spinner5 = findViewById(R.id.spinner5);
        List<String> primeList = new ArrayList<>();
        for (int j = 2; j <= 1000; j++) {
            if (isPrime(j)) {
                primeList.add("Element " + j);
            }
        }

        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, primeList);
        _spinner5.setAdapter(arrayAdapter5);
    }

    private void initSpinner6() {
        _spinner6 = findViewById(R.id.spinner6);

        PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfoList = pm.queryIntentActivities(intent, 0);
        List<String> appNames = new ArrayList<>();

        for (ResolveInfo ri : resolveInfoList) {
            String appName = ri.loadLabel(pm).toString();
            appNames.add(appName);
        }

        Collections.sort(appNames);

        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                appNames
        );

        _spinner6.setAdapter(arrayAdapter6);
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void initColorSpinner() {
        Spinner spinnerColors = findViewById(R.id.spinner_colors);
        // Find the main background layout using the ID from your XML
        View mainLayout = findViewById(R.id.main);

        // 1. Create a list of colors
        String[] colorNames = {"Default White", "Light Red", "Light Blue", "Light Green", "Dark Mode"};

        // 2. Set the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                colorNames
        );
        spinnerColors.setAdapter(adapter);

        // 3. Add the Listener to detect when a color is clicked
        spinnerColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the name of the color the user just clicked
                String selectedColor = colorNames[position];

                // Change the background color based on the choice
                switch (selectedColor) {
                    case "Light Red":
                        mainLayout.setBackgroundColor(Color.parseColor("#FFCDD2"));
                        break;
                    case "Light Blue":
                        mainLayout.setBackgroundColor(Color.parseColor("#BBDEFB"));
                        break;
                    case "Light Green":
                        mainLayout.setBackgroundColor(Color.parseColor("#C8E6C9"));
                        break;
                    case "Dark Mode":
                        mainLayout.setBackgroundColor(Color.parseColor("#333333"));
                        break;
                    default:
                        // Default White
                        mainLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // You must include this method, but you don't need to put anything inside it
            }
        });
    }
}