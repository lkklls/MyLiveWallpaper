package com.example.mylivewallpaper;

import static android.provider.Settings.ACTION_DREAM_SETTINGS;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.service.dreams.DreamService;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylivewallpaper.service.LiveWallpaperService;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                WallpaperManager wallpaperManager = WallpaperManager.getInstance(MainActivity.this);
                try {
                    wallpaperManager.setResource(R.drawable.ntf);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {

                try {
                    WallpaperManager.getInstance(MainActivity.this)
                            .clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(MainActivity.this, LiveWallpaperService.class));
                startActivity(intent);
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ACTION_DREAM_SETTINGS), 0);
                //PackageManager pm = getApplicationContext().getPackageManager();
                //Intent dreamIntent = new Intent(DreamService.SERVICE_INTERFACE);
                //List<ResolveInfo> resolveInfos = pm.queryIntentServices(dreamIntent, PackageManager.GET_META_DATA);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
