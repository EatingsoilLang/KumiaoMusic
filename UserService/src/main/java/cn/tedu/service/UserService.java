package cn.tedu.service;

import cn.tedu.mapper.UserMapper;
import cn.tedu.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper = null;
    public Users getFromUsername(String username) {
        Users users = userMapper.getFromUsername(username);
        return users;
    }


    public void addUsers(String username, String password, String nickname, String email) {
        userMapper.addUsers(username,password,nickname,email);
    }

    public Users checkUsernamePassword(String username, String password) {
        return userMapper.checkUsernamePassword(username,password);
    }

    public Users getuserfromid(String userid) {
       return userMapper.getuserfromid(userid);
    }
}
