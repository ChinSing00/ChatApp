package com.example.vogabond.chatapp.chatroom.helper;


import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang。先森 on 2017/8/8 0008.
 */

public class ChatRoomMemberCache {
    private static final String TAG = "ChatRoomMemberCache";
    public static ChatRoomMemberCache getInstance() {
        return InstanceHolder.instance;
    }
    private Map<String,Map<String,ChatRoomMemberCache>> cache = new HashMap<>();

    public void clearRoomCache(String roomId) {

    }

    public void saveMyMember(ChatRoomMember chatRoomMember) {
    }

    public void clear() {
    }

    public void registerObservers() {
    }

    static class InstanceHolder{
        final static ChatRoomMemberCache instance = new ChatRoomMemberCache();
    }
}
