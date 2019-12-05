package com.sisf.Pojo;

public class Cons_quiz_home {
    String id,name,Que_size;

    public Cons_quiz_home(String id, String name, String que_size) {
        this.id = id;
        this.name = name;
        Que_size = que_size;
    }

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

    public String getQue_size() {
        return Que_size;
    }

    public void setQue_size(String que_size) {
        Que_size = que_size;
    }
}
