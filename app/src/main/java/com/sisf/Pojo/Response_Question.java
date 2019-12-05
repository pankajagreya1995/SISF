package com.sisf.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response_Question {
    @SerializedName("status")
    public Boolean status;

    @SerializedName("Error")
    public String Error;

    @SerializedName("Responce")
    public List<Response> Responce=null;

    public class Response
    {
        @SerializedName("id")
        public String id;

        @SerializedName("chapter_id")
        public String chapter_id;

        @SerializedName("question")
        public String question;

        @SerializedName("created_on")
        public String created_on;

        @SerializedName("created_by")
        public String created_by;

        @SerializedName("questionRows")
        public List<Question_Options> questionRows;

        public class Question_Options
        {
            @SerializedName("id")
            public String id;

            @SerializedName("question_id")
            public String question_id;

            @SerializedName("objective")
            public String objective;

            @SerializedName("currect_answer")
            public String currect_answer;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(String question_id) {
                this.question_id = question_id;
            }

            public String getObjective() {
                return objective;
            }

            public void setObjective(String objective) {
                this.objective = objective;
            }

            public String getCurrect_answer() {
                return currect_answer;
            }

            public void setCurrect_answer(String currect_answer) {
                this.currect_answer = currect_answer;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChapter_id() {
            return chapter_id;
        }

        public void setChapter_id(String chapter_id) {
            this.chapter_id = chapter_id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCreated_on() {
            return created_on;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(String created_by) {
            this.created_by = created_by;
        }

        public List<Question_Options> getQuestionRows() {
            return questionRows;
        }

        public void setQuestionRows(List<Question_Options> questionRows) {
            this.questionRows = questionRows;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public List<Response> getResponce() {
        return Responce;
    }

    public void setResponce(List<Response> responce) {
        Responce = responce;
    }
}
