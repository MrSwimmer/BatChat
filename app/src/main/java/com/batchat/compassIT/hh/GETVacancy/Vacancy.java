package com.batchat.compassIT.hh.GETVacancy;

/**
 * Created by Севастьян on 26.09.2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vacancy {

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<KeySkills> getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(List<KeySkills> keySkills) {
        this.keySkills = keySkills;
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

    public Object getBrandedDescription() {
        return brandedDescription;
    }

    public void setBrandedDescription(Object brandedDescription) {
        this.brandedDescription = brandedDescription;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    @SerializedName("experience")
    @Expose
    private Experience experience;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("key_skills")
    @Expose
    private List<KeySkills> keySkills = null;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("branded_description")
    @Expose
    private Object brandedDescription;

    @SerializedName("specializations")
    @Expose
    private List<Specialization> specializations = null;

}
