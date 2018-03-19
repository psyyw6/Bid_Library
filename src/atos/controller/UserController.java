package atos.controller;

import atos.admain.InternalTemplate;
import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.admain.Userjson;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import atos.wordExport.ExportWord;
import atos.exceptions.NoSearchResultException;
import atos.wordExport.RichHtmlHandler;
import atos.wordExport.WordGeneratorWithFreemarker;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
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


    @RequestMapping(value = "/staff_search", method = GET)
    public String staffSearchPage(HttpServletRequest request, ModelMap model) {
        List<SolutionVO> contentList = solutionDao.selectAll();
        if (!contentList.isEmpty()) {
            model.addAttribute("content_list", contentList);
        } else {
            throw new NoSearchResultException("No Available Content", "Sorry, there is no content now");
        }
        for (int i = 0; i < contentList.size(); i++) {
            String content_title = contentList.get(i).getContent_title();
            List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(content_title);
            model.addAttribute("section" + i, sectionList);
        }

        return "staff_search";

    }

    @RequestMapping(value = "/search.do", method = POST)
    public String staffSearchResultPage(HttpServletRequest request, ModelMap model) {
        String searchArea = request.getParameter("tag");
        String keyword = request.getParameter("keyword");
        List<SolutionVO> resultContentList = new ArrayList<SolutionVO>();
        List<SectionVO> resultSectionList = new ArrayList<SectionVO>();
        List<SectionVO> resultSectionList2 = new ArrayList<SectionVO>();
        List<SectionVO> finalSectionList = new ArrayList<SectionVO>();
        switch (searchArea) {
            case "Default":
                resultContentList = solutionDao.selectByDefault(keyword);
                resultSectionList = solutionDao.searchMaxVersionByName(keyword);
                resultSectionList2 = solutionDao.searchMaxVersionByDetails(keyword);
                finalSectionList.addAll(resultSectionList);
                finalSectionList.addAll(resultSectionList2);
                for (SectionVO resultS : finalSectionList) {
                    int isExist = 0;
                    for (SolutionVO resultC : resultContentList) {
                        if (resultS.getTitle().equals(resultC.getContent_title())) {
                            isExist = 1;
                            break;
                        }
                    }
                    if (isExist == 0) {
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
                for (SectionVO resultS : resultSectionList) {

                    SolutionVO newContent = solutionDao.selectContentByTitle(resultS.getTitle());
                    resultContentList.add(newContent);
                }

        }

        if (!resultContentList.isEmpty()) {
            model.addAttribute("content_list", resultContentList);

            for (int i = 0; i < resultContentList.size(); i++) {
                String content_title = resultContentList.get(i).getContent_title();
                List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(content_title);
                model.addAttribute("section" + i, sectionList);
            }
        } else {
            throw new NoSearchResultException("No Result", "Sorry, there is no matched result, please try another keyword");
        }

        return "staff_search";
    }

    @RequestMapping(value = "success_generate", method = GET)
    public String showSuccessGenerate(HttpServletRequest request, ModelMap model) {
        return "success_generate";
    }

    @ExceptionHandler(NoSearchResultException.class)
    public ModelAndView handleAdministerExceptionException(HttpServletRequest request, NoSearchResultException ex) {
        ModelAndView modelAndView = new ModelAndView("staff_error");
        modelAndView.addObject("errCode", ex.getErrCode());
        modelAndView.addObject("errMsg", ex.getErrMsg());
        return modelAndView;
    }

    @RequestMapping(value = "userViewDetail.do", method = GET)
    public String userViewDetail(HttpServletRequest request, ModelMap model) {
        String content_title = request.getParameter("content_title");
        String section_name = request.getParameter("section_name");
        String str_version = request.getParameter("version");
        if (content_title == null || section_name == null || str_version == null) {
            return "staff_search";
        }
        int version = Integer.parseInt(str_version);
        SectionVO requestSection = solutionDao.selectSectionByName(content_title, section_name, version);
        String details = requestSection.getSection_details().replaceAll("\n", "</p><p>");
        model.addAttribute("content_title", content_title);
        model.addAttribute("version", version);
        model.addAttribute("section_name", section_name);
        model.addAttribute("content", details);

        return "UserDetail";
    }

    @RequestMapping(value = "export_word", method = POST)
    @ResponseBody
    public List<Userjson> exportWord(HttpServletRequest request, ModelMap model, @RequestParam(required = false, value = "list[]") List<String> list,String selected_template) throws IOException {

        List<SolutionVO> targetContents = new ArrayList<SolutionVO>();
        List<Userjson> response = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        response.add(jsonInfo);
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (String content_title : list) {
            SolutionVO temp = solutionDao.selectContentByTitle(content_title);
            targetContents.add(temp);
        }
        for (SolutionVO temp : targetContents) {
            List<SectionVO> sectionList = solutionDao.selectNewestDetailOfContent(temp.getContent_title());
            map.put("CustomerName",temp.getCustomer());
            for (SectionVO sectionTemp : sectionList) {
                String data = sectionTemp.getSection_details();
                String orginalData = "";
                String section_name = sectionTemp.getSection_name().replaceAll(" ", "");

                if (map.containsKey(section_name)) {
                    orginalData = (String) map.get(section_name);
                    data = orginalData + data;
                }

                RichHtmlHandler handler = new RichHtmlHandler(data);
                if(selected_template.equals("Template1")){
                    handler.setDocSrcLocationPrex("file:///C:/26F25CD1");
                    handler.setNextPartId("01D3BFBC.2F868BE0");
                    handler.setDocSrcParent("Template1.fld");
                }
                else if(selected_template.equals("Template2")){
                    handler.setDocSrcLocationPrex("file:///C:/26F25CD1");
                    handler.setNextPartId("01D3BFAE.E2495DB0");
                    handler.setDocSrcParent("Template1.fld");
                }
                handler.setShapeidPrex("_x56fe_x7247_x0020");
                handler.setSpidPrex("_x0000_i");
                handler.setTypeid("#_x0000_t75");
                handler.handledHtml(false);
                String bodyBlock = handler.getHandledDocBodyBlock();
                String handledBase64Block = "";
                if (handler.getDocBase64BlockResults() != null
                        && handler.getDocBase64BlockResults().size() > 0) {
                    for (String item : handler.getDocBase64BlockResults()) {
                        handledBase64Block += item + "\n";
                    }
                }
                String old_handldBase64Block = "";
                if (map.containsKey("imagesBase64String")) {
                    old_handldBase64Block = (String) map.get("imagesBase64String");
                    handledBase64Block = old_handldBase64Block + handledBase64Block;
                }
                map.put("imagesBase64String", handledBase64Block);
                String xmlimaHref = "";
                if (handler.getXmlImgRefs() != null && handler.getXmlImgRefs().size() > 0) {
                    for (String item : handler.getXmlImgRefs()) {
                        xmlimaHref += item + "\n";
                    }
                }
                String old_xmlimaHref = "";
                if (map.containsKey("imagesXmlHrefString")) {
                    old_xmlimaHref = (String) map.get("imagesXmlHrefString");
                    xmlimaHref = old_xmlimaHref + xmlimaHref;
                }
                map.put("imagesXmlHrefString", xmlimaHref);
                map.put(section_name, bodyBlock);
            }
        }
        String filePath = "/Users/realmadrid/Desktop/RichTest2.doc";
        File f = new File(filePath);
        OutputStream out;
        try {
            out = new FileOutputStream(f);
            WordGeneratorWithFreemarker.createDoc(map,selected_template+".ftl", out);
            jsonInfo.setInfo("true");
            response.add(jsonInfo);
        } catch (FileNotFoundException e) {

        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            jsonInfo.setInfo("false");
            response.add(jsonInfo);
        }
        return response;

    }

    @RequestMapping(value = "generate_error",method = GET)
    public String showGenerateError(HttpServletRequest request,ModelMap model){
        return "generate_error";
    }

}