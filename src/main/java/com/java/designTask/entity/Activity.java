package com.java.designTask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

}
