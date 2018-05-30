package fasttext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class map_valuePaiXu 
{
	public static Map<String, Double > sortMap(Map<String, Double > oldMap) 
	{  
        ArrayList<Map.Entry<String, Double >> list = new ArrayList<Map.Entry<String, Double >>(oldMap.entrySet());  
        Collections.sort(  
        		list, new Comparator<Map.Entry<String, Double >>() 
                                   {  
                              @Override  
                             public int compare(Entry<java.lang.String, Double > arg0,  Entry<java.lang.String, Double > arg1) 
                                           {  
                                                           return (int) (arg1.getValue() - arg0.getValue());  
                                             }

                                   }
        		          );  
        Map<String, Double > newMap = new LinkedHashMap<String, Double >();  
        for (int i = 0; i < list.size(); i++) {  
            newMap.put(list.get(i).getKey(), list.get(i).getValue());  
        }  
        return newMap;  
    } 
	public static void main(String[] args)
	{
		
		  HashMap<String,Double>  map = new HashMap<String,Double>();
		  map.put("图书" ,  0.07031251861553034);
	      map.put("音像" , 1.9531265169053625E-8);
	      map.put("素材" , 9.0);
	      map.put("音乐" , 8.0);
	      map.put("影视" , 7.0);
	      map.put("动漫" , 4.0);
	      map.put("歌曲" , 3.0);
	      map.put("图片" , 2.0);
	      map.put("图标" , 6.0);
	      Map<String, Double> newMap = new HashMap<String, Double>();  
	      newMap = sortMap(map);
	      Iterator iter = newMap.entrySet().iterator(); 
	      while (iter.hasNext()) { 
	          Map.Entry<String,Double> entry = (Map.Entry<String,Double>) iter.next(); 
	          String key = entry.getKey(); 
	          double val = entry.getValue(); 
	          System.out.println(key+"     "+val);
	      } 
	      
	}

}
