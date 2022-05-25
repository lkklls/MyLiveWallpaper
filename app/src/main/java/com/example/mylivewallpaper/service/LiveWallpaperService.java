package com.example.mylivewallpaper.service;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: Coolspan
 * time: 2017/3/13 15:19
 * describe: 动态壁纸服务，此服务继承自系统服务，一般不出现异常，此服务不会退出
 */
public class LiveWallpaperService extends WallpaperService {

    private MovieThread movieThread;
    private Movie movie;
    //缩放倍率
    private float mScaleX = 2;
    private float mScaleY = 2;
    //空间测量高度和宽度
    private int mMeasureHeight;
    private int mMeasureWidth;

    @Override
    public Engine onCreateEngine() {
        try {
            InputStream stream = getAssets().open("coffe.gif");
            movie = Movie.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MyEngine();
    }

    private void initParam(SurfaceHolder surfaceHolder) {
        movieThread = new MovieThread(surfaceHolder);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
    }

    class MyEngine extends Engine {
        @Override
        public SurfaceHolder getSurfaceHolder() {
            return super.getSurfaceHolder();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            initParam(surfaceHolder);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            mMeasureWidth = width;
            mMeasureHeight = height;
            movieThread.start();
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

    private class MovieThread extends Thread {
        private SurfaceHolder mHolder;

        public MovieThread(SurfaceHolder holder) {
            this.mHolder = holder;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            mScaleX = (float) mMeasureWidth / movie.width();
           mScaleY = (float) mMeasureHeight / movie.height();
            while (true) {
                canvas = mHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (mHolder) {
                        canvas.save();
                        canvas.scale(mScaleX, mScaleY);    //x为水平方向的放大倍数，y为竖直方向的放大倍数。
                        //绘制此gif的某一帧，并刷新本身
                        movie.draw(canvas, 0, 0);
                        //逐帧绘制图片(图片数量5)
                        // 1 2 3 4 5 6 7 8 9 10
                        // 1 2 3 4 0 1 2 3 4 0  循环
                        //Log.i(SurfaceTag, "run: "+ "time" + System.currentTimeMillis() % movie.duration());
                        movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
                        canvas.restore();
                        //结束锁定画图，并提交改变,画画完成(解锁)
                    }

                }
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
