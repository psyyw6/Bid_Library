package atos.admain;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadLogVO implements RowMapper<DownloadLogVO>,Serializable {

    private int download_id;
    private String download_name;
    private String selected_template;
    private String selected_contents;
    private String user;
    private String download_time;

    @Override
    public DownloadLogVO mapRow(ResultSet resultSet, int i) throws SQLException {
        DownloadLogVO downloadLogVO = new DownloadLogVO();
        downloadLogVO.setDownload_id(resultSet.getInt("Download_ID"));
        downloadLogVO.setDownload_name(resultSet.getString("Download_Name"));
        downloadLogVO.setSelected_template(resultSet.getString("Selected_Template"));
        downloadLogVO.setSelected_contents(resultSet.getString("Selected_Contents"));
        downloadLogVO.setDownload_time(resultSet.getString("Download_Time"));
        downloadLogVO.setUser(resultSet.getString("User"));

        return downloadLogVO;
    }

    public int getDownload_id() {
        return download_id;
    }

    public void setDownload_id(int download_id) {
        this.download_id = download_id;
    }

    public String getDownload_name() {
        return download_name;
    }

    public void setDownload_name(String download_name) {
        this.download_name = download_name;
    }

    public String getSelected_template() {
        return selected_template;
    }

    public void setSelected_template(String selected_template) {
        this.selected_template = selected_template;
    }

    public String getSelected_contents() {
        return selected_contents;
    }

    public void setSelected_contents(String selected_contents) {
        this.selected_contents = selected_contents;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDownload_time() {
        return download_time;
    }

    public void setDownload_time(String download_time) {
        this.download_time = download_time;
    }
}
