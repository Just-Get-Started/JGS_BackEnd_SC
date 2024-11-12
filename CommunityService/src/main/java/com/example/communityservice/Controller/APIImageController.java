package com.example.communityservice.Controller;

import com.example.communityservice.Exception.BusinessLogicException;
import com.example.communityservice.Exception.ExceptionTypes.ImageExceptionType;
import com.example.communityservice.Service.APIImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class APIImageController {
    private final APIImageService apiImageService;

    @PostMapping
    public ResponseEntity<URL> saveImage(@RequestPart(value = "image") MultipartFile image) {
        if(image.isEmpty()) throw new BusinessLogicException(ImageExceptionType.IMAGE_IS_NOT_NULL);
        URL url = apiImageService.saveImage(image);
        return ResponseEntity.status(HttpStatus.OK).body(url);
    }
}
