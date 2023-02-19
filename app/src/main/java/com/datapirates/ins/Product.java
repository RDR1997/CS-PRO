package com.datapirates.ins;

import java.io.Serializable;

public class Product implements Serializable {


//--------------------------------------------------------------------------------------------------
    private boolean isChecked = false;

    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

//--------------------------------------------------------------------------------------------------
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//--------------------------------------------------------------------------------------------------
    String item_number;

    public String getItem_number() {
        return item_number;
    }

   public void setItem_number(String item_number) {
        this.item_number = item_number;
    }
}