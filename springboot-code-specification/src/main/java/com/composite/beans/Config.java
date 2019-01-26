package com.composite.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String value;
    private String creator;
    private long id;
}
