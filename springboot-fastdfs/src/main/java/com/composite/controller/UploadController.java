package com.composite.controller;

import com.composite.fastdfs.FastDFSClient;
import com.composite.fastdfs.FastDFSFile;
import org.csource.fastdfs.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed", e);
        }
        return "redirect:/uploadStatus";
    }

    @PostMapping("/delete")
    public void singleFileDelete() {
        try {
            deleteFile("group1", "M00/00/00/CgAQMltG80aARZ6AAABdrSqbHGQ832_big.jpg");
        } catch (Exception e) {
            logger.error("delete file failed", e);
        }
    }

    @PostMapping("/getFileInfo")
    public void singleFileInfo() {
        try {
            getFileInfo("group1", "M00/00/00/CgAQMltG80aARZ6AAABdrSqbHGQ832_big.jpg");
        } catch (Exception e) {
            logger.error("down file failed", e);
        }
    }

    @PostMapping("/down")
    public void singleFileDown() {
        try {
            downFile("group1", "M00/00/00/CgAQMltG80aARZ6AAABdrSqbHGQ832_big.jpg", "/opt/soft/software/testfile/1.jpg");
        } catch (Exception e) {
            logger.error("down file failed", e);
        }
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
        }
        if (fileAbsolutePath == null) {
            logger.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }

    private FileInfo getFileInfo(String groupName, String remoteFileName) {
        FileInfo fileInfo = FastDFSClient.getFile(groupName, remoteFileName);
        return fileInfo;
    }

    private void downFile(String groupName, String remoteFileName, String destination) throws IOException {
        InputStream inputStream = FastDFSClient.downFile(groupName, remoteFileName);
        writeToLocal(destination, inputStream);
    }

    /**
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    private void deleteFile(String groupName, String remoteFileName) throws Exception {
        FastDFSClient.deleteFile(groupName, remoteFileName);
    }

    private static void writeToLocal(String destination, InputStream inputStream) throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = inputStream.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        inputStream.close();
    }


}
