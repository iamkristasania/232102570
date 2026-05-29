package com.kristasania.recyclerviewb;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

// Ini adalah import yang benar untuk AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header;

public class AddMahasiswaActivity extends AppCompatActivity {

    private Button _saveButton;

    private EditText _alamatEditText, _namaEditText, _nimEditText, _tahunMasukEditText, _tanggalLahirEditText, _tempatLahirEditText;

    private Spinner _jenisKelaminSpinner, _jpSpinner, _statusNikahSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mahasiswa);

        initInputs();
        initSaveButton();
    }


    private void initSaveButton() {
        _saveButton = findViewById(R.id.saveButton);

        _saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alamat = _alamatEditText.getText().toString();
                String jenisKelamin = _jenisKelaminSpinner.getSelectedItem().toString();
                String jp = _jpSpinner.getSelectedItem().toString();
                String nama = _namaEditText.getText().toString();
                String nim = _nimEditText.getText().toString();
                String statusPernikahan = _statusNikahSpinner.getSelectedItem().toString();
                String tahunMasuk = _tahunMasukEditText.getText().toString();
                String tanggalLahir = _tanggalLahirEditText.getText().toString();
                String tempatLahir = _tempatLahirEditText.getText().toString();

                try {
                    alamat = URLEncoder.encode(alamat, StandardCharsets.UTF_8.name());
                    jenisKelamin = URLEncoder.encode(jenisKelamin, StandardCharsets.UTF_8.name());
                    jp = URLEncoder.encode(jp, StandardCharsets.UTF_8.name());
                    nama = URLEncoder.encode(nama, StandardCharsets.UTF_8.name());
                    nim = URLEncoder.encode(nim, StandardCharsets.UTF_8.name());
                    statusPernikahan = URLEncoder.encode(statusPernikahan, StandardCharsets.UTF_8.name());
                    tanggalLahir = URLEncoder.encode(tanggalLahir, StandardCharsets.UTF_8.name());
                    tempatLahir = URLEncoder.encode(tempatLahir, StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String url = "https://stmikpontianak.cloud/011100862/tambahMahasiswa.php" +
                        "?nim=" + nim +
                        "&nama=" + nama +
                        "&jenisKelamin=" + jenisKelamin +
                        "&tempatLahir=" + tempatLahir +
                        "&tanggalLahir=" + tanggalLahir +
                        "&alamat=" + alamat +
                        "&jp=" + jp +
                        "&statusPernikahan=" + statusPernikahan +
                        "&tahunMasuk=" + tahunMasuk;


                AsyncHttpClient ahc = new AsyncHttpClient();

                ahc.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.d("*tw*", new String(responseBody));

                        Gson g = new Gson();
                        String responseString = new String(responseBody);
                        Map<String, String> responseMap = g.fromJson(responseString, new TypeToken<Map<String, String>>(){}.getType());
                        String status = responseMap.get("status");

                        if (status != null && status.equals("ok")) {
                            String message = responseMap.get("message");
                            new AlertDialog.Builder(AddMahasiswaActivity.this)
                                    .setTitle("Berhasil")
                                    .setMessage(message)
                                    .show();

                        } else {
                            String errorMessage = responseMap.get("message");
                            new AlertDialog.Builder(AddMahasiswaActivity.this)
                                    .setTitle("Error")
                                    .setMessage(errorMessage)
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void initInputs() {
        _alamatEditText = findViewById(R.id.alamatEditText);
        _jenisKelaminSpinner = findViewById(R.id.jenisKelaminSpinner);
        _jpSpinner = findViewById(R.id.jpSpinner);
        _namaEditText = findViewById(R.id.namaEditText);
        _nimEditText = findViewById(R.id.nimEditText);
        _statusNikahSpinner = findViewById(R.id.statusNikahSpinner);
        _tahunMasukEditText = findViewById(R.id.tahunMasukEditText);
        _tanggalLahirEditText = findViewById(R.id.tanggalLahirEditText);
        _tempatLahirEditText = findViewById(R.id.tempatLahirEditText);
    }
}