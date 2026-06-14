package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}