package com.example.controller;

import com.example.client.FastdfsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(description = "fastdfs接口api")
@RequestMapping("/fdfs")
public class FdfsApi {
    @Autowired
    private FastdfsClient fastdfsClient;

    @RequestMapping("/uploadPage")
    public String uploadPage() {
        return "/index";
    }

    @ApiOperation("文件上传")
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) throws Exception {
        String url = fastdfsClient.uploadFile(file);
        System.out.println(url);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "文件上传成功");
        result.put("url", url);
        return result;
    }

    @ApiOperation("文件下载")
    @RequestMapping("/download")
    public void download(String fileUrl, HttpServletResponse response) throws IOException {
        byte[] download = fastdfsClient.download(fileUrl);
        //直接从fileUrl提取出文件名（包含后缀）
        String name = extractName(fileUrl);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));

        // 写出
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.write(download, outputStream);

    }


    @ApiOperation("删除文件")
    @RequestMapping("deleteFile")
    public Map<String, Object> deleteFile(String fileUrl) {
        Map<String, Object> result = new HashMap<>();

        try {
            fastdfsClient.deleteFile(fileUrl);
            result.put("code", 200);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            result.put("code", 201);
            result.put("msg", "删除失败");
        }
        return result;
    }

    /**
     * 从fileUrl中提取出文件名及后缀
     *
     * @param fileUrl
     * @return
     */
    public String extractName(String fileUrl) {
        String name = fileUrl.substring(fileUrl.lastIndexOf("_") + 1);
        if (StringUtils.isBlank(name)) {
            name = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        }
        return name;
    }
}
