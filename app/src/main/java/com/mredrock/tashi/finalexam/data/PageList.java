package com.mredrock.tashi.finalexam.data;

import java.util.List;

public class PageList {

    /**
     * categoryList : [{"categoryId":"10","name":"新知","color":"#A2B0A0"},{"categoryId":"1","name":"社会","color":"#F04A50"},{"categoryId":"2","name":"世界","color":"#33B7A7"},{"categoryId":"9","name":"体育","color":"#FECE3E"},{"categoryId":"5","name":"生活","color":"#8CD931"},{"categoryId":"8","name":"科技","color":"#33A7D8"},{"categoryId":"4","name":"娱乐","color":"#E966AE"},{"categoryId":"3","name":"财富","color":"#3276B5"},{"categoryId":"31","name":"汽车","color":"#6E8490"},{"categoryId":"6","name":"美食","color":"#F58D4E"},{"categoryId":"59","name":"音乐","color":"#B936EB"}]
     */


    private List<CategoryListBean> categoryList;

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class CategoryListBean {
        /**
         * categoryId : 10
         * name : 新知
         * color : #A2B0A0
         */

        private String categoryId;
        private String name;
        private String color;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
