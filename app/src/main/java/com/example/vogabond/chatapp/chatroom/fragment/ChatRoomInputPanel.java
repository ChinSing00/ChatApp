
package com.example.vogabond.chatapp.chatroom.fragment;

import android.view.View;

import com.netease.nim.uikit.session.actions.BaseAction;
import com.netease.nim.uikit.session.module.Container;
import com.netease.nim.uikit.session.module.input.InputPanel;
import com.netease.nimlib.sdk.chatroom.ChatRoomMessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.List;

/**
 * Created by yang。先森 on 2017/8/10 0010.
 */

public class ChatRoomInputPanel extends InputPanel{

    public ChatRoomInputPanel(Container container, View view, List<BaseAction> actions, boolean isTextAudioSwitchShow) {
        super(container, view, actions, isTextAudioSwitchShow);
    }

    public ChatRoomInputPanel(Container container, View view, List<BaseAction> actions) {
        super(container, view, actions);
    }
    protected IMMessage createTextMessage(String text){
        return ChatRoomMessageBuilder.createChatRoomTextMessage(container.account, text);
    }
}
