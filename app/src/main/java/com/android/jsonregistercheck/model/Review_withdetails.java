package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/12/2018.
 */

public class Review_withdetails {
    private String username;

    private String image;

    private String review;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

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
        return "ClassPojo [username = "+username+", image = "+image+", review = "+review+"]";
    }
}
