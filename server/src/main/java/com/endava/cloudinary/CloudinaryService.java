package com.endava.cloudinary;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Component
@Slf4j
public class CloudinaryService {

    private Cloudinary cloudinary;

    @Autowired
    private Environment env;

    @Bean
    public Cloudinary cloudinaryConfig() {
        Map<String, String> config = new HashMap();
        config.put("cloud_name", env.getProperty("cloudinary.cloud_name"));
        config.put("api_key", env.getProperty("cloudinary.api_key"));
        config.put("api_secret", env.getProperty("cloudinary.api_secret"));
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }


    public String uploadFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map<?,?> uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted) {
                log.info("File successfully deleted");
            } else
                log.info("File doesn't exist");
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) {
        try {
            File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
