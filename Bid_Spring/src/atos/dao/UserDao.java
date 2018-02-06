package atos.dao;

import atos.admain.UserVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public List find() {
        String sql = "select * from Users";
        return jdbcTemplate.query(sql,new UserVO());
    }


    public UserVO selectByName(String name,String pwd)
    {
        String sql = "select * from Users where Username=? and Password=?";
        Object[] params = new Object[]{name, pwd};
        try {
            return jdbcTemplate.queryForObject(sql,new UserVO(),params);
        }
        catch (Exception e) {
            return null;
        }
    }

    public int registerUser(String name,String password,String email) {
        String sql = "INSERT INTO Users VALUES(?,?,?,FALSE);";
        Object[] params = new Object[]{name,password,email};
        try {
            return jdbcTemplate.update(sql, params);
        }
        catch (Exception e){
            return 0;
        }


    }

    public UserVO checkDuplicate(String name){

        String sql = "select * from Users where Username=?";
        try{
            return jdbcTemplate.queryForObject(sql,new UserVO(),name);
        }
        catch(Exception e){
            return null;
        }
    }



}
