package fasttext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import JBfenci.jiabaOne;

//标题分类   输入  文本   
//返回  可能性最大的 三个预测
public class gBiaoTi 
{
	//hash表 按照 vlaue 排序 
	public static Map<String, Double> sortMap(Map<String, Double> oldMap) 
	{  
		
        ArrayList<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(oldMap.entrySet());  
        Collections.sort(  
        		list, new Comparator<Map.Entry<String, Double>>() 
                                   {  
                              @Override  
                             public int compare(Entry<java.lang.String, Double> arg0,  Entry<java.lang.String, Double> arg1) 
                                           {  
                                                           return (int) (arg1.getValue() - arg0.getValue());  
                                             }

                                   }
        		          );  
        Map<String, Double> newMap = new LinkedHashMap<String, Double>();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        System.out.println("按value 值分类");
        return newMap;  
    } 
	
	// 情感倾向性 二分类
	public static HashMap<String,Double> gSentiment(String str) throws IOException
	{
		
		  HashMap<String,Double>  hashmap = new HashMap<String,Double>();
		  FastText fasttext = new FastText();  
		  //String line = "小米雷军，工商登记情况 ，可在 一定 程万强洪峰四位创始人 。在工商登记，分别持股";
		  String line = str;
		   line = jiabaOne.jieba(line);
		   System.out.println(line);
		  String[] test= line.split(" ");
		  //test = guoLV(test);
	      System.out.println("dddd:"+test[0]);
	      fasttext.loadModel("model//sentiment_model.bin");  
	      List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的2个预测概率  
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
	      hashmap.put(k0, v0);
	      hashmap.put(k1, v1);
	      
	      System.out.println(k0+"    "  +hashmap.get(k0));
	      System.out.println(k1+"    "  +hashmap.get(k1));
	      return hashmap;
	}
	
	
	
	//  标题分类            输入  文本   返回  可能性最大的 几个预测
	public static Map<String,Double> gClassify(String str) throws IOException
	{
		
		String pathB = "model//product_label.txt";
		//读取 要过滤掉的标签  
		InputStreamReader readB = new InputStreamReader(new FileInputStream(pathB),"UTF-8");	
		BufferedReader bufferedReaderB = new BufferedReader(readB);	
		String lineB= null;
			
		Map<String,String> hashLabel = new HashMap<String,String>();
	    //把 要过滤的标签 添加到 hash表中
	    while((lineB = bufferedReaderB.readLine() ) != null)
	     {
	    	String label[] = lineB.split("  ");
	    	
		   hashLabel.put(label[0], label[1]);
	     }
		
	    
	    
		//存储 预测出来的 前几个概率最大的  类别名称 和  概率值
	    /*
	     * 限制条件：首先是 有二十个候选向
	     * 再者是：    概率值相加，当概率值超过90%的时候，就停止显示
	     */
		  Map<String,Double>  hashmap = new LinkedHashMap<String,Double>();
		  FastText fasttext = new FastText();  
		  //String line = "小米雷军，工商登记情况 ，可在 一定 程万强洪峰四位创始人 。在工商登记，分别持股";
		  String line = str;
		  System.out.println("我是原数据   ："+line);
		   line = jiabaOne.jieba(line);
		  System.out.println("我是分词结果："+line);
		  String[] test= line.split(" ");
		  //test = guoLV(test);
	      System.out.println("dddd:"+test[0]);
	      fasttext.loadModel("model//news_fasttext2.model.bin");  
		  
		  
		  /*
		   * 现在添加一些规则
		   */
		  String guizhe = "#";
		  if(line.contains("微波炉"))
		  {
			  guizhe = "微波炉";
		  }else if(line.contains("空调"))
		  {
			  guizhe = "空调";
		  }/*else if("")
		  {
			  guizhe = "";
		  }*/
	      
		  if( !guizhe.contains("#")){
			  System.out.println("我在规则中");
			  double v_w = 0.0;
			  String   k_w = guizhe;
			  hashmap.put(k_w, v_w);
			  List<Pair<Float, String>> list = fasttext.predict(test, 6);  //得到最大可能的2个预测概率  
		      //统计
		      double sum = 0;
		      for (Pair<Float, String> parir : list) 
		      {  
		    	  
		      	   //System.out.println( parir.getValue());  
		      	  String label = (parir.getValue()).replaceAll("#", "")  ;
		      	  double value = Math.exp(parir.getKey());
		      	  System.out.println("shuchu doubele::::"+value);
		      	  if(0.5> sum)
		      	  {
		      		  sum = sum + value;
		      		  String k0 =  "cat"+ label ;
		      		  //获得label 对应的 实际类别 如要要预测的 类别存在 就返回实际类别 
		      		  if(hashLabel.containsKey(k0) )
		      		  {
		      		       String k1 = hashLabel.get(k0);
		      		       if(!k1.equals(k_w))
		      		             {
		      			                  hashmap.put(k1, value);
		      		              }
		      		  
		      		
		      		    }
		      		  
		      	  }else
		      	  {
		      		hashmap.put(k_w, (1-sum));
		      		 break;
		      	  }
		      	  
		      
		       //    System.out.println("   leibie  is:"  + (parir.getValue()) +  " gailv zhi :" + Math.exp(parir.getKey()) );  
		              
		       }  
			  
			  
		  }else {
		  
	      //返回20 个 
	      List<Pair<Float, String>> list = fasttext.predict(test, 20);  //得到最大可能的2个预测概率  
	      //统计
	      double sum = 0;
	      for (Pair<Float, String> parir : list) 
	      {  
	    	  
	      	   //System.out.println( parir.getValue());  
	      	  String label = (parir.getValue()).replaceAll("#", "")  ;
	      	  double value = Math.exp(parir.getKey());
	      	
	      	  System.out.println("shuchu doubele::::"+value);
	      
	      	  if(0.99>= sum)
	      	  {
	      		  sum = sum + value;
	      		  String k0 =  "cat"+ label ;
	      		  //获得label 对应的 实际类别 如要要预测的 类别存在 就返回实际类别 
	      		  if(hashLabel.containsKey(k0))
	      		  {
	      		  String k1 = hashLabel.get(k0);
	      		  hashmap.put(k1, value);
	      		  }else {
	      			 //如果在影射表中不存在，就返回，影射前的label 表示
	      			hashmap.put(k0, value);
	      		  }
	      	  }else
	      	  {
	      		  break;
	      	  }
	      	  
	      
	       //    System.out.println("   leibie  is:"  + (parir.getValue()) +  " gailv zhi :" + Math.exp(parir.getKey()) );  
	              
	       }  
   }//if else 
	 	 Map<String, Double> newMap = new HashMap<String, Double>();  
	    newMap = hashmap;
	    
		return newMap;
	}
	
	public static String[] guoLV(String[] test)
	{
		
		 List<String> strList = new ArrayList<String>();
		  for(int i = 0;i<test.length;i++)
		  {
			  if(test[i].length()>1)
			  {
				  strList.add(test[i]);
			  }
		  }
		  
		  String[] arrtest= new String[strList.size()];
		  for(int j = 0  ;  j<strList.size() ; j++)
		  {
			  arrtest[j] = strList.get(j);
		  }
		return arrtest;
	}
	public static void main(String[] args) throws Exception
	{  
		
		
		
		//存储 预测出来的 前三个概率最大的  类别名称 和  概率值
	 /* HashMap<String,Double>  hashmap = new HashMap<String,Double>();
	  FastText fasttext = new FastText();  
	  String line = "小米雷军，工商登记情况 ，可在 一定 程万强洪峰四位创始人 。在工商登记，分别持股";
	   line = jiabaOne.jieba(line);
	   System.out.println(line);
	  String[] test= line.split(" ");
	  
	  
	  System.out.println("test.length"+test.length);
	 
	 String[]  arrtest = guoLV(test);
	  
	  
	  System.out.println("arrtest.length"+arrtest.length);
	  
      System.out.println("dddd:"+test[0]);
      fasttext.loadModel("model//model.bin");  
      List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的六个预测概率  
      double sum = 0;
      for (Pair<Float, String> parir : list) 
      {  
      	   //System.out.println( parir.getValue());  
      	  String label = (parir.getValue()).replaceAll("#", "")  ;
      	  double value = Math.exp(parir.getKey());
      
      	  if(0.6>= sum)
      	  {
      		  sum = sum + value;
      		  String k0 =  "cat"+ label ;
      		  hashmap.put(k0, value);
      		System.out.println("bbbb");
      	  }else {
      		System.out.println("aaaaaaaaa");
      		  break;
      	  }
      	  
           System.out.println("   leibie  is:"  + (parir.getValue()) +  " gailv zhi :" + Math.exp(parir.getKey()) );  
           
           
       }  
      
      
      String k0 =  "cat"+ (list.get(0).getValue()).replaceAll("#", "")  ;
      double v0 = Math.exp(list.get(0).getKey());
      hashmap.put(k0, v0);
      String k1 =  "cat"+ (list.get(1).getValue()).replaceAll("#", "")  ;
      double v1 = Math.exp(list.get(1).getKey());
      hashmap.put(k1, v1);
      String k2 =  "cat"+ (list.get(2).getValue()).replaceAll("#", "")  ;
      double v2 = Math.exp(list.get(2).getKey());
      hashmap.put(k2, v2);
      
      System.out.println(k0+"    "  +hashmap.get(k0));
      System.out.println(hashmap.get(k1));
      System.out.println(   "cat"+ (list.get(0).getValue()).replaceAll("#", "")   );  //得到最大的预测概率的                 类别
         System.out.println(   Math.exp(list.get(0).getKey())   );  //得到最大预测类别的        概率  
   
        System.out.println((list.get(1).getValue()));  //得到最大预测概率  类别
        System.out.println(Math.exp(list.get(1).getKey()));  //得到最大预测概率  
      */
      
     // gBiaoTiFenLei(line);
     // gSentiment(line);
		
		/*Map<String,Double>  hh = new HashMap<String,Double>();
		String ss = "TCL KFRd-51LW/FC13 大2匹 立柜式电辅型定频家用冷暖空调";
		hh=gSentiment(ss);
		System.out.println("###############");
		 Iterator<Entry<String,Double>> iter = hh.entrySet().iterator();
	      while(iter.hasNext()){
	          Map.Entry<String, Double> m = iter.next();
	          System.out.println("key:    "+m.getKey()+"     对应的值为："+m.getValue());
	      }*/
		String ss = "微波炉";
		System.out.println(  "我是 ss:"+ ss);
		 ss = jiabaOne.jieba(ss);
		 System.out.println("我是分词结果ss："+ss);
		
		
		
		
     Map<String,Double>  hh = new HashMap<String,Double>();
     //String str = "美的 ( midea ) 1.5 匹 变频 冷暖 壁挂式 空调 智弧 kfr-35gw wdaa3";
     //String str =  "微波炉 松下 panasonic nn-cs1000xpe 30升 空调";
     String str =  "微波炉 ";
     System.out.println(  "我是 str:"+ str);
     String strline = str.toLowerCase();
      hh = gClassify(strline);
      
      System.out.println("例外");
      Iterator<Entry<String,Double>> iter = hh.entrySet().iterator();
      while(iter.hasNext()){
          Map.Entry<String, Double> m = iter.next();
          System.out.println("key:    "+m.getKey()+"     对应的值为："+m.getValue());
       }
      
	
	}

}
