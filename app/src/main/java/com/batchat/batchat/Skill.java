package com.batchat.batchat;

import java.util.Comparator;

/**
 * Created by Севастьян on 03.10.2017.
 */

class Skill {
    private String skill;
    public int count=0;

    public Skill() {
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }
    public static final Comparator<Skill> COMPARE_BY_COUNT = new Comparator<Skill>() {
        @Override
        public int compare(Skill lhs, Skill rhs) {
            return rhs.getCount() - lhs.getCount();
        }
    };
}
