package com.sisf.Pojo;

public class Cons_quiz_home {
    String id,name,description,no_of_question,timing;

    public Cons_quiz_home(String id, String name, String description, String no_of_question, String timing) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.no_of_question = no_of_question;
        this.timing = timing;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNo_of_question() {
        return no_of_question;
    }

    public void setNo_of_question(String no_of_question) {
        this.no_of_question = no_of_question;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
