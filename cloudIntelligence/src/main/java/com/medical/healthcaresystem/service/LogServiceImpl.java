package com.medical.healthcaresystem.service;

import com.medical.healthcaresystem.entity.Log;
import com.medical.healthcaresystem.repository.LogRepository;
import com.medical.healthcaresystem.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void log(String username, String action) {
        Log log = new Log(username, action);
        logRepository.save(log);
    }
}
