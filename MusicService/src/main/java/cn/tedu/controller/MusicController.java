package cn.tedu.controller;

import cn.tedu.pojo.Music;
import cn.tedu.service.MusicService;
import cn.tedu.utils.JsonUtil;
import cn.tedu.utils.SerializeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("music")
public class MusicController {
    @Autowired
    MusicService service = null;
    @Autowired
    JedisCluster jedis = null;

    @RequestMapping("query")
    public String queryMusic(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        Music music = service.queryMusic(id);
        HttpSession session = request.getSession();
        session.setAttribute("music", music);
        return "1";

    }

    @RequestMapping("listen")
    public String listen(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Music music = (Music) session.getAttribute("music");
        try {
            return JsonUtil.ToJsonMusic(music);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @RequestMapping("list")
    public List<Music> Music_list() {
//        try {
//            List<Music> list = new ArrayList<>();
//
//            for (int i = 0; i <= 11; i++) {
//                Music music = (Music) SerializeUtil.unSerialize(jedis.get(("indexList" + i).getBytes()));
//                list.add(music);
//            }
//            return list;
//        } catch (Exception e) {
            List<Music> list1 = service.MusicList();
            for (int i = 0; i < list1.size(); i++) {
                jedis.set((("indexList") + i).getBytes(), SerializeUtil.serialize(list1.get(i)));
            }
            return list1;
            //如果redis炸了，就搜数据库
//        }

    }

    @RequestMapping("liuxing")
    public List<Music> MusicLiuxing() {
        return service.MusicList01();
    }

    @RequestMapping("gufeng")
    public List<Music> Musicgufeng() {
        return service.MusicListgufeng();
    }

    @RequestMapping("yaogun")
    public List<Music> Musicyaogun() {
        return service.MusicListyaogun();
    }

    @RequestMapping("huayu")
    public List<Music> Musichuayu() {
        return service.MusicListhuayu();
    }

    @RequestMapping("yueyu")
    public List<Music> Musicyueyu() {
        return service.MusicListyueyu();
    }


    @RequestMapping("getuserlove")
    public List<Music> getUserLove(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("userid".equals(cookie.getName())) {
                String userid = cookie.getValue();
                List<Music> userLove = service.getUserLove(userid);
                return userLove;//TODO 前端的我的喜欢的列表
            }
        }
        return null;
    }

    @RequestMapping("delmylove")
    public void delMyLove(String mid, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userid")) {
                String uid = cookie.getValue();
                service.delMyLove(mid, uid);
            }
        }
    }

    @RequestMapping("addmylove")
    public void addMyLove(String mid, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userid")) {
                String uid = cookie.getValue();
                service.addMyLove(mid, uid);
            }
        }
    }


}
