package com.kristasania.webviewangular;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        // Konfigurasi wajib untuk Angular
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Membersihkan cache agar selalu memuat versi terbaru (opsional tapi disarankan saat testing)
        webView.clearCache(true);

        // Menangani navigasi dan SSL Error
        webView.setWebViewClient(new WebViewClient() {
            @SuppressLint("WebViewClientOnReceivedSslError")
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Abaikan error sertifikat SSL
            }
        });

        // PENTING: Menambahkan WebChromeClient untuk melihat error Angular di Logcat Android Studio
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                // Ini akan mencetak error JavaScript Angular ke dalam tab Logcat
                Log.e("ANGULAR_ERROR", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId());
                return super.onConsoleMessage(consoleMessage);
            }
        });

        // Memuat URL
        webView.loadUrl("https://stmikpontianak.cloud/011100862/angular011100862");

        // Memperbaiki warning onBackPressed yang deprecated menggunakan OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish(); // Keluar dari aplikasi
                }
            }
        });
    }
}