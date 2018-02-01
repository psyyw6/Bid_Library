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
public class AdminController {
    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @RequestMapping(value="/administer_solution", method = GET)
    public String showSolution(HttpServletRequest request, ModelMap model) {
        UserVO loginstaff = (UserVO)request.getSession().getAttribute("loginstaff");
        model.addAttribute("name",loginstaff.getName());
        return "administer_solution";}
}
