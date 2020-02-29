package cn.tedu.utils;

import cn.tedu.pojo.Music;
import cn.tedu.pojo.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static String ToJsonMusic(Music music) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        return mapper.writeValueAsString(music);
    }
}
