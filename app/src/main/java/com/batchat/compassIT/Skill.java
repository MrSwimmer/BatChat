package com.batchat.compassIT;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by Севастьян on 12.10.2017.
 */

public class Skill {
    @Required
    private String skill, area;
    private int count=0;

    public Skill(String skill, String area) {
        this.skill = skill;
        this.area = area;
        addCount();
    }

    public void addCount() {
        this.count++;
    }

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
