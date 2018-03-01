package atos.admain;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolutionVO implements RowMapper<SolutionVO>, Serializable{
    private String content_title;
    private boolean isExternal;
    private String author;
    private String expired_date;
    private String upload_date;
    private String customer;

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }


    @Override
    public SolutionVO mapRow(ResultSet resultSet, int i) throws SQLException {
        SolutionVO solutionVO = new SolutionVO();
        solutionVO.setContent_title(resultSet.getString("Title"));
        solutionVO.setExternal(resultSet.getBoolean("IsExternal"));
        solutionVO.setAuthor(resultSet.getString("Author"));
        solutionVO.setExpired_date(resultSet.getString("ExpiredDate"));
        solutionVO.setUpload_date(resultSet.getString("UploadDate"));
        solutionVO.setCustomer(resultSet.getString("Customer"));
        return solutionVO;
    }

}
