package com.sisf.Pojo;

public class Cons_quiz_home {
    String id,name,description,no_of_question,timing,subject_id,subject_name;

    public Cons_quiz_home(String id, String name, String description, String no_of_question, String timing, String subject_id, String subject_name) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.no_of_question = no_of_question;
        this.timing = timing;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public Cons_quiz_home(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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
