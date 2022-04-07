package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/File-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file)
    {
        //System.out.println(file.getOriginalFilename());
        //System.out.println(file.getSize());
        //System.out.println(file.getContentType());
        //System.out.println(file.getName());

        try {
            //if file is empty validation
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
            }
            /*
            //if file is not jpeg or gif
            if (!file.getContentType().equals("images/jpg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File must be jpg");
            }*/
           boolean f= fileUploadHelper.uploadFile(file);
            if(f)
            {
                //return ResponseEntity.ok("file uploaded successfully");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //file upload
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong! Try again");
    }
}
