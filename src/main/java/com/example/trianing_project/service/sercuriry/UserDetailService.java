package com.example.trianing_project.service.sercuriry;

import com.example.trianing_project.domain.Employee;
import com.example.trianing_project.repository.EmployeeRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public UserDetailService( EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(usernameOrEmail);
        if (employee != null) {
            return new org.springframework.security.core.userdetails.User(employee.getEmail()
                    , new BCryptPasswordEncoder().encode(employee.getPassword()),
                    employee.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getRole_name()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}


