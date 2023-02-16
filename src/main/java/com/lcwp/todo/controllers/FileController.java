package com.lcwp.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    //image is key here
    public String uploadSingle(@RequestParam("image") MultipartFile file) {
        logger.info("Name: {}", file.getName());
        logger.info("Content Type: {}", file.getContentType());
        logger.info("Original file name {}", file.getOriginalFilename());
        logger.info("File Size : {}", file.getSize());

       /* InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("data.png");
        byte data[] = new byte[inputStream.available()];
        fileOutputStream.write(data);*/

        return "File test";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files) {

        Arrays.stream(files).forEach(f -> {
            logger.info("Original file name {}", f.getOriginalFilename());
            logger.info("File Type: {}", f.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++");
        });

        return "Handling multiple files";
    }
}
