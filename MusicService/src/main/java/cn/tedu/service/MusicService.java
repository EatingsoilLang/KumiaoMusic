package cn.tedu.service;

import cn.tedu.mapper.MusicMapper;
import cn.tedu.pojo.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    MusicMapper mapper = null;

    /**
     * 查询单首歌
     * @param id 歌曲id
     * @return 查到的music对象
     */
    public Music queryMusic(int id){
        return mapper.queryMusic(id);
    }

    /**
     *
     * @return 歌曲对象的列表
     */
    public List<Music> MusicList(){return mapper.MUSIC_LIST();};

    public List<Music> MusicList01() {
        return mapper.MUSIC_LIST01();
    }

    public Music queryLiuXing(int id) {
        return mapper.queryLiuXing(id);
    }

    public List<Music> getUserLove(String userid) {
       return mapper.getUserLove(userid);
    }

    public void delMyLove(String mid, String uid) {
        mapper.delMyLove(mid,uid);
    }

    public void addMyLove(String mid, String uid) {
        mapper.addMyLove(mid,uid);
    }

    public List<Music> MusicListgufeng() {
        return mapper.MusicListgufeng();
    }

    public List<Music> MusicListyaogun() {
        return mapper.MusicListyaogun();
    }

    public List<Music> MusicListhuayu() {
        return mapper.MusicListhuayu();
    }

    public List<Music> MusicListyueyu() {
        return mapper.MusicListyueyu();
    }
}
