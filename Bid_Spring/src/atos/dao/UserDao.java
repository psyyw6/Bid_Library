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
        return jdbcTemplate.query(sql,new UserMapper());
    }

    private static final class UserMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet resultSet, int i) throws SQLException {
            UserVO userVO = new UserVO();
            userVO.setDepartment(resultSet.getString("DEPARTMENT"));
            userVO.setEmail(resultSet.getString("Email"));
            userVO.setName(resultSet.getString("Username"));
            userVO.setPwd(resultSet.getString("Password"));
            userVO.setRole(resultSet.getString("ROLE"));

            return userVO;
        }
    }

    public UserVO selectByName(String name,String pwd)
    {
        String sql = "select * from Users where Username=? and Password=?";
        Object[] params = new Object[]{name, pwd};
        try {
            return jdbcTemplate.queryForObject(sql,params, new BeanPropertyRowMapper<UserVO>(UserVO.class));
        }
        catch (Exception e) {
            return null;
        }
    }
}
