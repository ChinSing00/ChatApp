package com.example.vogabond.chatapp.chatroom.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.R;
import com.netease.nim.uikit.common.ui.imageview.CircleImageView;

/**
 * Created by yang。先森 on 2017/8/10 0010.
 */

public class ChatRoomImageView extends CircleImageView{
    private static final int DEFAULT_THUMB_SIZE = (int) NimUIKit.getContext().getResources().getDimension(R.dimen.avatar_max_size);

    public ChatRoomImageView(Context context) {
        super(context);
    }

    public ChatRoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChatRoomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void loadAvatarByUrl(String url){
        loadAvatar(url, DEFAULT_THUMB_SIZE);
    }

    private void loadAvatar(final String url, final int thumbSize) {
        Glide.with(getContext().getApplicationContext())
                .load(url).asBitmap().centerCrop()
                .placeholder(NimUIKit.getUserInfoProvider().getDefaultIconResId())
                .error(NimUIKit.getUserInfoProvider().getDefaultIconResId())
                .override(thumbSize, thumbSize) .into(this);
    }
}
