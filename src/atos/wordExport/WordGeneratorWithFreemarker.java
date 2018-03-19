package atos.wordExport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**
 * @Description:word导出帮助类 通过freemarker模板引擎来实现
 * @author:LiaoFei
 * @date :2016-3-24 下午3:49:25
 * @version V1.0
 * 
 */
public class WordGeneratorWithFreemarker {
	private static Configuration configuration = null;

	static {
		configuration = new Configuration(Configuration.VERSION_2_3_23);
		configuration.setDefaultEncoding("utf-8");
		configuration.setClassicCompatible(true);
		configuration.setClassForTemplateLoading(
				WordGeneratorWithFreemarker.class,
				"/atos/ftl");

	}

	private WordGeneratorWithFreemarker() {

	}

	public static void createDoc(Map<String, Object> dataMap,String templateName, OutputStream out)throws Exception {
		Template t = configuration.getTemplate(templateName);
		WordHtmlGeneratorHelper.handleAllObject(dataMap);

		try {
			Writer w = new OutputStreamWriter(out,Charset.forName("utf-8"));
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	public static void main(String[] args) throws Exception {
		HashMap<String, Object> data = new HashMap<String, Object>();

		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<p><img src='upload/Atos_logo.png' style='max-width:100%;'></p>");
//		sb.append("<img style='height:100px;width:200px;display:block;' src='/Users/realmadrid/Desktop/BidLibrary/Bid_Library/out/WEB-INF/view/upload/ato_icon.png'/>");
//		sb.append("<img style='height:100px;width:200px;display:block;' src='upload/search.png/'>");
		sb.append("<br><span>中国梦，幸福梦！</span>");
		sb.append("</div>");
		RichHtmlHandler handler = new RichHtmlHandler(sb.toString());
//
//		handler.setDocSrcLocationPrex("file:///C:/8595226D");
//		handler.setDocSrcParent("file3405.files");
//		handler.setNextPartId("01D214BC.6A592540");
		handler.setDocSrcLocationPrex("file:///C:/213792E5");
		handler.setDocSrcParent("RichTemplate.fld");
		handler.setNextPartId("01D3BED8.C8C9FB50");
		handler.setShapeidPrex("_x56fe__x7247__x0020");
		handler.setSpidPrex("_x0000_i");
		handler.setTypeid("#_x0000_t75");

		handler.handledHtml(false);

		String bodyBlock = handler.getHandledDocBodyBlock();
		System.out.println("bodyBlock:\n"+bodyBlock);

		String handledBase64Block = "";
		if (handler.getDocBase64BlockResults() != null
				&& handler.getDocBase64BlockResults().size() > 0) {
			for (String item : handler.getDocBase64BlockResults()) {
				handledBase64Block += item + "\n";
			}
		}
		data.put("imagesBase64String", handledBase64Block);

		String xmlimaHref = "";
		if (handler.getXmlImgRefs() != null
				&& handler.getXmlImgRefs().size() > 0) {
			for (String item : handler.getXmlImgRefs()) {
				xmlimaHref += item + "\n";
			}
		}
		data.put("imagesXmlHrefString", xmlimaHref);
		data.put("Name", "张三");
		data.put("Content", bodyBlock);

		String docFilePath = "/Users/realmadrid/Desktop/RichTest.doc";
		System.out.println(docFilePath);
		File f = new File(docFilePath);
		OutputStream out;
		try {
			out = new FileOutputStream(f);
//			WordGeneratorWithFreemarker.createDoc(data, "temp.ftl", out);
			WordGeneratorWithFreemarker.createDoc(data, "RichTemplate.ftl", out);


		} catch (FileNotFoundException e) {

		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}