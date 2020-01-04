package com.diazzerss.newsapp.java.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diazzerss.newsapp.R;
import com.diazzerss.newsapp.java.article.ArticlesAdapter;
import com.diazzerss.newsapp.java.article.ArticlesFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class SearchFragment extends Fragment {
    private Toolbar toolbar;
    private TextView searchTitleText;
    private SearchView searchView;
    private SearchViewModel searchViewModel;
    private ArticlesAdapter articlesAdapter;
    private RecyclerView recyclerView;

    private Disposable disposable;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);


        //Toolbar
        toolbar = view.findViewById(R.id.toolbar_search);
        toolbar.setTitle("Поиск");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        initRecyclerView(view);

        searchTitleText = view.findViewById(R.id.text_search_title);
        searchTitleText.setText("Начните вводить запрос");
        searchTitleText.setVisibility(View.VISIBLE);


        articlesAdapter.addOnClickListener(position -> searchViewModel.onArticleClick(position));


        bindViewModel();

        return view;
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        myActionMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                recyclerView.setVisibility(View.GONE);
                searchTitleText.setText("Начните вводить запрос");
                searchTitleText.setVisibility(View.VISIBLE);
                return true;
            }
        });
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);


        disposable = Observable.create((ObservableOnSubscribe<String>) emitter ->
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (query.length() == 0) {
                            recyclerView.setVisibility(View.GONE);
                            searchTitleText.setText("Начните вводить запрос");
                            searchTitleText.setVisibility(View.VISIBLE);
                        }
                        emitter.onNext(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (newText.length() == 0) {
                            recyclerView.setVisibility(View.GONE);
                            searchTitleText.setText("Начните вводить запрос");
                            searchTitleText.setVisibility(View.VISIBLE);
                        }
                        emitter.onNext(newText);
                        return false;
                    }

                }))
                .map(text -> text.toLowerCase().trim())
                .filter(text -> text.length() > 3)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribe(text -> searchViewModel.loadArticlesWithQuery(text));


        super.onCreateOptionsMenu(menu, inflater);

    }

    private void initRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.articles_recycler_view_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        articlesAdapter = new ArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);
    }

    private void bindViewModel() {

        searchViewModel.articlesListLiveData.observe(this.getViewLifecycleOwner(), article_s -> {
            if (article_s.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                searchTitleText.setText("Ничего не найдено");
                searchTitleText.setVisibility(View.VISIBLE);
            } else {
                searchTitleText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                articlesAdapter.addData(article_s);
            }
        });
        searchViewModel.openArticleFragment.observe(this.getViewLifecycleOwner(), url -> Navigation
                .findNavController(getView())
                .navigate(R.id.articleFragment, ArticlesFragment.getBundle(url)));

    }

}