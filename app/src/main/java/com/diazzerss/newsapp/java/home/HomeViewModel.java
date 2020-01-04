package com.diazzerss.newsapp.java.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzerss.newsapp.java.NetworkService;
import com.diazzerss.newsapp.java.SingleLiveEvent;
import com.diazzerss.newsapp.java.article.Article_;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private ArrayList<Article_> articlesList;

    private CompositeDisposable disposable;

    public MutableLiveData<ArrayList<Article_>> articlesListLiveData;
    public SingleLiveEvent<String> openArticleFragment;
    public MutableLiveData<Boolean> loadingLiveData;

    public HomeViewModel() {
        articlesListLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
        openArticleFragment = new SingleLiveEvent<>();
        disposable = new CompositeDisposable();

    }


    public void loadData() {

        disposable.add(NetworkService
                .getInstance()
                .getNewsAPI()
                .getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> loadingLiveData.postValue(Boolean.TRUE))
                .doFinally(() -> {
                    loadingLiveData.postValue(Boolean.FALSE);
                })
                .subscribe(article -> {
                    articlesList = new ArrayList<>();
                    articlesList = article.articles;
                    articlesListLiveData.postValue(articlesList);
                }, throwable -> {

                })
        );


    }

    public void onArticleClick(int position) {
        String url = articlesList.get(position).getUrl();
        openArticleFragment.postValue(url);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }


}