package com.diazzers.newzz.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diazzers.newzz.OnItemClickListener;
import com.diazzers.newzz.R;
import com.diazzers.newzz.adapter.ArticlesAdapter;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.ui.article.ArticleFragment;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private ArticlesAdapter articlesAdapter;
    private HomeViewModel homeViewModel;
    private Toolbar toolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar progressBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = v.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Главные новости");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        initRecyclerView(v);

        mSwipeRefreshLayout = v.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //Progress Bar
        progressBar = v.findViewById(R.id.progressBarHome);


        //
        articlesAdapter.addOnClickListener(new OnItemClickListener() {

            @Override
            public void onClick(int position) {
                homeViewModel.onArticleClick(position);


            }
        });

        bindViewModel();
        homeViewModel.loadData();

        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            Toast.makeText(getContext(), "В разработке:)", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.articles_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);


    }


    public void bindViewModel() {
        homeViewModel.articlesListLiveData.observe(this, new Observer<ArrayList<Article>>() {
            @Override
            public void onChanged(ArrayList<Article> articles) {
                articlesAdapter.addData(articles);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        homeViewModel.loadingLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                if (b)progressBar.setVisibility(View.VISIBLE);
                else progressBar.setVisibility(View.GONE);
            }
        });

        homeViewModel.openArticleFragment.observe(this, new Observer<String>() {
        @Override
        public void onChanged(String s) {
            Navigation.findNavController(getView()).navigate(R.id.articleFragment, ArticleFragment.getBundle(s));
        }
    });
}


    @Override
    public void onRefresh() {
        articlesAdapter.clearData();
        homeViewModel.loadData();
    }

}
