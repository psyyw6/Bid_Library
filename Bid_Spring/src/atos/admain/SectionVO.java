package atos.admain;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionVO implements RowMapper<SectionVO>, Serializable {
    private String section_name;
    private String title;
    private String section_version;
    private String section_details;

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection_version() {
        return section_version;
    }

    public void setSection_version(String section_version) {
        this.section_version = section_version;
    }

    public String getSection_details() {
        return section_details;
    }

    public void setSection_details(String section_details) {
        this.section_details = section_details;
    }

    @Override
    public SectionVO mapRow(ResultSet resultSet, int i) throws SQLException {
        SectionVO sectionVO = new SectionVO();
        sectionVO.setTitle(resultSet.getString("Title"));
        sectionVO.setSection_name(resultSet.getString("Section_Name"));
        sectionVO.setSection_version(resultSet.getString("Section_Version"));
        sectionVO.setSection_details(resultSet.getString("Section_Detail"));

        return sectionVO;
    }
}
