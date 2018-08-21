package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/11/2018.
 */

public class College_Aboutus {

    private String title;

    private String description;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", description = "+description+"]";
    }
}
