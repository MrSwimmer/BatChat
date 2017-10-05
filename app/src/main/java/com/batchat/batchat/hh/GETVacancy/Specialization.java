package com.batchat.batchat.hh.GETVacancy;

/**
 * Created by Севастьян on 27.09.2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialization {

    @SerializedName("profarea_id")
    @Expose
    private String profareaId;
    @SerializedName("profarea_name")
    @Expose
    private String profareaName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getProfareaId() {
        return profareaId;
    }

    public void setProfareaId(String profareaId) {
        this.profareaId = profareaId;
    }

    public String getProfareaName() {
        return profareaName;
    }

    public void setProfareaName(String profareaName) {
        this.profareaName = profareaName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
