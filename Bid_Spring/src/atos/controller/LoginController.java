package atos.controller;

import atos.admain.UserVO;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/login.do",method = GET)
    public String logIn(HttpServletRequest request, ModelMap model) {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        UserVO loginvo = userDao.selectByName(name, pwd);
        if(loginvo!= null){
        model.addAttribute("name",name);
        return "hello";
        }
        else {
            return "login";
        }

    }


}
