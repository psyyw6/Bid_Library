package atos.controller;

import atos.admain.*;
import atos.dao.SolutionDao;
import atos.dao.UserDao;
import atos.exceptions.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
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

//    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        List<SolutionVO> content_list = solutionDao.selectAll();
        model.addAttribute("content_list",content_list);
        return "administer_solution";
    }

    @RequestMapping(value="/modify", method = GET)
    public String modifySolution(HttpServletRequest request, ModelMap model) {
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        return "modify";
    }

    @RequestMapping(value = "/add_solution",method = GET)
    public String addSolutionPage(HttpServletRequest request,ModelMap model) throws Exception{
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());

        }
        else{
            return "unlogin";
        }
        return "add_solution";
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ModelAndView handleDuplicateKeyException(HttpServletRequest request, DuplicateKeyException ex){

//        System.out.println(ex.getContentTitle());
        if(ex.getContentTitle()!=null) {
            solutionDao.deleteContent(ex.getContentTitle(),ex.getIsExternal());
        }
        ModelAndView modelAndView = new ModelAndView("administer_error");
        modelAndView.addObject("errCode", ex.getErrCode());
        modelAndView.addObject("errMsg", ex.getErrMsg());
        return modelAndView;
    }

    @RequestMapping(value = "/success_upload", method = GET)
    public String showSuccess(HttpServletRequest request,ModelMap model){
        return "success_upload";
    }

    @RequestMapping(value = "/upload.do",method = POST)
    public String upLoadFile(@RequestParam MultipartFile myfile,HttpServletRequest request) throws Exception{
        String author;
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            author = loginstaff.getName();
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
        }
        else{
            return "unlogin";
        }
        String fileName = myfile.getOriginalFilename();
        String content_title = request.getParameter("solution_title");
        String customer_name = request.getParameter("customer_name");
        String expired_date = convertSqlDate(request.getParameter("expired_date"));
        String type = request.getParameter("isExternal");
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String upload_date = df.format(today);
        int version =1;
        if(!checkFile(fileName)||fileName == ""){
            throw new DuplicateKeyException(null, "Wrong File Format", "Please upload again",type);
        }
        else {
            InputStream file = myfile.getInputStream();
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            String lineText = "";
            String section_details = "";
            String section_name = "";
            int i = 0;
            if(solutionDao.storeContent(content_title,author,customer_name,expired_date,upload_date,type)==1){
                while ((lineText = br.readLine()) != null) {
                    if(!lineText.equals("")){
                        if(lineText.charAt(0) == '*') {
                            if (!section_name.equals("") && !section_details.equals("")) {
                                solutionDao.storeSectionDetail(content_title, section_name,type, version, section_details,true,upload_date);
                            }
                            section_name = lineText.substring(1);
                            section_details = "";
                        }
                        else{
                            section_details += "<p>"+lineText+"</p>";
                        }
                    }
                }
                if(!section_name.equals("")&&!section_details.equals("")){
                    solutionDao.storeSectionDetail(content_title, section_name,type,version, section_details,true,upload_date);
                }
                return "success_upload";
            }
            else{
                solutionDao.deleteContent(content_title,type);
                return "error";
            }
        }

    }


    @RequestMapping(value="/admin_view_detail",method = GET)
    public String viewContentDetail(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        String content_title = request.getParameter("content_title");
        String type = request.getParameter("isExternal");
        if(content_title==null||type==null){
            return "administer_solution";
        }
        List<SectionVO>section_list = solutionDao.selectInUseSection(content_title,type);
        model.addAttribute("section_list",section_list);
        model.addAttribute("content_title",content_title);
        return "admin_view_detail";
    }

    @RequestMapping(value = "/edit",method = GET)
    public String editSolution(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        String content_title = request.getParameter("content_title");
        String section_name = request.getParameter("section_name");
        int version = Integer.parseInt(request.getParameter("version"));
        String type = request.getParameter("isExternal");
        SectionVO section_detail = solutionDao.selectSectionByName(content_title,section_name,version,type);
        if(section_detail!=null){
            String details = section_detail.getSection_details();
            model.addAttribute("section_name",section_name);
            model.addAttribute("content_title",content_title);
            model.addAttribute("version",version);
            model.addAttribute("content",details);
            model.addAttribute("type",type);
            return "edit";
        }
        else{
            return "error";
        }
    }

    @RequestMapping(value="/edit_upload.do",method = POST)
    @ResponseBody
    public List<Userjson> uploadForEdit(HttpServletRequest request, @RequestParam String content_title, String section_name, String version, String content_detail,String type){
        int num_version = Integer.parseInt(version);
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String modify_time = df.format(today);
        List<Userjson> response = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        SectionVO latestSection = solutionDao.selectMaxVersionByTitleAndName(content_title,section_name,type);
        solutionDao.updateInUseVersionToFalse(content_title,section_name,type);
        int new_version = latestSection.getSection_version() + 1;
        solutionDao.storeSectionDetail(content_title,section_name,type,new_version,content_detail,true,modify_time);
        jsonInfo.setInfo("true");
        response.add(jsonInfo);
        return response;
    }

    @RequestMapping(value="section_history",method = GET)
    public String showHistory(HttpServletRequest request, ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        String content_title = request.getParameter("content_title");
        String section_name = request.getParameter("section_name");
        String type = request.getParameter("isExternal");
        if(content_title==null||section_name==null||type==null){
            return "administer_solution";
        }
        model.addAttribute("section_name",section_name);
        List<SectionVO> allSections = solutionDao.selectAllHistory(content_title,section_name,type);
        SectionVO inUseSection = solutionDao.selectInUseSectionByTilteAndName(content_title,section_name,type);
        model.addAttribute("InUseVersion",inUseSection.getSection_version());
        model.addAttribute("content_title",content_title);
        model.addAttribute("section_name",section_name);
        model.addAttribute("type",type);
        if(allSections!=null){
            model.addAttribute("allSections",allSections);
        }
        return "section_history";
    }

    @RequestMapping(value="delete_content.do",method= POST)
    @ResponseBody
    public List<Userjson> deleteContent(HttpServletRequest request,ModelMap model,@RequestParam String content_title,String type){

        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        if(solutionDao.deleteContent(content_title,type)==1){
            jsonInfo.setInfo("true");

        }else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="delete_section.do",method = POST)
    @ResponseBody
    public List<Userjson> deleteSection(HttpServletRequest request,ModelMap model,@RequestParam String content_title,String section_name,String version,String type){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        int num_version = Integer.parseInt(version);
        int inuse_version = solutionDao.selectInUseSectionByTilteAndName(content_title,section_name,type).getSection_version();
        if(solutionDao.DeleteSection(content_title,section_name,num_version,type) == 1){
            if(inuse_version == num_version){
                int new_inuse_version = num_version - 1;
                while(solutionDao.selectSectionByTitleAndName(content_title,section_name,new_inuse_version,type)==null){
                    new_inuse_version--;
                }
                solutionDao.updateInUseVersionToTrue(content_title,section_name,new_inuse_version,type);
            }
            jsonInfo.setInfo("true");
        }
        else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }


    @RequestMapping(value="/upload",method = POST)
    @ResponseBody
    public ResponseEntity<EditorUploadVO> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request)throws IOException{
        String filename = file.getOriginalFilename();
        String name = filename.substring(0,filename.indexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."));
        String path1 = request.getSession().getServletContext().getRealPath("WEB-INF/view/upload"+File.separator);
        String path = path1+filename;
        File descFile = new File(path);
        int i = 1;
        String newFilename = filename;
        while (descFile.exists()) {
            newFilename = name + "(" + i + ")" + suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + newFilename);
            i++;
        }
        if (!descFile.getParentFile().exists()) {
            descFile.getParentFile().mkdirs();
        }
        file.transferTo(descFile);
        String fileUrl = "upload/" + newFilename;
        String[] data = {fileUrl};
        return ResponseEntity.ok().body(new EditorUploadVO(0,data));
    }

    @RequestMapping(value="/rollback.do",method = POST)
    @ResponseBody
    List<Userjson> rollBackVersion(HttpServletRequest request,ModelMap model,@RequestParam String content_title,String section_name,String type){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        SectionVO currentUse = solutionDao.selectInUseSectionByTilteAndName(content_title,section_name,type);
        int currentVersion = currentUse.getSection_version();
        if(currentVersion == 1){
            jsonInfo.setInfo("false");
        }
        else{
            currentVersion--;
            while (solutionDao.selectSectionByTitleAndName(content_title,section_name,currentVersion,type)==null){
                currentVersion--;
            }
            solutionDao.updateInUseVersionToFalse(content_title,section_name,type);
            solutionDao.updateInUseVersionToTrue(content_title,section_name,currentVersion,type);
            jsonInfo.setInfo("true");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="/forward.do",method = POST)
    @ResponseBody
    List<Userjson> forwardVersion(HttpServletRequest request,ModelMap model,@RequestParam String content_title,String section_name,String type){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        SectionVO currentUse = solutionDao.selectInUseSectionByTilteAndName(content_title,section_name,type);
        int maxVersion = solutionDao.selectMaxVersionByTitleAndName(content_title,section_name,type).getSection_version();
        int currentVersion = currentUse.getSection_version();
        if(currentVersion == maxVersion){
            jsonInfo.setInfo("false");
        }
        else{
            currentVersion++;
            while (solutionDao.selectSectionByTitleAndName(content_title,section_name,currentVersion,type)==null){
                currentVersion++;
            }
            solutionDao.updateInUseVersionToFalse(content_title,section_name,type);
            solutionDao.updateInUseVersionToTrue(content_title,section_name,currentVersion,type);
            jsonInfo.setInfo("true");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="/upgrade",method = GET)
    public String upgrdePage(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        List<UserVO> user_list = userDao.selectAllUsers();
        model.addAttribute("user_list",user_list);
        return "upgrade";
    }

    @RequestMapping(value="/upgradeUser.do",method = POST)
    @ResponseBody
    List<Userjson> upgradeUser(HttpServletRequest request,ModelMap model,@RequestParam String username){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        userDao.upgradeUser(username);
        jsonInfo.setInfo("true");
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="/deleteUser.do",method = POST)
    @ResponseBody
    List<Userjson> deleteUser(HttpServletRequest request,ModelMap model,@RequestParam String username){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        userDao.deleteUser(username);
        jsonInfo.setInfo("true");
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="/changePassword",method = GET)
    public String changePasswordPage(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        String username = request.getParameter("username");
        model.addAttribute("username",username);
        return "changePassword";
    }

    @RequestMapping(value="/changePassword.do",method = POST)
    @ResponseBody
    List<Userjson> changePassword(HttpServletRequest request,ModelMap model,@RequestParam String username, String password)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        String new_pwd = RegisterController.EncoderByMd5(password);
        userDao.changePassword(username, new_pwd);
        jsonInfo.setInfo("true");
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value = "admin_search.do",method = POST)
    public String adminSearch(HttpServletRequest request,ModelMap model)
    {
        String keyword = request.getParameter("keyword");
        List<SolutionVO> resultContentList;
        List<SectionVO> resultSectionList;
        List<SectionVO> resultSectionList2;
        List<SectionVO> finalSectionList = new ArrayList<SectionVO>();
        resultContentList = solutionDao.selectByDefault(keyword);
        resultSectionList = solutionDao.searchInUseSectionByName(keyword);
        resultSectionList2 = solutionDao.searchInUseSectionByDetails(keyword);
        finalSectionList.addAll(resultSectionList);
        finalSectionList.addAll(resultSectionList2);
        for (SectionVO resultS : finalSectionList) {
            int isExist = 0;
            for (SolutionVO resultC : resultContentList) {
                if (resultS.getTitle().equals(resultC.getContent_title())&&resultS.getIsExternal().equals(resultC.getIsExternal())) {
                    isExist = 1;
                    break;
                }
            }
            if (isExist == 0) {
                SolutionVO newContent = solutionDao.selectContentByTitle(resultS.getTitle(),resultS.getIsExternal());
                resultContentList.add(newContent);
            }
        }
        model.addAttribute("content_list",resultContentList);
        model.addAttribute("keyword",keyword);
        return "administer_solution";
    }

    @RequestMapping(value = "template",method = GET)
    public String templatePage(HttpServletRequest request,ModelMap model)
    {
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        List<TemplateVO> allTemplates = solutionDao.selectAllTemplates();
        model.addAttribute("allTemplates",allTemplates);
        return "template";
    }

    @RequestMapping(value = "add_template",method = GET)
    public String AddtemplatePage(HttpServletRequest request,ModelMap model)
    {
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        return "add_template";
    }

    @RequestMapping(value="upload_template.do",method= POST)
    public String uploadTemplate(HttpServletRequest request,ModelMap model,@RequestParam("files") MultipartFile[] files) throws IOException {
        String imageUrl = "";
        if(files!=null&&files.length==2){
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                String filename;
                if(i==0){
                     filename = request.getParameter("Template_name")+".ftl";
                     String path1 = request.getSession().getServletContext().getRealPath("WEB-INF/classes/atos/ftl")+File.separator;
                     String path = path1 + filename;
                     File descFile = new File(path);
                     file.transferTo(descFile);
                }
                if(i==1){
                    filename = file.getOriginalFilename();

                    String path1 = request.getSession().getServletContext().getRealPath("WEB-INF/view/coverimage")+File.separator;
                    String path = path1 + filename;
                    String name = filename.substring(0,filename.indexOf("."));
                    String suffix = filename.substring(filename.lastIndexOf("."));
                    File descFile = new File(path);
                    int count = 1;
                    String newFilename = filename;
                    while(descFile.exists()) {
                        newFilename = name + "("+count+")"+suffix;
                        String parentPath = descFile.getParent();
                        descFile = new File(parentPath + File.separator + newFilename);
                        count++;
                    }
                    file.transferTo(descFile);
                    imageUrl = "coverimage/"+newFilename;
            }
        }
    }
        String template_name = request.getParameter("Template_name");
        String doc_src_prefix_location = request.getParameter("DocSrcPrefixLocation");
        String next_part_id = request.getParameter("NextPartId");
        String doc_src_parent = request.getParameter("DocSrcParent");
        if(solutionDao.insertTemplate(template_name,doc_src_prefix_location,next_part_id,doc_src_parent,imageUrl)==1){
            return "template_success";
        }
        else{
            return "error";
        }



    }

    @RequestMapping(value="delete_template.do",method = POST)
    @ResponseBody
    public List<Userjson> deleteTemplate(HttpServletRequest request,ModelMap model,@RequestParam String template_name,String image_url){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        if(solutionDao.deleteTemplate(template_name)==1){
            String path = request.getSession().getServletContext().getRealPath("WEB-INF/classes/atos/ftl")+File.separator;
            String template_real_path = path + template_name + ".ftl";
            String image_path = request.getSession().getServletContext().getRealPath("WEB-INF/view")+File.separator;
            String image_real_path = image_path + image_url;
            File template_file = new File(template_real_path);
            File image_file = new File(image_real_path);
            if(template_file.exists()){
                template_file.delete();
            }
            if(image_file.exists()){
                image_file.delete();
            }
            jsonInfo.setInfo("true");

        }else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="modify_template",method = GET)
    public String modifyTemplatePage(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        String template_name = request.getParameter("template_name");
        TemplateVO template = solutionDao.selectTemplateByName(template_name);
        model.addAttribute("template_name",template.getTemplate_name());
        model.addAttribute("doc_src_prefix_location",template.getDoc_src_prefix_location());
        model.addAttribute("next_part_id",template.getNext_part_id());
        model.addAttribute("doc_src_parent",template.getDoc_src_parent());
        return "modify_template";
    }

    @RequestMapping(value = "modify_template.do",method = POST)
    public String modifyTemplate(HttpServletRequest request,ModelMap model){
        String template_name = request.getParameter("Template_name");
        String doc_src_prefix_location = request.getParameter("DocSrcPrefixLocation");
        String next_part_id = request.getParameter("NextPartId");
        String doc_src_parent = request.getParameter("DocSrcParent");
        if(solutionDao.modifyTemplate(template_name,doc_src_prefix_location,next_part_id,doc_src_parent)==1)
        {
            return "template_success";
        }
        else{
            return "error";
        }
    }


    @RequestMapping(value="download_log",method=GET)
    public String downloadLogPage(HttpServletRequest request,ModelMap model){
        if(request.getSession().getAttribute("loginstaff")!=null) {
            UserVO loginstaff = (UserVO) request.getSession().getAttribute("loginstaff");
            if(!loginstaff.getRole()){
                return "unAdmin";
            }
            model.addAttribute("name",loginstaff.getName());
        }
        else{
            return "unlogin";
        }
        List<DownloadLogVO> logs = solutionDao.selectAllLogs();
        model.addAttribute("logs",logs);
        return "download_log";
    }

    @RequestMapping(value="delete_doc.do",method = POST)
    @ResponseBody
    public List<Userjson> deleteDoc(HttpServletRequest request,ModelMap model,@RequestParam String download_id,String file_name){
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        if(solutionDao.deleteLog(download_id)==1){
            String path = request.getSession().getServletContext().getRealPath("WEB-INF/view/download")+File.separator;
            String file_real_path = path + file_name;
            File doc_file = new File(file_real_path);
            if(doc_file.exists()){
                doc_file.delete();
            }
            jsonInfo.setInfo("true");

        }else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;
    }

    @RequestMapping(value="/checkDuplicateContent",method=POST)
    @ResponseBody
    public List<Userjson> checkDuplicateContent(HttpServletRequest request,ModelMap model,@RequestParam String content_title,String isExternal) {
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        if(solutionDao.checkDuplicateContent(content_title,isExternal)!=null){
            jsonInfo.setInfo("true");
        }
        else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;

    }

    @RequestMapping(value="/checkDuplicateTemplate",method=POST)
    @ResponseBody
    public List<Userjson> checkDuplicateTemplate(HttpServletRequest request,ModelMap model,@RequestParam String template_name) {
        List<Userjson> jsonList = new ArrayList<Userjson>();
        Userjson jsonInfo = new Userjson();
        if(solutionDao.checkDuplicateTemplate(template_name)!=null){
            jsonInfo.setInfo("true");
        }
        else{
            jsonInfo.setInfo("false");
        }
        jsonList.add(jsonInfo);
        return jsonList;

    }



}
