package cn.tedu.mapper;

import cn.tedu.pojo.Music;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchMapper {

    List<Music> selectMusic();
}
