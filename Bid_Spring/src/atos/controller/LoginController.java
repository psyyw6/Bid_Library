package atos.controller;

import atos.admain.UserVO;
import atos.admain.Userjson;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {
    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    @RequestMapping(value = "/login",method = GET)
    public String showLogin()
    {
        return "login";
    }

    @RequestMapping(value = "/login.do",method = POST)
    @ResponseBody
    public List<Userjson> logIn(HttpServletRequest request, @RequestParam String name, String password)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        UserVO loginvo = new UserVO();
        String new_password = RegisterController.EncoderByMd5(password);
        loginvo = userDao.selectByName(name, new_password);
        List<Userjson> staffjson = new ArrayList<Userjson>();
        Userjson response_json = new Userjson();
        if(loginvo!=null){
            response_json.setInfo("true");
            request.getSession().setAttribute("loginstaff",loginvo);
        }
        else{
            response_json.setInfo("false");
        }
       staffjson.add(response_json);
       return staffjson;

    }

    @RequestMapping(value="logout",method = GET)
    public String logout(HttpServletRequest request, ModelMap model){

      request.getSession().removeAttribute("loginstaff");
      request.getSession().invalidate();
      return "login";

    }

}
