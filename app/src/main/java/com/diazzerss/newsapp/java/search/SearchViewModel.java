package com.diazzerss.newsapp.java.search;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzerss.newsapp.java.NetworkService;
import com.diazzerss.newsapp.java.SingleLiveEvent;
import com.diazzerss.newsapp.java.article.Article_;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends ViewModel {

    private Disposable disposable1;

    private ArrayList<Article_> articlesList;

    public MutableLiveData<ArrayList<Article_>> articlesListLiveData;
    public SingleLiveEvent<String> openArticleFragment;
    public MutableLiveData<Boolean> loadingLiveData;


    public SearchViewModel() {
        articlesListLiveData = new MutableLiveData<>();
        openArticleFragment = new SingleLiveEvent<>();
        loadingLiveData = new MutableLiveData<>();

    }


    public void loadArticlesWithQuery(String query) {

        disposable1 =
                NetworkService
                        .getInstance()
                        .getNewsAPI()
                        .getArticlesWithQuery(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(article -> {
                            articlesList = new ArrayList<>();
                            articlesList = article.articles;
                            articlesListLiveData.postValue(articlesList);

                        }, throwable -> {

                        });

    }


    public void onArticleClick(int position) {
        String url = articlesList.get(position).getUrl();
        openArticleFragment.postValue(url);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable1 != null && !disposable1.isDisposed()) {
            disposable1.dispose();
        }
    }
}
