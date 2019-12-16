package com.diazzers.newzz.pojo;

import java.util.Date;

public class Article {
    private String title;
    private String url;
    private String urlToImage;
    private Date publishedAt;
    private String sourceName;

    public Article(String title, String url, String urlToImage, Date publishedAt, String sourceName) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.sourceName = sourceName;
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

    public String getSourceName() {
        return sourceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!title.equals(article.title)) return false;
        if (!url.equals(article.url)) return false;
        if (!urlToImage.equals(article.urlToImage)) return false;
        if (!publishedAt.equals(article.publishedAt)) return false;
        return sourceName.equals(article.sourceName);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + urlToImage.hashCode();
        result = 31 * result + publishedAt.hashCode();
        result = 31 * result + sourceName.hashCode();
        return result;
    }
}
