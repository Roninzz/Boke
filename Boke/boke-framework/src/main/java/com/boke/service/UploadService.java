package com.boke.service;

import com.boke.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author DreamRay
 * @date 2023/8/24 14:01
 * @mood happy
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
