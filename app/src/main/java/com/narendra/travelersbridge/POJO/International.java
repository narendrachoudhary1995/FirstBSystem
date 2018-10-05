
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class International {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("data")
    @Expose
    private String data;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
