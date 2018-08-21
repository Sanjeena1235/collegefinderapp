package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/15/2018.
 */

public class ContactClass {
    private String tel_no;

    private String id;

    private String college_id;

    private String postal_add;

    private String email;

    private String fax_no;

    public String getTel_no ()
    {
        return tel_no;
    }

    public void setTel_no (String tel_no)
    {
        this.tel_no = tel_no;
    }

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

    public String getPostal_add ()
    {
        return postal_add;
    }

    public void setPostal_add (String postal_add)
    {
        this.postal_add = postal_add;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getFax_no ()
    {
        return fax_no;
    }

    public void setFax_no (String fax_no)
    {
        this.fax_no = fax_no;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tel_no = "+tel_no+", id = "+id+", college_id = "+college_id+", postal_add = "+postal_add+", email = "+email+", fax_no = "+fax_no+"]";
    }
}
