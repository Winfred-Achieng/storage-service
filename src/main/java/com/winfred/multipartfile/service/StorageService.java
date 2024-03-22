package com.winfred.multipartfile.service;

import com.winfred.multipartfile.entity.ImageData;
import com.winfred.multipartfile.repository.StorageRepository;
import com.winfred.multipartfile.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public  String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        if(imageData != null){
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String filename){
        Optional<ImageData> imageData = repository.findImageByName(filename);

        byte[] images = ImageUtils.decompressImage(imageData.get().getImageData());

        return images;
    }


}
