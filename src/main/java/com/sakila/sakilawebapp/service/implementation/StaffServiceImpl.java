package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.entity.Staff;
import com.sakila.sakilawebapp.repository.StaffRepository;
import com.sakila.sakilawebapp.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public boolean validateLogin(String username, String password) {
        Staff staff = staffRepository.findByUsername(username);
        return staff != null && staff.getPassword().equals(password);
    }


}
