package com.java.designTask.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Destination {
    private String name;
    private List<Activity> activities;
    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }
    public void addActivity(Activity activity) {
        activities.add(activity);
    }
}
