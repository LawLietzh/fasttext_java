package fasttext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import JBfenci.jiabaOne;

public class zxl_test
{
	public static void main(String[] args) throws IOException
	{
		  FastText fasttext = new FastText();  
		  String pathTest = "C://Users//zhangheng//Desktop//zxl//test3.txt";
		  String pathzxl = "C://Users//zhangheng//Desktop//zxl//jieg.txt";
		  
		 //写数据
		FileOutputStream fos = new FileOutputStream(pathzxl);   
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
			
		//读数据
			InputStreamReader read = new InputStreamReader(new FileInputStream(pathTest),"GBK");	
			BufferedReader bufferedReader = new BufferedReader(read);	
			String line= null;
				int  t = 0;
				int z = 0;
	
	   
	      fasttext.loadModel("model//model3.bin");  
	   
		  while((line = bufferedReader.readLine() ) != null)
		  {
			 
			  String strline = line.split("#")[0];
			  String strlabel = line.split("#")[1];
			  System.out.println(strline);
			  
			  t++;
			  String[] test= strline.split(" ");
			  List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的六个预测概率  
		 
			  
			   /*
			    * 出现的问题是，有时候，0 或者1  不显示，要判断一下
			    * 
			    */
			      
			      String k0 =  (list.get(0).getValue()).replaceAll("#", "")  ;
			      String k1 =  (list.get(1).getValue()).replaceAll("#", "")  ;
			      if(k0.equals("0"))
			      {
			    	  k1="1";
			      }else if(k0.equals("1"))
			      {
			    	  k1="0";
			      }
			      System.out.println("ko="+k0+"    k1:"+k1);
			      double v0 = Math.exp(list.get(0).getKey());
			      double v1 = Math.exp(list.get(1).getKey());
			      System.out.println("v0 :"+v0 +"v1:"+v1);
			     if(strlabel.equals(k1))
			     {
			    	 z++;
			     }
			 
			    System.out.println("strlabel  :"+strlabel+"         k0   :"+k0);  //得到最大预测概率  

		  }
			
			System.out.println("t:"+t+"    z:"+z +"   z/t:"+z/t);
			
			
	}

}
