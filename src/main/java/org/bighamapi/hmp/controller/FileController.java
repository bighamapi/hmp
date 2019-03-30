package org.bighamapi.hmp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @PostMapping
    public Map<String, Object> addFile(){
        Map<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","请求成功");
        Map<String,Object> data = new HashMap<>();
        data.put("url","https://segmentfault.com/img/bVEo3w?w=1200&h=2800");
        map.put("data",data);
        return map;
    }
}
