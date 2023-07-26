package com.example.trianing_project.repository;

import com.example.trianing_project.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Long> {
    List<Certificate> findAllByEmployeeId(Long employeeId);
}
