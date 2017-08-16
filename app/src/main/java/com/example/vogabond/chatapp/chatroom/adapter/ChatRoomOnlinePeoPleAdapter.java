package com.example.vogabond.chatapp.chatroom.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.chatroom.widget.ChatRoomImageView;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseQuickAdapter;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;

import java.util.List;

/**
 * Created by yang。先森 on 2017/8/10 0010.
 */

public class ChatRoomOnlinePeoPleAdapter extends BaseQuickAdapter<ChatRoomMember, BaseViewHolder> {

    public ChatRoomOnlinePeoPleAdapter(RecyclerView recyclerView,List<ChatRoomMember> members) {
        super (recyclerView, R.layout.online_people_item,members);
    }
    @Override
    protected void convert(BaseViewHolder holder, ChatRoomMember member, int position, boolean isScrolling) {
        //idnetity image
        ImageView identityImage = holder.getView(R.id.identity_image);
        if (member.getMemberType() == MemberType.CREATOR){
            identityImage.setVisibility(View.VISIBLE);
            identityImage.setImageDrawable(holder.getContext().getResources().getDrawable(R.mipmap.master_icon));
        }else if (member.getMemberType()== MemberType.ADMIN){
            identityImage.setVisibility(View.VISIBLE);
            identityImage.setImageDrawable(holder.getContext().getResources().getDrawable(R.mipmap.admin_icon));
        }else {
            identityImage.setVisibility(View.GONE);
        }
        //head image
        ChatRoomImageView userHeadImag = holder.getView(R.id.user_head);
        userHeadImag.loadAvatarByUrl(member.getAvatar());

        //user name
        holder.setText(R.id.user_name, TextUtils.isEmpty(member.getNick()) ? "" : member.getNick());
    }
}
