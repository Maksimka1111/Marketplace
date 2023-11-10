package com.example.SpringPostgres.repository;

import com.example.SpringPostgres.entities.products.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
