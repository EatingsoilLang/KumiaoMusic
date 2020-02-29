package cn.tedu.mapper;

import cn.tedu.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BackMapper {
    void addmusic(@Param("name") String name, @Param("writer") String songs,@Param("picture") String imgpath,@Param("adds") String songpath);

    String querymusicid(@Param("name") String name, @Param("writer") String songs,@Param("picture") String imgpath,@Param("adds") String songpath);

    void addtype(@Param("id") String id, @Param("leixing") String leixing);
}
