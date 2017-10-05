package com.batchat.batchat.hh.GETPage;

/**
 * Created by Севастьян on 29.09.2017.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("type")
    @Expose
    private Type type;

    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}