package com.example.RestServer.Service;

public class User {

    private String id, name, message;

    //Default constructor
    public User (String id, String name, String msg){
        this.id = id;
        this.name = name;
        this.message = msg;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString (){
        return "\n[id = " + id + ", name = " + name +  ", message = " + message;
    }

}
