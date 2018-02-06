package atos.controller;

import atos.admain.UserVO;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    private boolean checkFile(String fileName){
        String suffixList = "txt";
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length());
        if(suffixList.contains(fileSuffix.trim().toLowerCase())){
            return true;
        }
        return false;
    }

    @RequestMapping(value="/administer_solution", method = GET)
    public String showSolution(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            model.addAttribute("name",loginstaff.getName());
        }
        return "administer_solution";
    }
    @RequestMapping(value="/modify", method = GET)
    public String modifySolution(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            model.addAttribute("name",loginstaff.getName());
        }
        return "modify";
    }

    @RequestMapping(value = "/add_solution",method = GET)
    public String addSolutionPage(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            model.addAttribute("name",loginstaff.getName());
        }
        return "add_solution";
    }

    @RequestMapping(value = "/upload.do",method = POST)
    public String upLoadFile(@RequestParam MultipartFile myfile,HttpServletRequest request) throws IOException{
        String fileName = myfile.getOriginalFilename();
        System.out.println(fileName);
        if(!checkFile(fileName)){
            return "error";
        }
        else{
            InputStream file = myfile.getInputStream();
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                System.out.println(lineTxt);
            }
            return "administer_solution";
        }
    }

}
