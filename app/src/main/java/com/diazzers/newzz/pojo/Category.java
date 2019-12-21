package com.diazzers.newzz.pojo;

public class Category {
    private String categoryName;
    private String categoryImage;

    public Category(String categoryName, String categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!categoryName.equals(category.categoryName)) return false;
        return categoryImage.equals(category.categoryImage);
    }

    @Override
    public int hashCode() {
        int result = categoryName.hashCode();
        result = 31 * result + categoryImage.hashCode();
        return result;
    }
}
