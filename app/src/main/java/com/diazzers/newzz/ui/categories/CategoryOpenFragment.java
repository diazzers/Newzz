package com.diazzers.newzz.ui.categories;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diazzers.newzz.R;

public class CategoryOpenFragment extends Fragment {

    private CategoryOpenViewModel categoryOpenViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        categoryOpenViewModel = ViewModelProviders.of(this).get(CategoryOpenViewModel.class);
        View view = inflater.inflate(R.layout.category_open_fragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String ctg = bundle.get("ctg").toString();
            Log.d("ctg", "ctg:" + ctg);

        }


        categoryOpenViewModel.loadData();

        return view;


    }


    public static Bundle getBundle(String ctg) {

        Bundle args = new Bundle();
        args.putString("ctg", ctg);

        return args;
    }


}
