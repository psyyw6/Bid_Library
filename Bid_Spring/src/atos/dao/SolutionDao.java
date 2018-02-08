package atos.dao;

import atos.admain.SolutionVO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class SolutionDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public int storeSolution(String solution_title,String creator,String heading,String customer,String expired_date,boolean isExternal, String content,double version){
        String sql = "INSERT INTO Solution VALUES(?,?,?,?,?,?,?,?);";
        Object[] params = {solution_title,heading,isExternal,creator,expired_date,version,customer,content};
        try{
            return jdbcTemplate.update(sql,params);
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public SolutionVO getContent(String heading){
        String sql = "Select * FROM Solution Where Heading=?";
        Object params = heading;
        try{
            return jdbcTemplate.queryForObject(sql,new SolutionVO(),params);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
