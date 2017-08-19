
package com.example.vogabond.chatapp.discovery.model;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    private List<Datum> mData;
    private String mStat;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getStat() {
        return mStat;
    }

    public void setStat(String stat) {
        mStat = stat;
    }

    @Override
    public String toString() {
        return "Result{" +
                "mData=" + mData +
                ", mStat='" + mStat + '\'' +
                '}';
    }
}
