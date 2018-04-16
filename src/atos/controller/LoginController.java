package atos.controller;

import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.admain.UserVO;
import atos.admain.Userjson;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import atos.exceptions.NoSearchResultException;
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

    @Resource
    private SolutionDao solutionDao;

    public void setSolutionDao(SolutionDao solutionDao) {this.solutionDao = solutionDao;}

    public SolutionDao getSolutionDao() {
        return solutionDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public UserDao getUserDao() {
        return userDao;
    }

    @RequestMapping(value = "/login",method = GET)
    public String showLogin(HttpServletRequest request,ModelMap model)
    {
        //If the user already log in, return the corresponding page according to his role.
        if(request.getSession().getAttribute("loginstaff")!=null) {
                UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
                 model.addAttribute("name",loginstaff.getName());
                if(!loginstaff.getRole()){
                    model.addAttribute("role","User");
                    List<SolutionVO> contentList = solutionDao.selectAll();
                    if (!contentList.isEmpty()) {
                        model.addAttribute("content_list", contentList);
                    } else {
                        throw new NoSearchResultException("No Available Content", "Sorry, there is no content now");
                    }
                    for (int i = 0; i < contentList.size(); i++) {
                        String content_title = contentList.get(i).getContent_title();
                        String type = contentList.get(i).getIsExternal();
                        List<SectionVO> sectionList = solutionDao.selectInUseSection(content_title,type);
                        model.addAttribute("section" + i, sectionList);
                    }
                    return "staff_search";
                }
            List<SolutionVO> content_list = solutionDao.selectAll();
            model.addAttribute("content_list",content_list);
            return "administer_solution";
        }
        return "login";
    }

    @RequestMapping(value = "/login.do",method = POST)
    @ResponseBody
    public List<Userjson> logIn(HttpServletRequest request, @RequestParam String name, String password)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String new_password = RegisterController.EncoderByMd5(password);
        UserVO loginvo = userDao.selectByName(name, new_password);
        List<Userjson> staffjson = new ArrayList<Userjson>();
        Userjson response_json = new Userjson();
        if(loginvo!=null){
            if(loginvo.getRole()){
                response_json.setInfo("admin");
            }
            else{
                response_json.setInfo("staff");
            }
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
