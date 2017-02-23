package com.feicuiedu.okhttpdemo_0223;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.okHttpGet)
    public void gotoNetwork(View view) {
        switch (view.getId()) {
            case R.id.okHttpGet:

                // 简单的构建一个GET请求
                /**
                 * 1. 创建一个客户端：目的是发送请求
                 * 2. 构建请求：GET
                 * 3. 发送请求给服务器
                 */
                final OkHttpClient okHttpClient = new OkHttpClient();

                // 构造器模式创建请求
                final Request request = new Request.Builder()
                        // 请求方式:Get 不需要添加请求体
                        .get()

                        // 请求的url
                        .url("http://106.14.32.204/eshop/emobile/?url=/category")

                        // 不需要添加请求头，如果添加，服务器会当做无用资源，不做处理
                        .addHeader("zbc", "123")
                        .addHeader("123", "123")

                        .build();

                // 同步
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try {
//                            Response response = okHttpClient.newCall(request).execute();
//
//                            response.code();// 响应码
//                            response.body();// 响应体
//                            response.header("123");// 获取响应头
//                            Headers headers = response.headers();
//
//                            if (response.isSuccessful()){
//                                Log.i("TAG","respone"+response.code());
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

                // 异步
                okHttpClient.newCall(request).enqueue(new Callback() {

                    // 里面都是后台线程：不能做UI的操作
                    // 请求失败：网络未连接超时等
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("TAG","onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                            Log.i("TAG","respone"+response.code());

                    }
                });

                break;

        }
    }
}
