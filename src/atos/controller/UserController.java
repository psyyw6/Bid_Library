package atos.controller;

import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
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



    @RequestMapping(value="/staff_search", method = GET)
    public String staffSearchPage(HttpServletRequest request, ModelMap model) {
        return "staff_search";
    }

    @RequestMapping(value="/search.do", method = GET)
//    @ResponseBody
    public String staffSearchResultPage(HttpServletRequest request, ModelMap model) {
        String searchArea = request.getParameter("search_area");
        String keyword = request.getParameter("keyword");
        String[] multiKeyword = keyword.split("\\|");
        List<SolutionVO> contentList = new ArrayList<SolutionVO>();
        for(int i=0; i<multiKeyword.length; i++) {
            if (searchArea.equals("everything")) {
                contentList.addAll(solutionDao.selectContentByEverything(multiKeyword[i]));
            } else {
                contentList.addAll(solutionDao.selectContentByKeyword(searchArea, multiKeyword[i]));
            }
        }

        if(!contentList.isEmpty()){
//            check duplicate result
            model.addAttribute("solution_list",contentList);
            return "staff_search";
        }
        else{
            return "error";
        }

    }





//    @RequestMapping(value = "/search.do",method = POST)
//    public String SearchSolution(HttpServletRequest request, ModelMap model){
//        String keyword = request.getParameter("searchKeyword");
//        List<SolutionVO> search_solution_list = solutionDao.selectByKeyword(keyword);
//        if(search_solution_list!=null){
//            model.addAttribute("search_solution",search_solution_list);
//            return "staff_search";
//        }
//        else{
//            return "staff_search";
//        }
//    }


}
