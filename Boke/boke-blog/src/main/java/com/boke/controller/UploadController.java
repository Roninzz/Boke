package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author DreamRay
 * @date 2023/8/24 13:58
 * @mood happy
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam(value = "file") MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
