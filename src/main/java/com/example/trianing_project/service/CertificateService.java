package com.example.trianing_project.service;

import com.example.trianing_project.service.dto.CertificateDTO;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    List<CertificateDTO> findAllByEmployeeId(Long employeeId);
    CertificateDTO save(CertificateDTO certificateDTO);
    void delete(Long id);
    Optional<CertificateDTO> findOne(Long id);
}
