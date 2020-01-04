package com.diazzerss.newsapp.java.bookmarks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.diazzerss.newsapp.R;

public class BookmarksFragment extends Fragment {

    private BookmarksViewModel bookmarksViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        bookmarksViewModel = new ViewModelProvider(this).get(BookmarksViewModel.class);

        //Toolbar
        Toolbar toolbar = v.findViewById(R.id.toolbar_bookmarks);
        toolbar.setTitle("Избранное");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        return v;
    }


}