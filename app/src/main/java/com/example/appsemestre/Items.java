package com.example.appsemestre;

public class Items {
    private String text;
    private int imageResId;

    public Items(String text, int imageResId) {
        this.text = text;
        this.imageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public int getImageResId() {
        return imageResId;
    }
}
