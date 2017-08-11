package com.example.vogabond.chatapp.session.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.example.vogabond.chatapp.R;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.netease.nim.uikit.session.module.Container;
import com.netease.nim.uikit.session.module.ModuleProxy;
import com.netease.nim.uikit.session.module.list.MessageListPanelEx;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * Created by Selet on 2017/8/7 0007.
 */

public class MSG_HistoryAcitivity extends UI implements ModuleProxy {
    private static final String EXTRA_DATA_ACCOUNT = "EXTRA_DATA_ACCOUNT";
    private static final String EXTRA_DATA_SESSION_TYPE = "EXTRA_DATA_SESSION_TYPE";

    private SessionTypeEnum sessionTypeEnum;
    private String account;
    private MessageListPanelEx messageListPanelEx;
    @Override
    public boolean sendMessage(IMMessage msg) {
        return false;
    }
    public static void start(Context context,String account,SessionTypeEnum sessionTypeEnum){
        Intent itt = new Intent();
        itt.putExtra(EXTRA_DATA_ACCOUNT,account);
        itt.putExtra(EXTRA_DATA_SESSION_TYPE,sessionTypeEnum);
        itt.setClass(context,MSG_HistoryAcitivity.class);
        context.startActivity(itt);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        messageListPanelEx.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        messageListPanelEx.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.message_history_activity, null);
        setContentView(rootView);
        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.message_history_query;
        setToolBar(R.id.toolbar, options);
        onParseIntent();
        Container container = new Container(this, account, sessionTypeEnum, this);
        messageListPanelEx = new MessageListPanelEx(container, rootView, true, true);
    }

    private void onParseIntent(){
        account = getIntent().getStringExtra(EXTRA_DATA_ACCOUNT);
        sessionTypeEnum = (SessionTypeEnum) getIntent().getSerializableExtra(
                EXTRA_DATA_SESSION_TYPE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (messageListPanelEx != null ){
            messageListPanelEx.onActivityResult(requestCode,resultCode,data);
        }
    }

}
