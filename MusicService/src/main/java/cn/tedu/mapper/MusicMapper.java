package cn.tedu.mapper;

import cn.tedu.pojo.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicMapper {
    Music queryMusic(@Param("id") int id);

    List<Music> MUSIC_LIST();

    List<Music> MUSIC_LIST01();

    Music queryLiuXing(@Param("id") int id);

    List<Music> getUserLove(@Param("id") String userid);

    void delMyLove(@Param("mid") String mid, @Param("uid") String uid);

    void addMyLove(@Param("mid") String mid, @Param("uid") String uid);

    List<Music> MusicListgufeng();

    List<Music> MusicListyaogun();

    List<Music> MusicListhuayu();

    List<Music> MusicListyueyu();
}
