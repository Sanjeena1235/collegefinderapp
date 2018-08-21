package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/15/2018.
 */

public class FaqClass {
    private String questions;

    public String getQuestions ()
    {
        return questions;
    }

    public void setQuestions (String questions)
    {
        this.questions = questions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [questions = "+questions+"]";
    }
}
