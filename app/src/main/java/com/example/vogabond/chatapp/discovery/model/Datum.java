
package com.example.vogabond.chatapp.discovery.model;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Datum {

    private String Author_Name;
    private String Category;
    private String Date;
    private String Thumbnail_Pic_S;
    private String Thumbnail_PicS_02;
    private String Thumbnail_PicS_03;
    private String Title;
    private String Uniquekey;
    private String Url;

    @Override
    public String toString() {
        return "Datum{" +
                "AuthorName='" + Author_Name + '\'' +
                ", Category='" + Category + '\'' +
                ", Date='" + Date + '\'' +
                ", Thumbnail_Pic_S='" + Thumbnail_Pic_S + '\'' +
                ", Thumbnail_PicS_02='" + Thumbnail_PicS_02 + '\'' +
                ", Thumbnail_PicS_03='" + Thumbnail_PicS_03 + '\'' +
                ", Title='" + Title + '\'' +
                ", Uniquekey='" + Uniquekey + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }

    public String getAuthor_Name() {
        return Author_Name;
    }

    public void setAuthor_Name(String author_Name) {
        Author_Name = author_Name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getThumbnail_Pic_S() {
        return Thumbnail_Pic_S;
    }

    public void setThumbnail_Pic_S(String thumbnail_Pic_S) {
        Thumbnail_Pic_S = thumbnail_Pic_S;
    }

    public String getThumbnail_PicS_02() {
        return Thumbnail_PicS_02;
    }

    public void setThumbnail_PicS_02(String thumbnail_PicS_02) {
        Thumbnail_PicS_02 = thumbnail_PicS_02;
    }

    public String getThumbnail_PicS_03() {
        return Thumbnail_PicS_03;
    }

    public void setThumbnail_PicS_03(String thumbnail_PicS_03) {
        Thumbnail_PicS_03 = thumbnail_PicS_03;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUniquekey() {
        return Uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        Uniquekey = uniquekey;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
