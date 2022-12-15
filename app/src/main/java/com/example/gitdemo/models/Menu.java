package com.example.gitdemo.models;

public class Menu {
    private String test_title, imgURL;

    public Menu(String test_title, String imgURL) {
        this.test_title = test_title;
        this.imgURL = imgURL;
    }

    public String getTest_title() {
        return test_title;
    }

    public void setTest_title(String test_title) {
        this.test_title = test_title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
