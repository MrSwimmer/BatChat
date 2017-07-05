package com.batchat.batchat;

import android.media.Image;

public class Message {
    // поле
    private String text;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;
    // пустой конструктор (необходим для работы базы данных Firebase)
    public Message() {
    }

    // конструктор, используемый нами
    public Message(String text) {
        this.text = text;
    }

    // геттер
    public String getText() {
        return text;
    }

    // сеттер
    public void setText(String text) {
        this.text = text;
    }
}


