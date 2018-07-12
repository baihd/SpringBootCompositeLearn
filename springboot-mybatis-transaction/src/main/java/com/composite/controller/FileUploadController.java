package com.composite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String fileUpload() {
        return "/fileUpload";
    }

    @RequestMapping(value = "/multiFileUpload", method = RequestMethod.GET)
    public String multiFileUpload() {
        return "/multiFileUpload";
    }

    @RequestMapping(value = "/upload/file", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件不存在";
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            return "上传失败," + e.getMessage();
        }
        return "上传成功";
    }

    @RequestMapping(value = "/upload/files", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFiles(@RequestParam("file") MultipartFile[] files) {
        if (files.length == 0) {
            return "上传文件不存在";
        }
        BufferedOutputStream stream = null;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            try {
                byte[] bytes = file.getBytes();
                stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                stream = null;
                return "You failed to upload " + " => " + e.getMessage();
            }
        }
        return "upload successful";
    }

}
