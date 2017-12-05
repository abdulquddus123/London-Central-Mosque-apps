package com.londoncentralmosque.Model;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class News1 {
    private String  newsDate;
    private String newsTitle;
    private String newsDescription;
    private String newsImage;
    public News1(String newsTitle,String newsImage,String newsDescription,String newsDate){
        this.newsTitle=newsTitle;
        this.newsImage=newsImage;
        this.newsDescription=newsDescription;
        this.newsDate=newsDate;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;

    }
}

