package com.example.splitwisedec232.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Group extends BaseModel{
    private String name;
    private String description;

    @ManyToMany
    private List<User> users;
    @ManyToMany
    private List<User> admins;
}
