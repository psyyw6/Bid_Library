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

    public int storeContent(String content_title,String author,String customer,String expired_date,String upload_date,boolean isExternal,int version,String flag){
        String sql = "INSERT INTO Content VALUES(?,?,?,?,?,?,?,?);";
        Object[] params = {content_title,isExternal,author,expired_date,upload_date,version,customer,flag};
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
        String sql = "SELECT a.* FROM bid_library.Content a inner join (select Title,max(Version) Version from bid_library.Content group by Title)b on a.Title = b.Title and a.Version = b.Version; ";
        try{
            return jdbcTemplate.query(sql,new SolutionVO());
        }
        catch (Exception e){
            System.out.println(e);
            return  null;
        }
    }

    public int deleteContent(String content_title,int version){
        String sql = "delete from Content where Title = ? and Version = ?;";
        Object[] params = {content_title,version};
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

    public SolutionVO selectContentByTitleAndVersion(String content_title,int version){
        String sql = "select * from Content where Title = ? and Version = ?;";
        Object[] params = {content_title,version};
        try{
            return jdbcTemplate.queryForObject(sql,new SolutionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public List selectAllHistory(String content_title){
        String sql = "select * from Content where Title = ?;";
        Object[] params = {content_title};
        try{
            return jdbcTemplate.query(sql,new SolutionVO(),params);
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
