package com.diazzerss.newsapp.java.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("name")
    @Expose
    public String sourceName;

    public Source(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Source source = (Source) o;

        return sourceName.equals(source.sourceName);
    }

    @Override
    public int hashCode() {
        return sourceName.hashCode();
    }
}
