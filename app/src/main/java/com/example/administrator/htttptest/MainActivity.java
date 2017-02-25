package com.example.administrator.htttptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//    private EditText edtNet;
    private Button btnLoad;
    private TextView tvTest;
    private ImageView imgView;
    private String net = "http://www.baidu.com/img/bd_logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
//        edtNet = (EditText) findViewById(R.id.edt_net);
        btnLoad = (Button) findViewById(R.id.btn_load);
        tvTest = (TextView) findViewById(R.id.tv_text);
        imgView = (ImageView) findViewById(R.id.imgview);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient mokHttpClient = new OkHttpClient();

                final Request request = new Request.Builder().url(net).build();

                Call call = mokHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
//                        final String res = response.body().string();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                tvTest.setText(res);
//                            }
//                        });

                        byte[] imgbyte = response.body().bytes();
                        final Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imgView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
            }
        });
    }
}
