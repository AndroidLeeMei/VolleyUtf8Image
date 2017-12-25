package com.example.auser.volleyutf8image;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView img,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //從網路上抓取文章
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //下面這行會出現亂碼
//        final StringRequest request = new StringRequest("https://udn.com/rssfeed/news/2/6638?ch=news",
        //@Override StringRequest==>UTF8StringRequest
        final StringRequest request = new UTF8StringRequest("https://udn.com/rssfeed/news/2/6638?ch=news",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //從網路上放入圖片開始
        img=(ImageView)findViewById(R.id.imageView);
        final ImageRequest imageRequest = new ImageRequest("http://webneel.com/wallpaper/sites/default/files/images/01-2014/2-flower-wallpaper.preview.jpg"
                , new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                img.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
        queue.add(imageRequest);
        queue.start();
        //當檔案很大時,用picasso抓網路的資料
        img2=(ImageView)findViewById(R.id.imageView2);
//        Picasso.with(this).load("http://square.github.io/picasso/static/debug.png").into(img2);
        Picasso.with(this).load("https://attach.setn.com/newsimages/2015/09/24/344060-XXL.jpg").into(img2);

    }
}

