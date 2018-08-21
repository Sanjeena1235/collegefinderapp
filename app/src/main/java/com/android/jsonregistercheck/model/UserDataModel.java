package com.android.jsonregistercheck.model;

/**
 * Created by user on 8/8/2018.
 */

public class UserDataModel {
    private Message message;

    public Message getMessage ()
    {
        return message;
    }

    public void setMessage (Message message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+"]";
    }
}
