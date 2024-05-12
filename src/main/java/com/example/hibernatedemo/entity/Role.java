package com.example.hibernatedemo.entity;

import com.example.hibernatedemo.util.AllRole;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private AllRole name;

  public Role() {

  }

  public Role(AllRole name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AllRole getName() {
    return name;
  }

  public void setName(AllRole name) {
    this.name = name;
  }
}