package cn.tedu.mapper;

import cn.tedu.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    Users getFromUsername(@Param("username") String username);

    void addUsers(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname, @Param("email") String email);

    Users checkUsernamePassword(@Param("username") String username, @Param("password") String password);

    Users getuserfromid(@Param("id") String userid);
}
