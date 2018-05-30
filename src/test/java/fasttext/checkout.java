package fasttext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class checkout {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		    String encoding="GBK";
		    //读文件
			File file=new File("C://Users//zhangheng//Desktop//zxl//test3.txt");
			InputStreamReader input=new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader read=new BufferedReader(input);
	        FastText fasttext = new FastText(); 
	        fasttext.loadModel("model/model3.bin");  
	        String line=null;
	        //写文件
	        File file1=new File("C://Users//zhangheng//Desktop//zxl//jieg.txt");
	        FileWriter fw=new FileWriter(file1);
	        PrintWriter pw=new PrintWriter(fw);
	        int right=0;
	        int wrong=0;
	        while ((line=read.readLine())!=null) {
//	        	pw.println(line);
	        	String[] lines=line.split(" ");
	        	pw.print(lines[lines.length-1]);
	        	String strline = line.split("#")[0];
	     //   	System.out.println(strline);
	        	 String[] test= strline.split(" ");
	             List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的六个预测概率  
	             if (list.get(0).getKey()>list.get(1).getKey()) {
	            	 pw.print(" "+list.get(0).getValue());
	            	 if (list.get(0).getValue().equals(lines[lines.length-1])) {
					    right++;	
					}else{
						wrong++;
					}
				 }else{
					 pw.print(" "+list.get(1).getValue());
					 if (list.get(1).getValue().equals(lines[lines.length-1])) {
					right++;	
					}else{
						wrong++;
					}
					 
				 }
	             pw.println();
	        }
	        System.out.println("正确的个数为："+right);
	        System.out.println("错误的个数为："+wrong);
	        fw.flush();
	        pw.flush();
	        fw.close();
	        pw.close();
	        read.close();
	}

}
