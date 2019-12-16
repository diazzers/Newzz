package com.diazzers.newzz.ui.article;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzers.newzz.NewzzRestClient;
import com.diazzers.newzz.pojo.Article;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class ArticleViewModel extends ViewModel {
    private ArrayList<Article> articlesList;

    public MutableLiveData<ArrayList<Article>> articlesListLiveData;

    public ArticleViewModel() {
        articlesListLiveData = new MutableLiveData<>();
    }

    public void loadData() {

        NewzzRestClient.getTopNewsList(
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            articlesList = new ArrayList<>();

                            for (int i = 0; i < response.getJSONArray("articles").length(); i++) {
                                String title = response.getJSONArray("articles").getJSONObject(i).getString("title");
                                String url = response.getJSONArray("articles").getJSONObject(i).getString("url");
                                String urlToImage = response.getJSONArray("articles").getJSONObject(i).getString("urlToImage");

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                                Date publishedAt = null;
                                try {
                                    publishedAt = sdf.parse(response.getJSONArray("articles").getJSONObject(i).getString("publishedAt"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                String sourceName = response.getJSONArray("articles").getJSONObject(i).getJSONObject("source").getString("name");


                                Article article = new Article(title, url, urlToImage, publishedAt, sourceName);
                                articlesList.add(i, article);

                            }

                            articlesListLiveData.postValue(articlesList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }
}
