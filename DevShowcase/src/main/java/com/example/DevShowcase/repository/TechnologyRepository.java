package com.example.DevShowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.DevShowcase.model.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}