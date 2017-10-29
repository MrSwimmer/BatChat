package com.batchat.compassIT.Realm;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Севастьян on 12.10.2017.
 */

public class RealSkill  extends RealmObject{
    @Required
    private String skill, area;
    private int count=0;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
