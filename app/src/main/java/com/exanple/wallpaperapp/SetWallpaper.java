package com.exanple.wallpaperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class SetWallpaper extends AppCompatActivity {
    Intent intent;
    ImageView i ;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());


        i = findViewById(R.id.finalImage);
        set=findViewById(R.id.set);
        intent=getIntent();

        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(i);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)i.getDrawable()).getBitmap();
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}