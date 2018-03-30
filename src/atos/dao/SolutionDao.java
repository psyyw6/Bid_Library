package atos.dao;

import atos.admain.DownloadLogVO;
import atos.admain.SectionVO;
import atos.admain.SolutionVO;
import atos.admain.TemplateVO;
import atos.exceptions.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

public class SolutionDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public int storeContent(String content_title,String author,String customer,String expired_date,String upload_date,String isExternal){

        String sql = "INSERT INTO Content VALUES(?,?,?,?,?,?)";
        Object[] params = {content_title,isExternal,author,expired_date,upload_date,customer};
        try{
        return jdbcTemplate.update(sql,params);
        }
        catch(org.springframework.dao.DuplicateKeyException e){
            throw new DuplicateKeyException(null, "Existed Content Title", "'"+content_title+ "' have already existed, "+"please choose another content title");
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int storeSectionDetail(String content_title,String section_name,int version,String section_detail,boolean inUse){
        String sql = "INSERT INTO Section Values(?,?,?,?,?);";
        Object[] params = {section_name,content_title,section_detail,version,true};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (org.springframework.dao.DuplicateKeyException e){
            throw new DuplicateKeyException(content_title,"Duplicate Section Name in the '" + content_title+"' ", "'"+section_name+"' have already existed, please check your uploaded file");
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public List selectAll(){
        String sql = "SELECT * FROM Content;";
        try{
            return jdbcTemplate.query(sql,new SolutionVO());
        }
        catch (Exception e){
            System.out.println(e);
            return  null;
        }
    }

    public SolutionVO selectContentByTitle(String title){
        String sql = "select * from Content where Title = ?";
        Object[] params = {title};
        try{
            return jdbcTemplate.queryForObject(sql,new SolutionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int deleteContent(String content_title){
        String sql = "delete from Content where Title = ?;";
        Object[] params = {content_title};
        try{
            return jdbcTemplate.update(sql,params);
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public SectionVO selectSectionByName(String content_title,String section_name,int version){
        String sql = "select * from Section where Title = ? and Section_Version = ? and Section_Name = ?";
        Object[] params = {content_title,version,section_name};
        try{
            return jdbcTemplate.queryForObject(sql,new SectionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<SectionVO> selectAllHistory(String content_title,String section_name){
        String sql = "select * from Section where Title = ? and Section_Name = ?";
        Object[] params = {content_title,section_name};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<SolutionVO> selectByDefault(String keyword){
        String sql = "select * from Content where Title LIKE ? or Author like ?  or Customer like ? or IsExternal like ?";
        Object[] params = {keyword,keyword,keyword,keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public SectionVO selectMaxVersionByTitleAndName(String content_title,String section_name){
        String sql = "select a.* from Section a inner join (select Section_Name,Title, max(Section_Version) Section_Version from Section WHERE Title = ? group by Section_Name,Title)b on a.Title = b.Title and  a.Section_Name = b.Section_Name and a.Section_Version = b.Section_Version Where a.Title = ? and a.Section_Name = ?;";
        Object[] params = {content_title,content_title,section_name};
        try{
            return jdbcTemplate.queryForObject(sql,new SectionVO(),params);
        }
        catch (Exception e){
            return null;
        }

    }

    public List<SectionVO> searchInUseSectionByName(String section_name){
        String sql = "select * from Section where Section_Name like ? and InUse = true";
        Object[] params = {section_name};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch (Exception e){
            return null;
        }

    }

    public List<SectionVO> searchInUseSectionByDetails(String details){
        String sql = "select * from Section where Section_Detail like ? and InUse = true";
        Object[] params = {details};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch (Exception e){
            return null;
        }
    }

    public int DeleteSection(String content_title,String section_name,int version){
        String sql = "delete from Section where Title = ? and Section_Name = ? and Section_Version = ?";
        Object[] params = {content_title,section_name,version};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public List<SolutionVO> searchByTitle(String keyword){
        String sql = "select * from Content where Title like ?";
        Object[] params = {keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<SolutionVO> searchByAuthor(String keyword){
        String sql = "select * from Content where Author like ?";
        Object[] params = {keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }

    }

    public List<SolutionVO> searchByType(String keyword){
        String sql = "select * from Content where IsExternal like ?";
        Object[] params = {keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List<SolutionVO> searchByCustomer(String keyword){
        String sql = "select * from Content where Customer like ?";
        Object[] params = {keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int updateInUseVersionToFalse(String content_title,String section_name){
        String sql = "UPDATE Section SET InUse = false where Title = ? and Section_Name = ? and InUse = true;";
        Object[] params = {content_title,section_name};
        try {
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }

    public List<SectionVO>selectInUseSection(String content_title)
    {
        String sql = "Select * from Section where Title = ? and InUse = true;";
        Object[] params = {content_title};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public SectionVO selectInUseSectionByTilteAndName(String content_title,String section_name)
    {
        String sql = "Select * from Section where Title = ? and Section_Name = ? and InUse = true;";
        Object[] params = {content_title,section_name};
        try {
            return jdbcTemplate.queryForObject(sql,new SectionVO(),params);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public int updateInUseVersionToTrue(String content_title,String section_name,int version){
        String sql = "UPDATE Section SET InUse = true where Title = ? and Section_Name = ? and Section_Version = ?;";
        Object[] params = {content_title,section_name,version};
        try {
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }

    public int insertTemplate(String template_name,String doc_src_prefix_location,String next_part_id,String doc_src_parent,String image_url){
        String sql = "insert into Template values(?,?,?,?,?)";
        Object[] params = {template_name,doc_src_prefix_location,next_part_id,doc_src_parent,image_url};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<TemplateVO> selectAllTemplates(){
        String sql = "select * from Template";
        try{
            return jdbcTemplate.query(sql,new TemplateVO());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int deleteTemplate(String template_name){
        String sql = "delete from Template where Template_Name = ?";
        Object[] params = {template_name};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public TemplateVO selectTemplateByName(String template_name){
        String sql = "select * from Template where Template_Name = ?";
        Object[] params = {template_name};
        try{
            return jdbcTemplate.queryForObject(sql,new TemplateVO(),params);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int storeDownloadLog(String download_name,String selected_contents,String selected_template,String download_time,String user){
        String sql = "insert into DownloadLog (Download_Name,Selected_Contents,Selected_Template,Download_Time,User) values (?,?,?,?,?)";
        Object[] params = {download_name,selected_contents,selected_template,download_time,user};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int modifyTemplate(String template_name,String doc_src_prefix_location,String next_part_id,String doc_src_parent){
        String sql = "UPDATE Template Set DocSrcPrefixLocation = ?,NextPartId = ?,DocSrcParent = ? WHERE Template_Name = ?";
        Object[] params = {doc_src_prefix_location,next_part_id,doc_src_parent,template_name};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e)
        {
            System.out.println(e);
            return 0;
        }
    }

    public List<DownloadLogVO> selectAllLogs(){
        String sql = "select * from DownloadLog";
        try{
            return jdbcTemplate.query(sql,new DownloadLogVO());
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    public int deleteLog(String download_id){
        String sql = "delete from DownloadLog where Download_ID = ?";
        Object[] params = {download_id};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
