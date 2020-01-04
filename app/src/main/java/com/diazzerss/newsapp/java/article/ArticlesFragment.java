package com.diazzerss.newsapp.java.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.diazzerss.newsapp.R;


public class ArticlesFragment extends Fragment {

    private String articleURL;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_articles, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            articleURL = bundle.get("url").toString();
        }

        //WebView
        WebView webView = view.findViewById(R.id.webView_article);
        webView.setWebViewClient(new ArticleWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(articleURL);

        //ProgressBar
        progressBar = view.findViewById(R.id.progressBar_article);
        progressBar.setVisibility(View.VISIBLE);

        //Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_article);
        toolbar.setTitle("Статья");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> ArticlesFragment.this.getActivity().onBackPressed());

        return view;

    }

    private class ArticleWebViewClient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public static Bundle getBundle(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        return args;
    }

}
