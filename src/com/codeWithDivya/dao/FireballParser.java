package com.codeWithDivya.dao;

import java.util.List;

public class FireballParser {


    private List<Fireball> fireballList;
    private int count;

    public FireballParser(int count) {
        this.count = count;
    }

    public <T> FireballParser(List<T> asList, int count) {
        for (T t: asList){
            Fireball fireball=new Fireball();


        }
    }

    public List<Fireball> getFireballList() {
        return fireballList;
    }

    public void setFireballList(List<Fireball> fireballList) {
        this.fireballList = fireballList;
    }

    @Override
    public String toString() {
        return "FireballParser{" +
                "fireballList=" + fireballList +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
