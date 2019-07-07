package com.pinyougou.shop.controller;

import com.pinyougou.common.util.FastDFSClient;
import entity.PygResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Value("${IMAGE_SERVER_BASE_USL}")
    private String IMAGE_SERVER_BASE_USL;

    //此处file变量名一定要和html页面中的名称一致。否则报空指针。
    @RequestMapping("/upload")
    public PygResult upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/client.conf");
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            return new PygResult(true,IMAGE_SERVER_BASE_USL+url);
        } catch (Exception e) {
            e.printStackTrace();
            return new PygResult(false,"上传失败!");
        }
    }
}
