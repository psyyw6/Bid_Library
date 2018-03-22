package atos.wordExport;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Map;

public class ExportWord {

    private Configuration configuration = null;

    public void configure(String templatePath){
        configuration = new Configuration(new Version("2.3.27"));
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(this.getClass(),templatePath);

    }

    public void create(String templatePath, String templateName, String filePath, String filename, Map<String,Object> map){
        this.configure(templatePath);
        Template template = null;
        try{
            template = configuration.getTemplate(templateName);
        }catch (IOException e){
            e.printStackTrace();
        }

        File outfile = new File(filePath+filename);
        Writer out = null;
        try{
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile),"UTF-8"));
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(map!=null){
            try {
                template.process(map,out);
                out.close();
            }catch (TemplateException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public String getImageStr(String imgPath){
        String imgFile = imgPath;
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read();
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

}
