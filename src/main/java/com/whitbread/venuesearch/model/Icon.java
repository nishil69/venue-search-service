package com.whitbread.venuesearch.model;


import java.io.Serializable;

public class Icon implements Serializable {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }
    public String getSuffix() {
        return suffix;
    }
}
