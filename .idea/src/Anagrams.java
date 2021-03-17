//java1.5
import java.net.*;
import java.io.*;
import java.util.*;

class WordsOfEqChars {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        //isr以流的形式存储上面网页中的单词集（输入数据）
        InputStreamReader isr = new InputStreamReader(url.openStream());
        //reader每次读取一行 也就是一个单词
        BufferedReader reader = new BufferedReader(isr);

        //anagrams是一个存储相同字母组成的单词的map 其中key存放这个单词字母排序后的单词排列 value是这些字母组成的单词集合 方便输出最后结果
        Map<String, Collection<String>> anagrams = new HashMap<String, Collection<String>>();
        String word;
        int count = 0;
        //单词不为空就对该单词进行检查
        while ((word = reader.readLine()) != null) {
            //将单词的字母拆开存入chars数组
            char[] chars = word.toCharArray();
            //对拆开的字母进行排序 用来发现相同字母组成的单词
            Arrays.sort(chars);
            //key是字母排序之后的单词
            String key = new String(chars);
            //根据排序后key的找有没有相同的 如果anagrams中不存在这个排列 就说明还没出现过这个单词排列
            if (!anagrams.containsKey(key))
                //未出现过就把这个序列放到map中 key是排好序的单词序列
                anagrams.put(key, new ArrayList<String>());
            //把每个单词添加到这个排列对应的集合中
            anagrams.get(key).add(word);
            //count用于记录当前相同字母组成的单词的最多的个数
            count = Math.max(count, anagrams.get(key).size());
        }
 		//关闭流
        reader.close();
 		//遍历map
        for (Collection<String> ana : anagrams.values())
            //如果当前排列含有的单词数大于等于最大值（实际上不会大于） 就输出这个集合（字谜）
            if (ana.size() >= count) {

                System.out.println(ana);
            }

    }

}



