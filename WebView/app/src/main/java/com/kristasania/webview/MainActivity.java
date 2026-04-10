package com.kristasania.webview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText _urlEditText;
    private WebView _webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _urlEditText = findViewById(R.id.urlEditText);
        _webView1 = findViewById(R.id.webView1);
    }

    public void tampilkanButton_onClick(View view) {
        String url = _urlEditText.getText().toString().trim();

        // Krista Sania (232102570) tgl 10/04/2026
        if (!url.startsWith("https://")) {
            url = "https://" + url;
            _urlEditText.setText(url);
            _urlEditText.setSelection(url.length());
        }

        _webView1.setWebViewClient(new WebViewClient());
        _webView1.loadUrl(url);
    }

    public void shareButton_onClick(View view) {
        // Krista Sania (232102570) Tanggal 10/04/2026
        String currentUrl = _webView1.getUrl();

        if (currentUrl != null && !currentUrl.isEmpty()) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("URL Berhasil Disalin", currentUrl);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "Link disalin ke clipboard", Toast.LENGTH_SHORT).show();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, currentUrl);

            startActivity(Intent.createChooser(shareIntent, "Bagikan Link Melalui:"));
        } else {
            Toast.makeText(this, "Tidak ada link untuk dibagikan", Toast.LENGTH_SHORT).show();
        }
    }
}