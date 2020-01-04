package com.diazzers.newzz.ui.article;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.diazzers.newzz.OnItemClickListener;
import com.diazzers.newzz.R;
import com.diazzers.newzz.adapter.ArticlesAdapter;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.ui.home.HomeViewModel;

import java.util.ArrayList;

public class ArticleFragment extends Fragment {

    private WebView webView;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getContext(),"Загрузка...",Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.fragment_article, container, false);

        //Progress Bar
        progressBar = view.findViewById(R.id.progressBarArticle);

        webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            String url = bundle.get("url").toString();
            Log.d("url", "url:" + url);
            webView.loadUrl(url);
        }

        toolbar = view.findViewById(R.id.toolbar_article);
        toolbar.setTitle("Статья");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;

    }

    public static Bundle getBundle(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);

        return args;
    }

}
