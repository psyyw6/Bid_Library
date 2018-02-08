package atos.controller;

import atos.admain.SolutionVO;
import atos.admain.UserVO;
import atos.dao.SolutionDao;
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
    @Resource
    private SolutionDao solutionDao;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setSolutionDao(SolutionDao solutionDao) {
        this.solutionDao = solutionDao;
    }

    public SolutionDao getSolutionDao() {
        return solutionDao;
    }

    private boolean checkFile(String fileName){
        String suffixList = "txt";
        String fileSuffix = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length());
        if(suffixList.contains(fileSuffix.trim().toLowerCase())){
            return true;
        }
        return false;
    }

    private String convertSqlDate(String date){
        String[] temp = date.split("/",3);
        String day = temp[1];
        String month = temp[0];
        String year = temp[2];
        String sql_date = temp[2]+"-"+temp[0]+"-"+temp[1];
        return sql_date;
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
        String solution_title = request.getParameter("solution_title");
        String customer_name = request.getParameter("customer_name");
        String expired_date = convertSqlDate(request.getParameter("expired_date"));
        String version = request.getParameter("version");
        String creator = request.getParameter("creator");
        String type = request.getParameter("isExternal");
        String heading = "Heading 1";
        double real_version = Double.parseDouble(version);
        boolean isExternal;
        if(type.equals("external")){
            isExternal = true;
        }else{
            isExternal = false;
        }
        if(!checkFile(fileName)||fileName == ""){
            return "error";
        }
        else {
            InputStream file = myfile.getInputStream();
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            String lineText = null;
            String fileContent = "";
            while ((lineText = br.readLine()) != null) {
                lineText += "\n";
                fileContent += lineText;
            }
            if(solutionDao.storeSolution(solution_title,creator,heading,customer_name,expired_date,isExternal,fileContent,real_version)==1){
            return "administer_solution";
            }else{
                return "error";
            }
        }

    }

    @RequestMapping(value = "/edit",method = GET)
    public String editSolution(HttpServletRequest request,ModelMap model){
        String solution_title = "Test Solution";
        String heading = "Heading 1";
        SolutionVO solution_1 = solutionDao.getContent(heading);
        if(solution_1!=null){
            String content = solution_1.getContent();
//            String content2 = "abcd\nefgh";
            model.addAttribute("content",content);
            return "edit";
        }
        else{
            return "error";
        }
    }

}
