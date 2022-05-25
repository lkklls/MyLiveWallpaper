package com.example.mylivewallpaper.service;

/*
 * @Author:ouli
 * @Data:2022/5/25
 * @Time: 18:06
 * @Description:
 */

import android.service.dreams.DreamService;

import com.example.mylivewallpaper.R;

public  class ScreenSaverService extends DreamService {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // Exit dream upon user touch
        setInteractive(false);
        // Hide system UI
        setFullscreen(true);
        // Set the dream layout
        setContentView(R.layout.activity_screen_saver);
    }


}
