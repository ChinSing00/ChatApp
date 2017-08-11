package com.example.vogabond.chatapp.chatroom.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vogabond.chatapp.R;
import com.example.vogabond.chatapp.chatroom.DemoCache;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Created by yang。先森 on 2017/8/9 0009.
 */

public class ChatRoomHelper {
    private static final int[] imageRes = {R.mipmap.room_cover_36,R.mipmap.room_cover_37,R.mipmap.room_cover_49,
    R.mipmap.room_cover_50,R.mipmap.room_cover_57,R.mipmap.room_cover_58,R.mipmap.room_cover_64
            ,R.mipmap.room_cover_72};

    private static Map<String,Integer> roomCoverMap = new HashMap<>();
    private static int index = 0;

    public static void init(){
        ChatRoomMemberCache.getInstance().clear();
        ChatRoomMemberCache.getInstance().registerObservers();
    }
    public static void setCoveImage(String roomId, ImageView coverImage,boolean blur){
        if (roomCoverMap.containsKey(roomId)){
            blurCoverImage(blur,coverImage,roomCoverMap.get(roomId));
        }else {
            if (index >imageRes.length ){
                index = 0;
            }
            roomCoverMap.put(roomId,imageRes[index]);
            blurCoverImage(blur,coverImage,imageRes[index]);
            index++;
        }
    }

    private static void blurCoverImage(boolean blur,final ImageView imageView,final int resId) {
        final Context context = DemoCache.getContext();
        if (!blur){
            Glide.with(context).load(resId).into(imageView);
        }else {
            Glide.with(context).load(resId)
                    .bitmapTransform(new BlurTransformation(context,5))
                    .into(imageView);
        }
    }
}
