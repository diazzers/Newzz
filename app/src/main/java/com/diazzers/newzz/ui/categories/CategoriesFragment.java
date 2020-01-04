package com.diazzers.newzz.ui.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diazzers.newzz.OnItemClickListener;
import com.diazzers.newzz.R;
import com.diazzers.newzz.adapter.ArticlesAdapter;
import com.diazzers.newzz.adapter.CategoriesAdapter;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.pojo.Category;
import com.diazzers.newzz.ui.article.ArticleFragment;
import com.diazzers.newzz.ui.home.HomeFragment;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private CategoriesAdapter categoriesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        View v = inflater.inflate(R.layout.fragment_categories, container, false);

        toolbar = v.findViewById(R.id.toolbar_categories);
        toolbar.setTitle("Категории");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        initRecyclerView(v);

        categoriesAdapter.addOnClickListener(new OnItemClickListener() {

            @Override
            public void onClick(int position) {
                categoriesViewModel.onCategoryClick(position);


            }
        });

        bindViewModel();
        categoriesViewModel.loadData();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.categories_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(getContext(), "Поиск", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initRecyclerView(View v) {
        recyclerView = v.findViewById(R.id.categories_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        categoriesAdapter = new CategoriesAdapter();
        recyclerView.setAdapter(categoriesAdapter);

    }

    public void bindViewModel() {
        categoriesViewModel.categoriesListLiveData.observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                categoriesAdapter.addData(categories);

            }
        });


        categoriesViewModel.openCategory.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Navigation.findNavController(getView()).navigate(R.id.categoryOpenFragment, CategoryOpenFragment.getBundle(s));
            }
        });

    }
}