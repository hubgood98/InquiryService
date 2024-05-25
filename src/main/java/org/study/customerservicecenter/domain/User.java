package org.study.customerservicecenter.domain;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class User {
    private String id;
    private String password;
    private String name;
    private boolean admin;

    public User(){}

    public User(String id, String password) {
        this.id = id;
        this.password = password;
        this.name = "";
        this.admin = false;
    }

    public User(String id, String password, String name, boolean admin)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.admin = admin;
    }

}
