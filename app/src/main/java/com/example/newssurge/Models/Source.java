package com.example.newssurge.Models;

import java.io.Serializable;

public class Source implements Serializable  {
    //variable is base on the variable name from the API
    String id = "";
    String name ="" ;

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
