package com.learn.bigevent.controller;

import com.learn.bigevent.pojo.Result;
import com.learn.bigevent.utils.TencentCosUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {


    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        // UUID to prevent files with the same name from overwriting each other
        String filename =  UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("E:\\Projects\\temp\\" +  filename));
        String url = TencentCosUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
