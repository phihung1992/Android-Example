package com.nac.mvpm.model;

/**
 * Created by Admin on 13/05/2017.
 */
public class NewsEntity {
    private String linkImg, title, date, linkNews;

    public NewsEntity(String linkImg, String title, String date, String linkNews) {
        this.linkImg = linkImg;
        this.title = title;
        this.date = date;
        this.linkNews = linkNews;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLinkNews() {
        return linkNews;
    }
}
