package com.diazzerss.newsapp.java.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diazzerss.newsapp.R;
import com.diazzerss.newsapp.java.article.ArticlesAdapter;
import com.diazzerss.newsapp.java.article.ArticlesFragment;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeViewModel homeViewModel;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArticlesAdapter articlesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        //Toolbar
        Toolbar toolbar = v.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Главные новости");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //Progress Bar
        progressBar = v.findViewById(R.id.progressBarHome);

        //SwipeRefreshLayout
        swipeRefreshLayout = v.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //RecyclerView
        RecyclerView recyclerView = v.findViewById(R.id.articles_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);

        articlesAdapter.addOnClickListener(position -> homeViewModel.onArticleClick(position));

        bindViewModel();
        homeViewModel.loadData();

        return v;

    }

    private void bindViewModel() {
        homeViewModel.articlesListLiveData.observe(getViewLifecycleOwner(), articles -> {
            articlesAdapter.addData(articles);
            swipeRefreshLayout.setRefreshing(false);
        });

        homeViewModel.loadingLiveData.observe(getViewLifecycleOwner(), b -> {
            if (b) progressBar.setVisibility(View.VISIBLE);
            else progressBar.setVisibility(View.GONE);
        });

        homeViewModel.openArticleFragment.observe(getViewLifecycleOwner(), url ->
                Navigation
                        .findNavController(getView())
                        .navigate(R.id.articleFragment, ArticlesFragment.getBundle(url)));
    }

    @Override
    public void onRefresh() {
        articlesAdapter.clearData();
        homeViewModel.loadData();
    }

}
