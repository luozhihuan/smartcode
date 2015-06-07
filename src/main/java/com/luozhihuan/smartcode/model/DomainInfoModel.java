package com.luozhihuan.smartcode.model;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class DomainInfoModel {

    public DomainInfoModel(String name, String columnClassName) {
        this.name = name;
        this.columnClassName = columnClassName;
    }

    public DomainInfoModel() {
    }

    private String name;

    private String columnClassName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }
}
