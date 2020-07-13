package Set.practice;

import Map.Map;
import Map.TreeMap;
import util.file.FileInfo;
import util.file.Files;

/**
 * 统计文件中单词数量	【Map实现】
 */
public class practice2 {
    public static void main(String[] args) {
        FileInfo fileInfo = Files.read("C:\\Users\\Administrator\\Desktop", new String[]{"java"});
        System.out.println("文件数量：" + fileInfo.getFiles());
        System.out.println("代码行数：" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量(有重复)：" + words.length);

        Map<String,Integer> map = new TreeMap<String,Integer>();
        for (int i = 0; i < words.length; i++) {
            Integer count = map.get(words[i]);
            count = count==null ? 0 : count;
            map.put(words[i],count+1);
            /*count = count==null ? 1 : (count+1);
            map.put(words[i],count);*/
        }
        System.out.println(map.size());
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
