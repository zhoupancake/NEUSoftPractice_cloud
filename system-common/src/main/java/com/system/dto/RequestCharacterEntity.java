package com.system.dto;

import com.system.entity.character.Administrator;
import com.system.entity.character.GridDetector;
import com.system.entity.character.Supervisor;

public class RequestCharacterEntity {
    private String id;
    private String username;
    private String password;
    private Integer status;
    private String role;
    private String tel;
    private String name;
    private Integer age;
    private String sex;
    private String idCard;

    private String zone;


    public User getUser_create(){
        return User.builder()
                .password(password)
                .role(role)
                .build();
    }

    public User getUser_modify(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .status(status)
                .role(role)
                .build();
    }

    public Administrator getAdministrator_create(){
        return Administrator.builder()
                .idCard(idCard)
                .name(name)
                .build();
    }

    public Administrator getAdministrator_modify(){
        return Administrator.builder()
                .id(id)
                .idCard(idCard)
                .name(name)
                .build();
    }

    public Supervisor getSupervisor_create(){
        return Supervisor.builder()
                .tel(tel)
                .name(name)
                .age(age)
                .sex(sex)
                .build();
    }

    public Supervisor getSupervisor_modify(){
        return Supervisor.builder()
                .id(id)
                .status(status)
                .tel(tel)
                .name(name)
                .age(age)
                .sex(sex)
                .build();
    }

    public GridDetector getGridDetector_create(){
        return GridDetector.builder()
                .idCard(idCard)
                .name(name)
                .zone(zone)
                .build();
    }

    public GridDetector getGridDetector_modify(){
        return GridDetector.builder()
                .id(id)
                .idCard(idCard)
                .name(name)
                .zone(zone)
                .build();
    }
}
