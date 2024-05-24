package org.study.customerservicecenter.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="user")//DB의 TableName
public class User {

    @Id //기본키
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
