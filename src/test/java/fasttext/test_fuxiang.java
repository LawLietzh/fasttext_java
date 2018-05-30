package fasttext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.List;  

import fasttext.FastText;  
import fasttext.Main;  
import fasttext.Pair;  
public class test_fuxiang 
{
	public static void main(String[] args) throws IOException
	{
		 FastText fasttext = new FastText();  
		String pathY = "D://data//csv//ss.txt";
		String pathX = "D://data//csv//yuzhi.txt";	
		FileOutputStream fos = new FileOutputStream(pathX);   
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		//	 String line = "小米 雷军 ， 乐视 贾跃亭 ， 是 中国 为数不多 富有 创新 与 冒险 精神 的 企业家 。 一直 以来 ， 雷军 提出 小米 生态 链 与 贾跃亭 乐视 生态化 反 ， 是 传统 制造业 与 互联网 融合 模式 探索 。 在 早期 ， 两者 都 取得 了 巨大 的 成功 ， 获得 了 媒体 的 关注 和 赞誉 ； 但 现在 ， 无论 小米 与 乐视 ， 总是 被 各种 负面 报道 伴随 着 ， 不仅 让 我们 思考 ： 这 两家 都 宣称 做 生态 的 企业 ， 究竟 谁 会 胜出 ， 差距 在 哪里 呢 ？ 通过 窥探 两者 股权 架构 的 设计 ， 我们 或许 可以 看出 一丝 端倪 。 先看 雷军 的 小米 的 股权 架构 ， 很 有 参考 雷军 小米 是 海外 架构 ， 通过 查询 国内 工商登记 情况 ， 可 在 一定 程度 上 了解 这 两个 生态 的 股权 架构 情况 。 下图 是 小米 集团 的 股权 架构 ， 顶层 是 雷军 、 黎万强 、 洪峰 、 刘德 四位 创始人 。 在 工商登记 ， 分别 持股 77";
		   
		InputStreamReader read = new InputStreamReader(new FileInputStream(pathY),"UTF-8");	
		BufferedReader bufferedReader = new BufferedReader(read);	
		String line= null;
			int  t = 0;
			int z = 0;
			 fasttext.loadModel("E://fasttext_model//6yue15hao//news_fasttext.model.bin");  
	  while((line = bufferedReader.readLine() ) != null)
	  {
		     t++;
		     String[] test= line.split(" ");
		
		    
		     List<Pair<Float, String>> list = fasttext.predict(test, 2);  //得到最大可能的六个预测概率  
		    // System.out.println( (list.get(0)).getKey());  
		     if(   Math.exp((list.get(0)).getKey())   >  0.97 )
		     {
		    	 z++;
		    	 System.out.println(    Math.exp((list.get(0)).getKey())    );  
		    	 
		    	 osw.write(    Math.exp((list.get(0)).getKey()) +" : "+line   )   ;
		    	// System.out.println((list.get(0)).getKey()+" : "+line);  
                 osw.write("\r\n");
                 osw.flush();
		     }
		     
		    /* for (Pair<Float, String> parir : list) 
		     {  
		     	   System.out.println( parir.getValue());  
		     	   System.out.println( parir.toString());  
		            System.out.println("key is:" + Math.exp(parir.getKey()) + "   value is:"  + parir.getValue());  
		      }  */
		  //System.out.println(Math.exp(list.get(0).getKey()));  //得到最大预测概率  
		  
		     
		/*     
		     if((list.get(0).getValue()).equals("#0"))
		     {
		    	 z++;
		    	 System.out.println(line);  
		     }else if((list.get(0).getValue()).equals("#1"))
		     {
		    	 //System.out.println(line);  
		    	//System.out.println(Math.exp(list.get(0).getKey()));  
		    	
		     }
		    // System.out.println((list.get(0).getValue()));  //得到最大预测概率  

		 */
	  }
		
		
		System.out.println("t:"+t+"    z:"+z +"   z/t:"+z/t);
		
	}
	 

}
