package com.sisf.Pojo;

public class Cons_submit_ans {
    int Chapt_ID,Quest_ID,Select_Ans;
    boolean Right_Ans;

    public Cons_submit_ans() {

    }

    public Cons_submit_ans(int chapt_ID, int quest_ID, int select_Ans, boolean right_Ans) {
        Chapt_ID = chapt_ID;
        Quest_ID = quest_ID;
        Select_Ans = select_Ans;
        Right_Ans = right_Ans;
    }

    public int getChapt_ID() {
        return Chapt_ID;
    }

    public void setChapt_ID(int chapt_ID) {
        Chapt_ID = chapt_ID;
    }

    public int getQuest_ID() {
        return Quest_ID;
    }

    public void setQuest_ID(int quest_ID) {
        Quest_ID = quest_ID;
    }

    public int getSelect_Ans() {
        return Select_Ans;
    }

    public void setSelect_Ans(int select_Ans) {
        Select_Ans = select_Ans;
    }

    public boolean isRight_Ans() {
        return Right_Ans;
    }

    public void setRight_Ans(boolean right_Ans) {
        Right_Ans = right_Ans;
    }
}
