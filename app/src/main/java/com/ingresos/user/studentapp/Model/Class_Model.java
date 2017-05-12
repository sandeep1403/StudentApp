package com.ingresos.user.studentapp.Model;

/**
 * Created by user on 20-Apr-17.
 */

public class Class_Model {

    private String class_no;
    private String class_location;
    private String class_payment  ;
    private String class_start_time;
    private String class_end_time;
    private String class_start_date;
    private String class_end_date;

    public String getClass_no() {
        return class_no;
    }

    public String getClass_start_time() {
        return class_start_time;
    }

    public void setClass_start_time(String class_start_time) {
        this.class_start_time = class_start_time;
    }

    public String getClass_end_time() {
        return class_end_time;
    }

    public void setClass_end_time(String class_end_time) {
        this.class_end_time = class_end_time;
    }

    public String getClass_start_date() {
        return class_start_date;
    }

    public void setClass_start_date(String class_start_date) {
        this.class_start_date = class_start_date;
    }

    public String getClass_end_date() {
        return class_end_date;
    }

    public void setClass_end_date(String class_end_date) {
        this.class_end_date = class_end_date;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public String getClass_location() {
        return class_location;
    }

    public void setClass_location(String class_location) {
        this.class_location = class_location;
    }

    public String getClass_payment() {
        return class_payment;
    }

    public void setClass_payment(String class_payment) {
        this.class_payment = class_payment;
    }


}
