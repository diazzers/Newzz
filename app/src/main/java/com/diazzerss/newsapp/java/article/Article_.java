package com.diazzerss.newsapp.java.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Article_ {

    @SerializedName("source")
    @Expose
    private Source sourceName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private Date publishedAt;

    public Article_(Source sourceName, String title, String url, String urlToImage, Date publishedAt) {
        this.sourceName = sourceName;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public Source getSourceName() {
        return sourceName;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article_ article_ = (Article_) o;

        if (!sourceName.equals(article_.sourceName)) return false;
        if (!title.equals(article_.title)) return false;
        if (!url.equals(article_.url)) return false;
        if (!urlToImage.equals(article_.urlToImage)) return false;
        return publishedAt.equals(article_.publishedAt);
    }

    @Override
    public int hashCode() {
        int result = sourceName.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + urlToImage.hashCode();
        result = 31 * result + publishedAt.hashCode();
        return result;
    }
}
