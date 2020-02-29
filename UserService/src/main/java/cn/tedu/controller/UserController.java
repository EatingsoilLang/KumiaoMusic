package cn.tedu.controller;

import cn.tedu.pojo.Users;
import cn.tedu.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService service = null;

    @Autowired
    JedisCluster jedis = null;


    /**
     * @注册功能
     * @param username2 用户名
     * @param password2 密码
     * @param nickname 昵称
     * @param email email
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public void regist(String username2, String password2, String nickname, String email, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (!("".equals(username2) || "".equals(password2) || "".equals(nickname) || "".equals(email))) {
            //后端的效验如果出错则不存
            String s = ajaxUser(username2);
            if (!s.equals("1")){
                service.addUsers(username2,password2,nickname,email);
            }
            HttpSession session = request.getSession();
            session.setAttribute("success", "1");
            response.sendRedirect("http://www.music.com");
        }
    }

    /**
     * @注册成功跳转功能
     * @return 注册成功跳转，否则返回none
     */
    @RequestMapping("registsuccess")
    public String registSuccess(HttpServletRequest request) {
        String msg = (String) request.getSession().getAttribute("success");
        if ("1".equals(msg)){
            request.getSession().removeAttribute("success");
            return "tiaozhuan";// TODO 存入的数据无误 finish
        }else{
            return "none";
        }
    }

    /**
     * @自动登录校验
     * @return 如果已经登录了就返回cookie中的nickname否则返回“none”
     */
    @RequestMapping(value = "logincheck")
    public String loginCheck(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("nickname".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "none";
    }


    /**
     * @用户注册时检测重名
     * @param username 用户名
     * @return 如果重名返回“1” 否则返回“0”
     */
    @RequestMapping("ajaxuser")
    public String ajaxUser(String username) {
        Users users = service.getFromUsername(username);
        if (users != null) {
            return "1";
        } else {
            return "0";
        }
    }
    @RequestMapping("getuserfromid")
    public Users getuserfromid(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if ("userid".equals(cookie.getName())) {
                String userid = cookie.getValue();
                Users getuserfromid = service.getuserfromid(userid);
                System.out.println(getuserfromid);
                return getuserfromid;
            }
        }
        return null;
    }




    /**
     * @登录功能
     * @param username  用户名
     * @param password  密码
     * @return 成功返回“1” 登录失败返回“0”
     */
    @RequestMapping("login")
    public String Login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws IOException {
        Users users = service.checkUsernamePassword(username,password);
        if (users!=null){

            Cookie cookie = new Cookie("nickname",users.getNickname());
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("userid",String.valueOf(users.getId()));
            cookie2.setPath("/");
            cookie2.setMaxAge(60*60);
            response.addCookie(cookie2);

            response.sendRedirect("http://www.music.com");
            return "1";
        }else{
            return "0";
        }

    }



    /**
     * @用户登出功能
     * @return ajax 登出返回“1”
     */
    @RequestMapping("logout")
    public String Logout(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Cookie cookie = new Cookie("nickname","1");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("userid","1");
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
        response.addCookie(cookie2);


        response.sendRedirect("http://www.music.com");
        return "1";
    }

}