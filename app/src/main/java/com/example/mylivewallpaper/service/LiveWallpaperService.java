package com.example.mylivewallpaper.service;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.example.mylivewallpaper.view.LiveWallpaperView;

/**
 * author: Coolspan
 * time: 2017/3/13 15:19
 * describe: 动态壁纸服务，此服务继承自系统服务，一般不出现异常，此服务不会退出
 */
public class LiveWallpaperService extends WallpaperService {

    @Override
    public Engine onCreateEngine() {
        return new MyEngine();
    }

    class MyEngine extends Engine {
        @Override
        public SurfaceHolder getSurfaceHolder() {
            return super.getSurfaceHolder();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.RED);
            holder.unlockCanvasAndPost(canvas);
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
        }
    }
}
