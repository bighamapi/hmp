package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

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
        System.out.println(name);
        Map<String,Object> data = new HashMap<>();
        data.put("file",fileName);
        data.put("url",name);
        return new Result(true, StatusCode.OK,"请求成功",data);
    }
}
