package com.londoncentralmosque.Model;

/**
 * Created by Android Dev on 6/20/2017.
 */

public class Event {
    private String title;
    private String id;
    private String date;
    private String  description;
    //    public void Event(String id,String title,String date,String description){
//        this.id=id;
//        this.title=title;
//        this.date=date;
//        this.description=description;
//
//    }
    public Event(String title,String date,String description ){

        this.title=title;
        this.date=date;
        this.description=description;


    }
    public Event(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}


