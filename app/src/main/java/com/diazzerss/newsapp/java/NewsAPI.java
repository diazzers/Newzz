package com.diazzerss.newsapp.java;

import com.diazzerss.newsapp.java.article.Article;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("top-headlines?country=ru&apiKey=e460b9e1d9b34ac49f7cd96e209c883b")
    public Single<Article> getArticles();

    @GET("everything?sortBy=publishedAt&apiKey=e460b9e1d9b34ac49f7cd96e209c883b")
    public Single<Article> getArticlesWithQuery(@Query("qInTitle") String query);

}
