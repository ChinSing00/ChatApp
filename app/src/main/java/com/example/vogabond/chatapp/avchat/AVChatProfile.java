package com.example.vogabond.chatapp.avchat;

import com.example.vogabond.chatapp.MyCache;
import com.example.vogabond.chatapp.helper.DemoCache;
import com.example.vogabond.chatapp.avchat.activity.AVChatActivity;
import com.example.vogabond.chatapp.helper.common.infra.Handlers;
import com.netease.nimlib.sdk.avchat.model.AVChatData;

/**
 * Created by MSI on 2017/8/9.
 */

public class AVChatProfile {
    private final String TAG = "AVChatProfile";

    private boolean isAVChatting = false; // 是否正在音视频通话

    public static AVChatProfile getInstance() {
        return InstanceHolder.instance;
    }

    public boolean isAVChatting() {
        return isAVChatting;
    }

    public void setAVChatting(boolean chating) {
        isAVChatting = chating;
    }

    private static class InstanceHolder {
        public final static AVChatProfile instance = new AVChatProfile();
    }

    public void launchActivity(final AVChatData data, final int source) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 启动，如果 task正在启动，则稍等一下
                if (!MyCache.isMainTaskLaunching()) {
                    AVChatActivity.launch(MyCache.getContext(), data, source);
                } else {
                    launchActivity(data, source);
                }
            }
        };
        Handlers.sharedHandler(MyCache.getContext()).postDelayed(runnable, 200);
    }
}
