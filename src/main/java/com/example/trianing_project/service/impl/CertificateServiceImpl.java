package com.example.trianing_project.service.impl;

import com.example.trianing_project.domain.Certificate;
import com.example.trianing_project.repository.CertificateRepository;
import com.example.trianing_project.service.CertificateService;
import com.example.trianing_project.service.dto.CertificateDTO;
import com.example.trianing_project.service.mapper.CertificateMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateMapper certificateMapper;
    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateMapper certificateMapper, CertificateRepository certificateRepository) {
        this.certificateMapper = certificateMapper;
        this.certificateRepository = certificateRepository;
    }

    @Override
    public List<CertificateDTO> findAllByEmployeeId(Long employeeId) {
        return certificateMapper.toDto(certificateRepository.findAllByEmployeeId(employeeId));
    }

    @Override
    public CertificateDTO save(CertificateDTO certificateDTO) {
        Certificate certificate = certificateMapper.toEntity(certificateDTO);
        certificateRepository.save(certificate);
        return certificateMapper.toDto(certificate);
    }

    @Override
    public void delete(Long id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public Optional<CertificateDTO> findOne(Long id) {
        return certificateRepository.findById(id).map(certificateMapper::toDto);
    }
}
