package com.diazzers.newzz.ui.categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzers.newzz.R;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.pojo.Category;

import java.util.ArrayList;

public class CategoriesViewModel extends ViewModel {


    private ArrayList<Category> categoriesList;
    public MutableLiveData<ArrayList<Category>> categoriesListLiveData;

    public CategoriesViewModel() {
        categoriesListLiveData = new MutableLiveData<>();
    }

    public void loadData() {

        categoriesList = new ArrayList<>();
        String categoryBusiness = "Бизнес";
        String categoryDesign = "Дизайн";
        String categoryFood = "Еда";
        String categoryArt = "Искусство";
        String categoryMovies = "Кино";
        String categoryEducation = "Образование";
        String categoryPolitics = "Политика";
        String categorySport = "Спорт";
        String categoryTech = "Технологии";
        String categoryFinance = "Финансы";

        String categoryBusinessImage= "file:///android_asset/category_business.jpg";
        String categoryDesignImage= "file:///android_asset/category_design.jpg";
        String categoryFoodImage= "file:///android_asset/category_food.jpg";
        String categoryArtImage= "file:///android_asset/category_art.jpg";
        String categoryMoviesImage= "file:///android_asset/category_movies.jpg";
        String categoryEducationImage= "file:///android_asset/category_education.jpg";
        String categoryPoliticsImage= "file:///android_asset/category_politics.jpg";
        String categorySportImage= "file:///android_asset/category_sport.jpg";
        String categoryTechImage= "file:///android_asset/category_tech.jpg";
        String categoryFinanceImage= "file:///android_asset/category_finance.jpg";

        Category category1 = new Category(categoryBusiness, categoryBusinessImage);
        Category category2 = new Category(categoryDesign, categoryDesignImage);
        Category category3 = new Category(categoryFood, categoryFoodImage);
        Category category4 = new Category(categoryArt, categoryArtImage);
        Category category5 = new Category(categoryMovies, categoryMoviesImage);
        Category category6 = new Category(categoryEducation, categoryEducationImage);
        Category category7 = new Category(categoryPolitics, categoryPoliticsImage);
        Category category8 = new Category(categorySport,categorySportImage);
        Category category9 = new Category(categoryTech, categoryTechImage);
        Category category10 = new Category(categoryFinance, categoryFinanceImage);


        categoriesList.add(0,category1);
        categoriesList.add(1,category2);
        categoriesList.add(2,category3);
        categoriesList.add(3,category4);
        categoriesList.add(4,category5);
        categoriesList.add(5,category6);
        categoriesList.add(6,category7);
        categoriesList.add(7,category8);
        categoriesList.add(8,category9);
        categoriesList.add(9,category10);

        categoriesListLiveData.postValue(categoriesList);
    }
}