package atos.controller;

import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class LoginController {
    private UserDao userDao;

    @RequestMapping(value = "/login",method = GET)
    public String showLogin()
    {
        return "login";
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
