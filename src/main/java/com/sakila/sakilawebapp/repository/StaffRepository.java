package com.sakila.sakilawebapp.repository;

import com.sakila.sakilawebapp.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Byte> {

    Staff findByUsername(String username);


}
