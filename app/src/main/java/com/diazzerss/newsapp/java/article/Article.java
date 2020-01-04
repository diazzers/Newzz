package com.diazzerss.newsapp.java.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Article {

    @SerializedName("articles")
    @Expose
    public ArrayList<Article_> articles;

}