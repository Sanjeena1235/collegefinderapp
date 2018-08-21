package com.android.jsonregistercheck.model;

import com.android.jsonregistercheck.collegeinfo.College_Display;

import java.util.List;

/**
 * Created by user on 8/9/2018.
 */

public class College_list {
    private String id;

    private String name;

    private String links;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLinks ()
    {
        return links;
    }

    public void setLinks (String links)
    {
        this.links = links;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", name = "+name+", links = "+links+"]";
    }
}
