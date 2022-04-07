package com.Spring.SpringBoot.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    //public final String Upload_Dir="C:\\Users\\Muskan\\Downloads\\SpringBoot\\src\\main\\resources\\static\\images";
    public final String Upload_Dir=new ClassPathResource("static/images/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipart)
    {
        boolean f=false;

        try {
            //reading file from server......
            //InputStream is=multipart.getInputStream();
            //byte data[]=new byte[is.available()];
            //is.read(data);

            //writting into our file.......
            //FileOutputStream fos=new FileOutputStream(Upload_Dir+File.separator+multipart.getOriginalFilename());
            //fos.write(data);
            //fos.close();


            Files.copy(multipart.getInputStream(), Paths.get(Upload_Dir+ File.separator+multipart.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

}
