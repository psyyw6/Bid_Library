package atos.wordExport;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadDoc {

    @Test
    public void testTemplateWrite() throws Exception {
        InputStream fis = new FileInputStream("/Users/realmadrid/Desktop/Project/AO001-1.docx");
        XWPFDocument doc = new XWPFDocument(fis);
        List<XWPFParagraph> paras = doc.getParagraphs();
//        for(XWPFParagraph temp : paras){
//            System.out.println(temp.getText());
//        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("SolutionDescription","this is the replacement");
        this.replaceInPara(doc, params);
        OutputStream os = new FileOutputStream("/Users/realmadrid/Desktop/TestDocx.docx");
        doc.write(os);
        fis.close();
        os.close();

    }
    private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para,params);
        }
    }

    private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();

            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                System.out.println(runText);
                matcher = this.matcher(runText);
                if (matcher.find()) {
                    System.out.println("yes");
                    while ((matcher = this.matcher(runText)).find()) {
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                    }
                    //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                    //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                    para.removeRun(i);
                    para.insertNewRun(i).setText(runText);
                }
            }
        }
    }

    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }





}