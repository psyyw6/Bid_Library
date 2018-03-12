package atos.controller;

import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.admain.Userjson;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import atos.wordExport.ExportWord;
import atos.exceptions.NoSearchResultException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<SolutionVO> contentList = solutionDao.selectAll();
        if(!contentList.isEmpty()){
            model.addAttribute("content_list",contentList);
        }
        else{
            throw new NoSearchResultException("No Available Content","Sorry, there is no content now");
        }
        for(int i=0;i<contentList.size();i++){
            String content_title = contentList.get(i).getContent_title();
            List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(content_title);
            model.addAttribute("section"+i,sectionList);
        }

        return "staff_search";

    }

    @RequestMapping(value="/search.do", method = POST)
    public String staffSearchResultPage(HttpServletRequest request, ModelMap model) {
        String searchArea = request.getParameter("tag");
        String keyword = request.getParameter("keyword");
        List<SolutionVO> resultContentList = new ArrayList<SolutionVO>();
        List<SectionVO> resultSectionList = new ArrayList<SectionVO>();
        switch (searchArea){
            case "Default":
                resultContentList =  solutionDao.selectByDefault(keyword);
                resultSectionList = solutionDao.searchMaxVersionByName(keyword);
                for(SectionVO resultS : resultSectionList){
                    int isExist = 0;
                    for(SolutionVO resultC : resultContentList){
                        if(resultS.getTitle().equals(resultC.getContent_title())){
                            isExist = 1;
                            break;
                        }
                    }
                    if(isExist == 0){
                        SolutionVO newContent = solutionDao.selectContentByTitle(resultS.getTitle());
                        resultContentList.add(newContent);
                    }
                }
                break;
            case "Content Title":
                resultContentList = solutionDao.searchByTitle(keyword);
                break;
            case "Author":
                resultContentList = solutionDao.searchByAuthor(keyword);
                break;
            case "Type":
                resultContentList = solutionDao.searchByType(keyword);
                break;
            case "Customer":
                resultContentList = solutionDao.searchByCustomer(keyword);
                break;
            case "Section Name":
                resultSectionList = solutionDao.searchMaxVersionByName(keyword);
                for(SectionVO resultS : resultSectionList){

                        SolutionVO newContent = solutionDao.selectContentByTitle(resultS.getTitle());
                        resultContentList.add(newContent);
                }

        }

        if(!resultContentList.isEmpty()){
            model.addAttribute("content_list",resultContentList);

            for(int i=0;i<resultContentList.size();i++){
                String content_title = resultContentList.get(i).getContent_title();
                List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(content_title);
                model.addAttribute("section"+i,sectionList);
            }
        }
        else{
            throw new NoSearchResultException("No Result", "Sorry, there is no matched result, please try another keyword");
        }

        return "staff_search";
    }

    @RequestMapping(value="success_generate",method = GET)
    public String showSuccessGenerate(HttpServletRequest request,ModelMap model) {
        return "success_generate";
    }

    @ExceptionHandler(NoSearchResultException.class)
    public ModelAndView handleAdministerExceptionException(HttpServletRequest request, NoSearchResultException ex){
        ModelAndView modelAndView = new ModelAndView("staff_error");
        modelAndView.addObject("errCode", ex.getErrCode());
        modelAndView.addObject("errMsg", ex.getErrMsg());
        return modelAndView;
    }

    @RequestMapping(value="userViewDetail.do",method = GET)
    public String userViewDetail(HttpServletRequest request,ModelMap model){
        String content_title = request.getParameter("content_title");
        String section_name = request.getParameter("section_name");
        String str_version = request.getParameter("version");
        if(content_title==null||section_name==null||str_version==null){
            return "staff_search";
        }
        int version = Integer.parseInt(str_version);
        SectionVO requestSection = solutionDao.selectSectionByName(content_title,section_name,version);
        String details = requestSection.getSection_details().replaceAll("\n","</p><p>");
        model.addAttribute("content_title",content_title);
        model.addAttribute("version",version);
        model.addAttribute("section_name",section_name);
        model.addAttribute("content",details);

        return "UserDetail";
    }

    @RequestMapping(value="export_word",method = POST)
    @ResponseBody
    public List<Userjson> exportWord(HttpServletRequest request, ModelMap model, @RequestParam (required = false, value = "list[]") List<String> list){

        List<SolutionVO> targetContents = new ArrayList<SolutionVO>();
        List<Userjson> response = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        response.add(jsonInfo);
        for(String content_title: list){
            SolutionVO temp = solutionDao.selectContentByTitle(content_title);
            targetContents.add(temp);
        }
        try{
            String templatePath = "/atos/ftl/";
            String templateName = "MI008.ftl";
            String filePath = "/Users/realmadrid/Desktop/";
            String filename = "test1.doc";
            ExportWord word1 = new ExportWord();
            Map<String,Object> map = new HashMap<String,Object>();
            for(SolutionVO temp : targetContents){
                List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(temp.getContent_title());
                for(SectionVO sectionTemp : sectionList){
                    String data = sectionTemp.getSection_details().replaceAll("\n","<w:p></w:p>");
                    String orginalData = "";
                    String section_name = sectionTemp.getSection_name().replaceAll(" ","");
                    if(map.containsKey(section_name)){
                        orginalData = map.get(section_name).toString();
                    }
                    data = data + orginalData;
                    map.put(section_name,data);
                }
            }
            jsonInfo.setInfo("true");
            response.add(jsonInfo);
            word1.create(templatePath,templateName,filePath,filename,map);
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            jsonInfo.setInfo("false");
            response.add(jsonInfo);
            return response;
        }

    }

}
