package com.example.firebase;

public class User {
    private String name;
    private String email;
    private String pass;
    private String cont;
    private String gen;
    String uid;

    public User(String name, String email,String pass, String cont, String gen, String uid)
    {
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.cont=cont;
        this.gen=gen;
        this.uid=uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getCont() {
        return cont;
    }

    public String getGen() {
        return gen;
    }

    public String getUid() {
        return uid;
    }
}
