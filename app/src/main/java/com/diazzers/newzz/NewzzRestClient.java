package com.diazzers.newzz;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NewzzRestClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void getTopNewsList(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl("top-headlines?country=ru&pageSize=100&apiKey=223135b4b6a4437097149c808de9745e"), params, responseHandler);
    }

}
