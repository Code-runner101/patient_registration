package com.example.ThymeleafProject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemObjRepository extends JpaRepository<ItemObj, Long> {
}
