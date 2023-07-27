package com.example.trianing_project.service.mapper.impl;

import com.example.trianing_project.domain.Certificate;
import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.service.dto.CertificateDTO;
import com.example.trianing_project.service.mapper.CertificateMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CertificateMapperImpl implements CertificateMapper {
    @Override
    public Certificate toEntity(CertificateDTO dto) {
        if (dto == null) {
            return null;
        }
        Certificate certificate = new Certificate();
        certificate.setCertificateOfficer(dto.getCertificateOfficer());
        certificate.setDescription(dto.getDescription());
        certificate.setId(dto.getId());
        certificate.setName(dto.getName());
        certificate.setEmployeeId(dto.getEmployeeId());
        certificate.setExpirationDate(dto.getExpirationDate());
        certificate.setIssueDate(dto.getIssueDate());
        return certificate;
    }

    @Override
    public CertificateDTO toDto(Certificate entity) {
        if (entity == null) {
            return null;
        }
        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setCertificateOfficer(entity.getCertificateOfficer());
        certificateDTO.setDescription(entity.getDescription());
        certificateDTO.setEmployeeId(entity.getEmployeeId());
        Employee employee = entity.getEmployee();
        if (employee != null) {
            certificateDTO.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
        }
        certificateDTO.setExpirationDate(entity.getExpirationDate());
        certificateDTO.setIssueDate(entity.getIssueDate());
        certificateDTO.setId(entity.getId());
        certificateDTO.setName(entity.getName());
        return certificateDTO;
    }

    @Override
    public List<Certificate> toEntity(List<CertificateDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Certificate> certificates = new ArrayList<>();
        for (CertificateDTO dto : dtoList) {
            certificates.add(toEntity(dto));
        }
        return certificates;
    }

    @Override
    public List<CertificateDTO> toDto(List<Certificate> entityList) {
        if (entityList == null) {
            return null;
        }
        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Certificate entity : entityList) {
            certificateDTOS.add(toDto(entity));
        }
        return certificateDTOS;
    }
}
