package org.study.customerservicecenter.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String id;
    private String password;
    private String name;
    private String rank; //등급

    public User(String id, String password, String name, String rank)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.rank = rank;
    }
}
