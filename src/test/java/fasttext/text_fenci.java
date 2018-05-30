package fasttext;
import JBfenci.jiabaOne;
public class text_fenci
{
	public static void main(String str)
	{
		String line = "西铁城citizen光动能商务男表";
		String sss  = jiabaOne.jieba(line);
		System.out.println(sss);
	}

}
