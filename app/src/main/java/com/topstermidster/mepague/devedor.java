package com.topstermidster.mepague;

public class devedor {
    private String name, value, date, desc;

    public devedor() {
    }

    public devedor(String name, String value, String date, String desc) {
        this.name = name;
        this.value = value;
        this.date = date;
        this.desc = desc;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() { return desc; }

    public void setDesc(String desc) { this.desc = desc; }
}