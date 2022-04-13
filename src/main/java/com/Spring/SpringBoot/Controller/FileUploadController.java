package com.Spring.SpringBoot.Controller;
import com.Spring.SpringBoot.Dao.FileData;
import com.Spring.SpringBoot.message.ResponseMessage;
import com.Spring.SpringBoot.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("files")
public class FileUploadController {

    private final FileStorageService fileStorageService;
    @Autowired
    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @PostMapping
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) {
        try{
            fileStorageService.save(file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("File uploaded successfully:  "+file.getOriginalFilename()));
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseMessage("Could not upload the file: " + file.getOriginalFilename() + "!"));
        }
    }

    @GetMapping
    public  ResponseEntity<List<FileData>> getListFiles()
    {
        List<FileData> fileInfos=fileStorageService.loadAll()
                .stream()
                .map(this::pathToFileData)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(fileInfos);
    }

    @DeleteMapping
    public void delete()
    {
        fileStorageService.deleteAll();
    }

    private FileData pathToFileData(Path path) {
        FileData filedata=new FileData();
        String filename = path.getFileName().toString();
        filedata.setFilename(filename);
        filedata.setUrl(MvcUriComponentsBuilder
                .fromMethodName(FileUploadController.class,"getFile",filename)
                .build().toString());
        try{
            filedata.setSize(Files.size(path));
        }catch(IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Error: "+e.getMessage());
        }
        return filedata;
    }

    @GetMapping("{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename)
    {
        Resource file=fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders
                .CONTENT_DISPOSITION,"attachment; filename= \""+file.getFilename()+"\"")
                .body(file);
    }

}
