package com.example.hibernatedemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hibernatedemo.entity.Role;
import com.example.hibernatedemo.util.AllRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(AllRole name);
}
