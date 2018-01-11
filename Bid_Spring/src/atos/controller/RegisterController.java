package atos.controller;

import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/register", method = GET)
    public String showReg()
    {
        return "register";
    }

    @RequestMapping(value = "/reg.do",method = GET)
    public String RegUser(HttpServletRequest request, ModelMap model) {
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        String cf_pwd = request.getParameter("cf_password");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        if(!pwd.equals(cf_pwd)) {
            return "register";
        }
//        userDao.RegUsers(name,pwd,email,department);
        return "login";
    }

}
