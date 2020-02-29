package cn.tedu.service;

import cn.tedu.mapper.BackMapper;
import cn.tedu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BackService {
    @Autowired
    BackMapper mapper = null;
    public void addmusic(String name, String songs, String imgpath, String songpath) {
    mapper.addmusic(name,songs,imgpath,songpath);
    }

    public String querymusicid(String name, String songs, String imgpath, String songpath) {
        return mapper.querymusicid(name,songs,imgpath,songpath);
    }

    public void addtype(String id, String leixing) {
        mapper.addtype(id,leixing);
    }
}
