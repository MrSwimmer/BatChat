package com.batchat.compassIT.hh.GETPage;

/**
 * Created by Севастьян on 29.09.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageV {
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("found")
    @Expose
    private Integer found;

    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
