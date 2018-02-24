package atos.controller;

import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.admain.UserVO;
import atos.admain.Userjson;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import atos.wordExport.ExportWord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<SolutionVO> content_list = solutionDao.selectAll();
        model.addAttribute("content_list",content_list);
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
    @RequestMapping(value = "/success_upload", method = GET)
    public String showSuccess(HttpServletRequest request,ModelMap model){
        return "success_upload";
    }

    @RequestMapping(value = "/upload.do",method = POST)
    public String upLoadFile(@RequestParam MultipartFile myfile,HttpServletRequest request) throws IOException{
        String author = "yutong";
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            author = loginstaff.getName();
        }
        String fileName = myfile.getOriginalFilename();
        String content_title = request.getParameter("solution_title");
        String customer_name = request.getParameter("customer_name");
        String expired_date = convertSqlDate(request.getParameter("expired_date"));
        String type = request.getParameter("isExternal");
        String flag = request.getParameter("flag");
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String upload_date = df.format(today);
        int version = 1;
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
            String lineText = "";
            String section_details = "";
            String section_name = "";
            int i = 0;
            if(solutionDao.storeContent(content_title,author,customer_name,expired_date,upload_date,isExternal,version,flag)==1){
                while ((lineText = br.readLine()) != null) {
                    if(!lineText.equals("")){
                        if(lineText.charAt(0) == '*') {
                            if (!section_name.equals("") && !section_details.equals("")) {
                                solutionDao.storeSectionDetail(content_title, section_name, version, section_details);
                            }
                            section_name = lineText.substring(1);
                            section_details = "";
                        }
                        else{
                            section_details += lineText;
                            section_details += "\n";
                        }
                    }
                }
                if(!section_name.equals("")&&!section_details.equals("")){
                    solutionDao.storeSectionDetail(content_title, section_name, version, section_details);
                }
                return "success_upload";
            }
            else{
                solutionDao.deleteContent(content_title,version);
                return "error";
            }
        }

    }
    @RequestMapping(value="/admin_view_detail",method = GET)
    public String viewContentDetail(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            model.addAttribute("name",loginstaff.getName());
        }
        String content_title = request.getParameter("content_title");
        int version = Integer.parseInt(request.getParameter("version"));
        List<SectionVO>section_list = solutionDao.selectDetailOfContent(content_title,version);
        model.addAttribute("section_list",section_list);
        model.addAttribute("content_title",content_title);
        model.addAttribute("version",version);
        return "admin_view_detail";
    }

    @RequestMapping(value = "/edit",method = GET)
    public String editSolution(HttpServletRequest request,ModelMap model){
        String content_title = request.getParameter("content_title");
        String section_name = request.getParameter("section_name");
        int version = Integer.parseInt(request.getParameter("version"));

        SectionVO section_detail = solutionDao.selectSectionByName(content_title,section_name,version);
        if(section_detail!=null){
            String details = section_detail.getSection_details().replaceAll("\n","</p><p>");
            model.addAttribute("section_name",section_name);
            model.addAttribute("content_title",content_title);
            model.addAttribute("version",version);
            model.addAttribute("content",details);
            return "edit";
        }
        else{
            return "error";
        }
    }

    @RequestMapping(value="/edit_upload.do",method = POST)
    @ResponseBody
    public List<Userjson> uploadForEdit(HttpServletRequest request, @RequestParam String content_title, String section_name, String version, String content_detail){
        int num_version = Integer.parseInt(version);
        List<Userjson> response = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        SolutionVO content = solutionDao.selectContentByTitleAndVersion(content_title,num_version);
        int new_version = num_version + 1;
        content_detail = content_detail.substring(3,content_detail.length()-4);
        content_detail = content_detail.replaceAll("</p><p>","\n");
        if(solutionDao.storeContent(content_title,content.getAuthor(),content.getCustomer(),content.getExpired_date(),content.getUpload_date(),content.isExternal(),new_version,content.getFlag())!=1)
        {
            jsonInfo.setInfo("false");
            response.add(jsonInfo);
            return response;
        }
        List<SectionVO> sections = solutionDao.selectDetailOfContent(content_title,num_version);
        for(int i = 0;i<sections.size();i++){
            if(!sections.get(i).getSection_name().equals(section_name)){
                solutionDao.storeSectionDetail(sections.get(i).getTitle(),sections.get(i).getSection_name(),new_version,sections.get(i).getSection_details());
            }else{
                solutionDao.storeSectionDetail(sections.get(i).getTitle(),sections.get(i).getSection_name(),new_version,content_detail);
            }
        }
        jsonInfo.setInfo("true");
        response.add(jsonInfo);
        return response;
    }
//
//    @RequestMapping(value="export_word",method = GET)
//    public String exportWord(HttpServletRequest request, ModelMap model){
//        String solution_title = request.getParameter("solution_title");
//        String heading = request.getParameter("heading");
//        SolutionVO solution_1 = solutionDao.getContent(heading,solution_title);
//        try{
//            String templatePath = "/atos/ftl/";
//            String templateName = "template1.ftl";
//            String filePath = "/Users/realmadrid/Desktop/";
//            String filename = "test1.doc";
//            ExportWord word1 = new ExportWord();
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("content",solution_1.getContent().replaceAll("\n","<w:p></w:p>"));
//            word1.create(templatePath,templateName,filePath,filename,map);
//            return "success_upload";
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return "error";
//        }
//    }

}
