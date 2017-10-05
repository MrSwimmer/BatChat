package com.batchat.batchat.Realm;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Севастьян on 01.10.2017.
 */

public class VacancysSkills extends RealmObject{

    public VacancysSkills() {

    }

    @Required
    private String skills;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Required
    private String area;

    public String getSkills() {
        return skills;
    }

    public void setSkills(final String skills) {
        this.skills = skills;
    }
}
