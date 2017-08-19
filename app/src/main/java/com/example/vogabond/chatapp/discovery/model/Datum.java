
package com.example.vogabond.chatapp.discovery.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Datum {

    @SerializedName("author_name")
    private String mAuthorName;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("date")
    private String mDate;
    @SerializedName("thumbnail_pic_s")
    private String mThumbnailPicS;
    @SerializedName("thumbnail_pic_s02")
    private String mThumbnailPicS02;
    @SerializedName("thumbnail_pic_s03")
    private String mThumbnailPicS03;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("uniquekey")
    private String mUniquekey;
    @SerializedName("url")
    private String mUrl;

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getThumbnailPicS() {
        return mThumbnailPicS;
    }

    public void setThumbnailPicS(String thumbnailPicS) {
        mThumbnailPicS = thumbnailPicS;
    }

    public String getThumbnailPicS02() {
        return mThumbnailPicS02;
    }

    public void setThumbnailPicS02(String thumbnailPicS02) {
        mThumbnailPicS02 = thumbnailPicS02;
    }

    public String getThumbnailPicS03() {
        return mThumbnailPicS03;
    }

    public void setThumbnailPicS03(String thumbnailPicS03) {
        mThumbnailPicS03 = thumbnailPicS03;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUniquekey() {
        return mUniquekey;
    }

    public void setUniquekey(String uniquekey) {
        mUniquekey = uniquekey;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
