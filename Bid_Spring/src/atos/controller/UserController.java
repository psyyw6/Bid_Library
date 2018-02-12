package atos.controller;

import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {
    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @RequestMapping(value = "/search", method = GET)
    public String searchPage(HttpServletRequest request, ModelMap model){
        return "search";
    }

    @RequestMapping(value="/staff_search", method = GET)
    public String staffSearchPage(HttpServletRequest request, ModelMap model) {
        return "staff_search";
    }

}
