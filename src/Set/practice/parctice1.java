package Set.practice;

import Set.Set;
import Set.ListSet;
import Set.TreeSet;
import util.Times;
import util.file.FileInfo;
import util.file.Files;

/**
 * 统计文件中单词数量	【Set实现】
 */
public class parctice1 {
	/**
	 * 统计文件中共有多少不同的单词
	 */
	static void wordsCount(){
		FileInfo fileInfo = Files.read("C:\\Users\\Administrator\\Desktop", new String[]{"java"});
		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量(有重复)：" + words.length);

		Set<String> listSet = new ListSet<String>();
		for (int i = 0; i < words.length; i++) {
			listSet.add(words[i]);
		}
		System.out.println("单词数量(无重复)：" + listSet.size());
	}
	/**
	 * 测试Set性能
	 */
	static void testSet(Set<String> set, String[] words){
		for (int i = 0; i < words.length; i++) {
			set.add(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.contains(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			set.remove(words[i]);
		}
	}
	static void test(){
		FileInfo fileInfo = Files.read("C:\\Users\\Administrator\\Desktop", new String[]{"java"});
		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量(有重复)：" + words.length);

		System.out.println("-------------------------性能测试-------------------------");
		Times.test("ListSet", new Times.Task() {
			public void execute() {
				testSet(new ListSet<String>(),words);
			}
		});
		Times.test("TreeSet", new Times.Task() {
			public void execute() {
				testSet(new TreeSet<String>(),words);
			}
		});
	}

	public static void main(String[] args) {
		test();
	}
}
