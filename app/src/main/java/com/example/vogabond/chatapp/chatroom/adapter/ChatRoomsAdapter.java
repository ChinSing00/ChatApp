package com.example.vogabond.chatapp.chatroom.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseQuickAdapter;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.IRecyclerView;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;

/**
 * Created by yang。先森 on 2017/8/10 0010.
 */

public class ChatRoomsAdapter extends BaseQuickAdapter<ChatRoomInfo, BaseViewHolder>{
    public ChatRoomsAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.chat_room_item, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatRoomInfo item, int position, boolean isScrolling) {

    }



}
