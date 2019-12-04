package com.sisf.Pojo;

public class Cons_quiz_home {
    String id,desc,Que_size;

    public Cons_quiz_home(String id, String desc, String que_size) {
        this.id = id;
        this.desc = desc;
        Que_size = que_size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQue_size() {
        return Que_size;
    }

    public void setQue_size(String que_size) {
        Que_size = que_size;
    }
}
