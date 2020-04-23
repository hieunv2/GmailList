package com.example.listapp;

public class ItemModal {
    String fullName;
    int imageAvatar;
    boolean isSelected;
    String description;
    String date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImageAvatar(int imageAvatar) {
        this.imageAvatar = imageAvatar;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getFullName() {
        return fullName;
    }

    public int getImageAvatar() {
        return imageAvatar;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public ItemModal(String fullName, int imageAvatar,String description, String date) {
        this.fullName = fullName;
        this.imageAvatar = imageAvatar;
        this.description = description;
        this.date = date;
        this.isSelected = false;
    }
}
