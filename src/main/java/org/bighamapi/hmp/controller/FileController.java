package org.bighamapi.hmp.controller;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.util.QCOSUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public Result addFile(@RequestParam(value = "file[]") MultipartFile file){
        String fileName = file.getOriginalFilename();

        String name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

//        CommonsMultipartFile cf= (CommonsMultipartFile) file;
//        org.apache.commons.fileupload.FileItem fileItem = cf.getFileItem();//(DiskFileItem) DiskFileItem fi
//
//        File f = fi.getStoreLocation();
        File newFile;
        try {
            newFile = File.createTempFile("tmp", null);
            file.transferTo(newFile);
            newFile.deleteOnExit();
            String url = QCOSUtil.uploadFile(newFile, name);
            System.out.println(url);
            Map<String,Object> data = new HashMap<>();
            data.put("file",fileName);
            data.put("url",url);
            return new Result(true, StatusCode.OK,"请求成功",data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"上传失败");
    }
}
