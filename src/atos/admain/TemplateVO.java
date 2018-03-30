package atos.admain;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateVO implements RowMapper<TemplateVO>, Serializable {

    private String template_name;
    private String doc_src_prefix_location;
    private String next_part_id;
    private String doc_src_parent;
    private String cover_image;

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getDoc_src_prefix_location() {
        return doc_src_prefix_location;
    }

    public void setDoc_src_prefix_location(String doc_src_prefix_location) {
        this.doc_src_prefix_location = doc_src_prefix_location;
    }

    public String getNext_part_id() {
        return next_part_id;
    }

    public void setNext_part_id(String next_part_id) {
        this.next_part_id = next_part_id;
    }

    public String getDoc_src_parent() {
        return doc_src_parent;
    }

    public void setDoc_src_parent(String doc_src_parent) {
        this.doc_src_parent = doc_src_parent;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    @Override
    public TemplateVO mapRow(ResultSet resultSet,int i) throws SQLException{
        TemplateVO templateVO = new TemplateVO();
        templateVO.setTemplate_name(resultSet.getString("Template_Name"));
        templateVO.setDoc_src_prefix_location(resultSet.getString("DocSrcPrefixLocation"));
        templateVO.setNext_part_id(resultSet.getString("NextPartId"));
        templateVO.setDoc_src_parent(resultSet.getString("DocSrcParent"));
        templateVO.setCover_image(resultSet.getString("CoverImage"));
        return templateVO;
    }

}
