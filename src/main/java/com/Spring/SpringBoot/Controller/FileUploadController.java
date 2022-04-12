package com.Spring.SpringBoot.Controller;
import com.Spring.SpringBoot.helper.FileUploadHelper;
import com.Spring.SpringBoot.message.ResponseFile;
import com.Spring.SpringBoot.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {
    @Autowired
   // private FileUploadHelper fileUploadHelper;
    private FileStorageService fileStorageService;

    @PostMapping("/File-upload")
    ResponseFile uploadFile(@RequestParam("file")MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String url=ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloads")
                .path(fileName).toUriString();
        String contentType=file.getContentType();
        ResponseFile response=new ResponseFile(fileName,contentType, url);
        return response;
    }

}
