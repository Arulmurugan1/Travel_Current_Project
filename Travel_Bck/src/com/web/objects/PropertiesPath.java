package com.web.objects;

public class PropertiesPath {
    
    private int id ;
    private String key, value ;
    
    
    public PropertiesPath() {
        super();
    }
    
    
    
    public PropertiesPath(int id, String key, String value) {
        super();
        this.id = id;
        this.key = key;
        this.value = value;
    }



    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }



    @Override
    public String toString() {
        return "PropertiesPath [id=" + this.id + ", key=" + this.key + ", value=" + this.value + "]";
    }
    
}
