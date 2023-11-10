package com.example.SpringPostgres.repository;

import com.example.SpringPostgres.entities.products.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<WashingMachine, Long> {
}
