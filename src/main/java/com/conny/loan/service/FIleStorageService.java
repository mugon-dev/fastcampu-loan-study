package com.conny.loan.service;

import org.springframework.web.multipart.MultipartFile;

public interface FIleStorageService {

    void save(MultipartFile file);

}
