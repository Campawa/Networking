package com.example.chelsi.networking;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void makeRequestWithOkHttp(String url) {
            OkHttpClient client = new OkHttpClient();   // 1
            Request request = new Request.Builder().url(url).build();  // 2

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(DownloadManager.Request request, IOException e) { // 3
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    final String result = response.body().string();  // 4

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // perform some ui work with `result`  // 5
                                TextView tv = (TextView) findViewById(R.id.text_view);
                                tv.setText(result);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }
}
