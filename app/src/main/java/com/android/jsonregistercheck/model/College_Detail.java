package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/9/2018.
 */

public class College_Detail {
    private String id;

    private String links;

    private String college_name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLinks ()
    {
        return links;
    }

    public void setLinks (String links)
    {
        this.links = links;
    }

    public String getCollege_name ()
    {
        return college_name;
    }

    public void setCollege_name (String college_name)
    {
        this.college_name = college_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", links = "+links+", college_name = "+college_name+"]";
    }
}
