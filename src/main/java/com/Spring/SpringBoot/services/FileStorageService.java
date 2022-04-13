package com.Spring.SpringBoot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    @Value("${upload.path}")
    private  String uploadPath;

    @PostConstruct//this will create an empty directory
    public void init()
    {
        try
        {
            Files.createDirectories(Paths.get(uploadPath));
        }catch(IOException e)
        {
         throw new RuntimeException("Could not create folder!!!"+e.getMessage());
        }
    }
    //storing the file
    public void save(MultipartFile file)
    {
        try{
            Path root =Paths.get(uploadPath);
            if(!Files.exists(root)){
                init();
            }
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()));
        }catch(Exception e)
        {
            throw new RuntimeException("Could not store file"+e.getMessage());
        }
    }
    //upload file
    public Resource load(String filename)
    {
        try{
            Path file=Paths.get(uploadPath).resolve(filename);
            Resource resource= (Resource) new UrlResource(file.toUri());
            return resource;
        }catch(Exception e)
        {
            throw new RuntimeException("Error: "+e.getMessage());
        }
    }

    //download file
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(fileName).normalize();
            Resource resource = (Resource) new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }

    //delete all files
    public  void deleteAll()
    {
        FileSystemUtils.deleteRecursively(Paths.get(uploadPath).toFile());
    }
    //list all files
    public List<Path> loadAll()
    {
        try{
           Path root=Paths.get(uploadPath);
           if(Files.exists(root)){
               return Files.walk(root, 1)
                       .filter(path -> !path.equals(root))
                       .collect(Collectors.toList());
           }
           return Collections.emptyList();
        }catch(IOException e)
        {
            throw new RuntimeException("Could not list the files!!");
        }
    }
}
