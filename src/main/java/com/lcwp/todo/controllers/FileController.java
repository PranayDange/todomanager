package com.lcwp.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
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

    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response) {
        try {
            InputStream fileInputStream = new FileInputStream("images/download.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
