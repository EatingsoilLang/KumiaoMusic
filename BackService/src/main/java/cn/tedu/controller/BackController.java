package cn.tedu.controller;

import cn.tedu.pojo.Users;
import cn.tedu.service.BackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RestController
@RequestMapping("back")
public class BackController {
    @Autowired
    BackService service = null;


    @RequestMapping("savefile")
    public String SaveFile(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("songs") String songs, @RequestParam("uploadFile") MultipartFile[] file) throws UnsupportedEncodingException {
        String leixing = null;
        switch (type) {
            case "1":
                leixing = "liuxing";
                break;
            case "2":
                leixing = "gufeng";
                break;
            case "3":
                leixing = "yaogun";
                break;
            case "4":
                leixing = "huayu";
                break;
            case "5":
                leixing = "yueyu";
                break;
        }


        if (file.length < 2) {
            return "false";
        }

        String fileName1 = file[0].getOriginalFilename().trim();
        String fileName2 = file[1].getOriginalFilename().trim();

        String[]arr = fileName1.split("\\.");
        String last = arr[arr.length - 1];
        fileName1 = fileName1.hashCode()+"."+last;


        String[] arr1 = fileName2.split("\\.");
        String last1 = arr1[arr1.length - 1];
        fileName2 = fileName2.hashCode()+"."+last1;

        String path1 = "C:\\Users\\ASUS\\Desktop\\nginxMUSIC\\music\\data\\pic\\";
        String path2 = "C:\\Users\\ASUS\\Desktop\\nginxMUSIC\\music\\data\\song\\";
        File dest1 = new File(new File(path1).getAbsolutePath()+ "/" + fileName1);
        File dest2 = new File(new File(path2).getAbsolutePath()+ "/" + fileName2);
        if (!dest1.getParentFile().exists()) {
            dest1.getParentFile().mkdirs();
        }
        if (!dest2.getParentFile().exists()) {
            dest2.getParentFile().mkdirs();
        }

        try {
//            传入数据库
//            封装路径
            String imgpath = "data/pic/"+fileName1;
            String songpath = "data/song/"+fileName2;
            service.addmusic(name,songs,imgpath,songpath);
            String id = service.querymusicid(name,songs,imgpath,songpath);
            service.addtype(id,leixing);
            file[0].transferTo(dest1); // 保存文件
            file[1].transferTo(dest2); // 保存文件
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}