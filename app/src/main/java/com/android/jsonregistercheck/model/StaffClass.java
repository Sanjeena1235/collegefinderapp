package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/15/2018.
 */

public class StaffClass {
    private String id;

    private String college_id;

    private String staff;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getCollege_id ()
    {
        return college_id;
    }

    public void setCollege_id (String college_id)
    {
        this.college_id = college_id;
    }

    public String getStaff ()
    {
        return staff;
    }

    public void setStaff (String staff)
    {
        this.staff = staff;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", college_id = "+college_id+", staff = "+staff+"]";
    }

}
