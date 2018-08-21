package com.android.jsonregistercheck.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 8/6/2018.
 */

public class SubmitData {
    @SerializedName("success")
    private static int status;
    @SerializedName("message")
    private String message;

    public SubmitData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
