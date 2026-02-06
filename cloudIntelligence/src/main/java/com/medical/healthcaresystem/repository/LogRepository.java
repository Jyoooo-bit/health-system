package com.medical.healthcaresystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.healthcaresystem.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
}
