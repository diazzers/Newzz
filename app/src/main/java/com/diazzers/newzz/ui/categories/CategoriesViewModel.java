package com.diazzers.newzz.ui.categories;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diazzers.newzz.NewzzRestClient;
import com.diazzers.newzz.R;
import com.diazzers.newzz.pojo.Article;
import com.diazzers.newzz.pojo.Category;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

import static android.util.Config.LOGD;

public class CategoriesViewModel extends ViewModel {

    private ArrayList<Article> articlesList;
    private ArrayList<Category> categoriesList;
    public MutableLiveData<ArrayList<Category>> categoriesListLiveData;
    public MutableLiveData<String> openCategory;

    String lt;

    public CategoriesViewModel() {
        categoriesListLiveData = new MutableLiveData<>();
        openCategory = new MutableLiveData<>();
    }

    public void loadData() {

        categoriesList = new ArrayList<>();

        Category categoryBusiness = new Category("Бизнес","file:///android_asset/category_business.jpg" );
        Category categoryDesign = new Category("Дизайн", "file:///android_asset/category_design.jpg");
        Category categoryFood = new Category("Еда", "file:///android_asset/category_food.jpg");
        Category categoryMovies = new Category("Кино", "file:///android_asset/category_movies.jpg");
        Category categoryEducation = new Category("Образование", "file:///android_asset/category_education.jpg");
        Category categoryPolitics = new Category("Политика", "file:///android_asset/category_politics.jpg");
        Category categorySport = new Category("Спорт","file:///android_asset/category_sport.jpg");
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

        NewzzRestClient.getBusinessCategoryNews(
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            articlesList = new ArrayList<>();

                            for (int i = 0; i < response.getJSONArray("articles").length(); i++) {
                                String title = response.getJSONArray("articles").getJSONObject(i).getString("title");
                                String url = response.getJSONArray("articles").getJSONObject(i).getString("url");
                                String urlToImage = response.getJSONArray("articles").getJSONObject(i).getString("urlToImage");

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                                Date publishedAt = null;
                                try {
                                    publishedAt = sdf.parse(response.getJSONArray("articles").getJSONObject(i).getString("publishedAt"));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                String sourceName = response.getJSONArray("articles").getJSONObject(i).getJSONObject("source").getString("name");


                                Article article = new Article(title, url, urlToImage, publishedAt, sourceName);
                                articlesList.add(i, article);

                                lt = articlesList.toString();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    public void onArticleClick(int position) {
        if (categoriesList.get(position).getCategoryName().equals("Бизнес")){
            openCategory.postValue(lt);
        }



    }

}