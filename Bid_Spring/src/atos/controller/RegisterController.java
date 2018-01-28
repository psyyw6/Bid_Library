package atos.controller;

import atos.admain.Userjson;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

        if(!pwd.equals(cf_pwd)) {
            return "register";
        }
        userDao.registerUser(name,pwd,email);
        return "login";
    }

    @RequestMapping(value = "/checkUser.do",method = POST)
        @ResponseBody
        public List<Userjson> CheckUser(@RequestParam String username) {
            List<Userjson> userList = new ArrayList<Userjson>();
            Userjson re_info = new Userjson();
            if(userDao.checkDuplicate(username)!=null)
            {
                re_info.setInfo("yes");
                userList.add(re_info);
                System.out.println(userList.get(0).getInfo());
                return userList;
            }
            else{
                re_info.setInfo("no");
                userList.add(re_info);
                System.out.println(userList.get(0).getInfo());
                return userList;
            }

        }

}
