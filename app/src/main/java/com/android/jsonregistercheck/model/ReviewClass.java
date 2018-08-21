package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/12/2018.
 */

public class ReviewClass {
    private String review;

    public String getReview ()
    {
        return review;
    }

    public void setReview (String review)
    {
        this.review = review;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [review = "+review+"]";
    }
}
