package atos.controller;

import atos.admain.Userjson;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RegisterController {

    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public static String EncoderByMd5(String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String new_pwd = base64Encoder.encode(md5.digest(pwd.getBytes("utf-8")));
        return new_pwd;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showReg() {
        return "register";
    }

    @RequestMapping(value = "/reg.do",method = POST)
        @ResponseBody
        public List<Userjson> register_user(@RequestParam String username,String pwd,String email)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("hhhhh");
        List<Userjson> userList = new ArrayList<Userjson>();
        Userjson re_info = new Userjson();
            String new_pwd = this.EncoderByMd5(pwd);
            if(userDao.registerUser(username,new_pwd,email)!=1) {
                re_info.setInfo("false");
                userList.add(re_info);
                return userList;
            }
            else {
                re_info.setInfo("true");
                userList.add(re_info);
                return userList;
            }


    }

    @RequestMapping(value = "/checkUser.do",method = POST)
        @ResponseBody
        public List<Userjson> CheckUser(@RequestParam String username) {
            List<Userjson> userList = new ArrayList<Userjson>();
            Userjson re_info = new Userjson();
            System.out.println("hhhhh");
            if(userDao.checkDuplicate(username)!=null)
            {
                re_info.setInfo("yes");
                userList.add(re_info);
                return userList;
            }
            else{
                re_info.setInfo("no");
                userList.add(re_info);
                return userList;
            }

        }

}
