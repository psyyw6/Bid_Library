package atos.controller;

import atos.admain.UserVO;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
        UserVO loginvo = new UserVO();
        loginvo = userDao.selectByName(name, pwd);
        request.getSession().setAttribute("loginstaff",loginvo);
        UserVO loginstaff = (UserVO)request.getSession().getAttribute("loginstaff");
        if(loginvo!= null){
        model.addAttribute("name",loginstaff.getName());
        return "administer_solution";
        }
        else {
            return "login";
        }

    }

    @RequestMapping(value="logout",method = GET)
    public String logout(HttpServletRequest request, ModelMap model){

      request.getSession().removeAttribute("loginstaff");
      request.getSession().invalidate();
      return "login";

    }

}
