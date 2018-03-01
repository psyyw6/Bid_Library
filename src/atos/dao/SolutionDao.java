package atos.dao;

import atos.admain.SectionVO;
import atos.admain.SolutionVO;
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
        String sql = "INSERT INTO Content VALUES(?,?,?,?,?,?);";
        Object[] params = {content_title,isExternal,author,expired_date,upload_date,customer};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int storeSectionDetail(String content_title,String section_name,int version,String section_detail){
        String sql = "INSERT INTO Section Values(?,?,?,?);";
        Object[] params = {section_name,content_title,section_detail,version};
        try{
            return jdbcTemplate.update(sql,params);
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

    public List selectDetailOfContent(String content_title,int version){
        String sql = "select * from Section where Title = ? and Section_Version = ?;";
        Object[] params = {content_title,version};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List selectNewestDetailOfContent(String content_title){
        String sql = "select a.* from Section a inner join (select Section_Name, max(Section_Version) Section_Version from Section group by Section_Name)b on a.Section_Name = b.Section_Name and a.Section_Version = b.Section_Version where a.Title = ?;";
        Object[] params = {content_title};
        try{
            return jdbcTemplate.query(sql,new SectionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public SectionVO selectSectionByName(String content_title,String section_name,int version){
        String sql = "select * from Section where Title = ? and Section_Version = ? and Section_Name = ?;";
        Object[] params = {content_title,version,section_name};
        try{
            return jdbcTemplate.queryForObject(sql,new SectionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List selectContentByKeyword(String searchArea, String keyword){
        String sql;
        keyword = '%'+keyword+'%';
        if(searchArea.equals("Section_Detail") || searchArea.equals("Section_Name")){
            sql = "SELECT DISTINCT c.* FROM Content c INNER JOIN Section s ON c.Title = s.Title WHERE s." + searchArea + " LIKE ?;";
        }
        else {
            sql = "SELECT DISTINCT * FROM Content where Content." + searchArea + " LIKE ?;";
        }
        Object[] params = {keyword};

//    public List<SolutionVO> selectSectionByName(String column, String keyword){
//        String sql = "select * from Section, Content where Content.? = ?;";
//        Object[] params = {column, keyword};
//        try{
//            return jdbcTemplate.query(sql,new SolutionVO(), params);
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }

        try{
            return jdbcTemplate.query(sql,new SolutionVO(), params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List selectContentByEverything(String keyword){
        keyword = '%'+keyword+'%';
        String sql = "SELECT DISTINCT c.* from Content c INNER JOIN Section s ON c.Title = s.Title WHERE s.Section_Name LIKE ? OR s.Title LIKE ? OR s.Section_Detail LIKE ? OR c.Title LIKE ? OR c.Customer LIKE ? ;";

        Object[] params = {keyword, keyword, keyword, keyword, keyword};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(), params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    public List<SectionVO> selectAllHistory(String content_title,String section_name){
        String sql = "select * from Section where Title = ? and Section_Name = ?;";
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
        String sql = "select * from Content where Title = ? or Author = ? or Version = ? or Customer = ?";
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
        String sql = "select a.* from Section a inner join (select Section_Name, max(Section_Version) Section_Version from Section group by Section_Name)b on a.Section_Name = b.Section_Name and a.Section_Version = b.Section_Version where a.Title = ? and a.Section_Name = ?;";
        Object[] params = {content_title,section_name};
        try{
            return jdbcTemplate.queryForObject(sql,new SectionVO(),params);
        }
        catch (Exception e){
            return null;
        }

    }

    public int DeleteSection(String content_title,String section_name,int version){
        String sql = "delete from Section where Title = ? and Section_Name = ? and Section_Version = ?;";
        Object[] params = {content_title,section_name,version};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }
}
