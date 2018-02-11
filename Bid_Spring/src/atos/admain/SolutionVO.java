package atos.admain;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolutionVO implements RowMapper<SolutionVO>, Serializable{
    private String solution_title;
    private String heading;
    private boolean isExternal;
    private String creator;
    private String expired_date;
    private String upload_date;
    private double version;
    private String customer;
    private String content;

    public String getSolution_title() {
        return solution_title;
    }

    public void setSolution_title(String solution_title) {
        this.solution_title = solution_title;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    @Override
    public SolutionVO mapRow(ResultSet resultSet, int i) throws SQLException {
        SolutionVO solutionVO = new SolutionVO();
        solutionVO.setSolution_title(resultSet.getString("S_Title"));
        solutionVO.setHeading(resultSet.getString("Heading"));
        solutionVO.setExternal(resultSet.getBoolean("IsExternal"));
        solutionVO.setCreator(resultSet.getString("Creator"));
        solutionVO.setExpired_date(resultSet.getString("ExpiredDate"));
        solutionVO.setUpload_date(resultSet.getString("UploadDate"));
        solutionVO.setVersion(resultSet.getDouble("Version"));
        solutionVO.setCustomer(resultSet.getString("Customer"));
        solutionVO.setContent(resultSet.getString("Content"));
        return solutionVO;

    }


    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }
}
