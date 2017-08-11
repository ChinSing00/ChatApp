package com.example.vogabond.chatapp.chatroom.fragment;

import android.view.View;

import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.session.module.ModuleProxy;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * Created by yang。先森 on 2017/8/8 0008.
 */

public class ChatRoomMessageFragment extends TFragment implements ModuleProxy {
    private View rootView;
    //modules

    private String roomId;

    public boolean onBackPressed() {
       return true;
    }

    public void init(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean sendMessage(IMMessage msg) {
        return false;
    }

    @Override
    public void onInputPanelExpand() {

    }

    @Override
    public void shouldCollapseInputPanel() {

    }

    @Override
    public boolean isLongClickEnabled() {
        return false;
    }
}
