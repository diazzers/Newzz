package com.diazzers.newzz.ui.categories;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzers.newzz.pojo.Category;

import java.util.ArrayList;


public class CategoriesViewModel extends ViewModel {

    private ArrayList<Category> categoriesList;
    public MutableLiveData<ArrayList<Category>> categoriesListLiveData;
    public MutableLiveData<String> openCategory;


    public CategoriesViewModel() {
        categoriesListLiveData = new MutableLiveData<>();
        openCategory = new MutableLiveData<>();
    }

    public void loadData() {

        categoriesList = new ArrayList<>();

        Category categoryBusiness = new Category("Бизнес", "file:///android_asset/category_business.jpg");
        Category categoryDesign = new Category("Дизайн", "file:///android_asset/category_design.jpg");
        Category categoryFood = new Category("Еда", "file:///android_asset/category_food.jpg");
        Category categoryMovies = new Category("Кино", "file:///android_asset/category_movies.jpg");
        Category categoryEducation = new Category("Образование", "file:///android_asset/category_education.jpg");
        Category categoryPolitics = new Category("Политика", "file:///android_asset/category_politics.jpg");
        Category categorySport = new Category("Спорт", "file:///android_asset/category_sport.jpg");
        Category categoryTechnologies = new Category("Технологии", "file:///android_asset/category_tech.jpg");
        Category categoryFinance = new Category("Финансы", "file:///android_asset/category_finance.jpg");

        categoriesList.add(categoryBusiness);
        categoriesList.add(categoryDesign);
        categoriesList.add(categoryFood);
        categoriesList.add(categoryMovies);
        categoriesList.add(categoryEducation);
        categoriesList.add(categoryPolitics);
        categoriesList.add(categorySport);
        categoriesList.add(categoryTechnologies);
        categoriesList.add(categoryFinance);

        categoriesListLiveData.postValue(categoriesList);


    }

    public void onCategoryClick(int position) {
        if (categoriesList.get(position).getCategoryName().equals("Бизнес")) {
            String s = "Бизнес";
            openCategory.postValue(s);
        }


    }

}