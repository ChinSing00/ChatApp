package com.example.vogabond.chatapp.helper.avchat;

/**
 * Created by MSI on 2017/8/9.
 */
//音频界面操作
public interface AVChatUIListener {
    void onHangUp();
    void onRefuse();
    void onReceive();
    void toggleMute();
    void toggleSpeaker();
    void toggleRecord();
    void videoSwitchAudio();
    void audioSwitchVideo();
    void switchCamera();
    void closeCamera();
}
