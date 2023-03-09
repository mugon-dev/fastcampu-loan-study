package com.conny.loan.service;

import com.conny.loan.exception.BaseException;
import com.conny.loan.exception.ResultType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FIleStorageServiceImpl implements FIleStorageService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(),
                Paths.get(uploadPath).resolve(file.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = Paths.get(uploadPath).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.isReadable() || resource.exists()) {
                return resource;
            } else {
                throw new BaseException(ResultType.NOT_EXIST);
            }
        } catch (Exception e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(Paths.get(uploadPath), 1)
                        .filter(path -> !path.equals(Paths.get(uploadPath)));
        } catch (Exception e) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
    }
}
