import java.io.*;
import java.util.*;
public class day3 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String buffer;
		while((buffer = scan.readLine()) != null)
			list.add(buffer);
		scan.close();
		int ret = 0;
		for(int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			for(int j = 0; j < line.length(); j++) {
				if(!Character.isDigit(line.charAt(j)))
					continue;
				int len = 1;
				while((j + len) < line.length() && Character.isDigit(line.charAt(j + len)))
					len++;
				boolean flag = false;
				if(j > 0)
					flag |= isSymb(line.charAt(j - 1));
				if(j + len < line.length() && !flag)
					flag |= isSymb(line.charAt(j + len));
				if(i > 0)
					for(int c = Math.max(j - 1, 0); c < Math.min(line.length(), j + len + 1) && !flag; c++)
						flag |= isSymb(list.get(i - 1).charAt(c));
				if(i + 1 < list.size())
					for(int c = Math.max(j - 1, 0); c < Math.min(line.length(), j + len + 1) && !flag; c++)
						flag |= isSymb(list.get(i + 1).charAt(c));
				if(flag)
					ret += Integer.parseInt(line.substring(j, j + len));
				j = j + len;
			}
		}
		System.out.println(ret);
	}
	public static boolean isSymb(Character c) {
		return !Character.isDigit(c) && c != '.';
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String buffer;
		while((buffer = scan.readLine()) != null)
			list.add(buffer);
		scan.close();
		int ret = 0;
		for(int i = 0; i < list.size(); i++) {
			String line = list.get(i);
			for(int j = 0; j < line.length(); j++) {
				if(line.charAt(j) != '*')
					continue;
				ArrayList<Integer> nums = new ArrayList<>();
				if(i > 0)
					addNums(list.get(i - 1), nums, j);
				if(j > 0 && Character.isDigit(line.charAt(j - 1)))
					addNum(line, nums, j - 1);
				if(j + 1 < line.length() && Character.isDigit(line.charAt(j + 1)))
					addNum(line, nums, j + 1);
				if(i + 1 < list.size())
					addNums(list.get(i + 1), nums, j);
				if(nums.size() == 2)
					ret += nums.get(0) * nums.get(1);
			}
		}
		System.out.println(ret);
		scan.close();	
	}
	public static void addNums(String s, ArrayList<Integer> list, int ind) {
		if(ind > 0 && Character.isDigit(s.charAt(ind - 1))) {
			addNum(s, list, ind - 1);
			
			if(!Character.isDigit(s.charAt(ind)) && ind + 1 < s.length() && Character.isDigit(s.charAt(ind + 1)))
				addNum(s, list, ind + 1);
		}
		else {
			if(Character.isDigit(s.charAt(ind)))
				addNum(s, list, ind);
			else if(ind + 1 < s.length() && Character.isDigit(s.charAt(ind + 1)))
				addNum(s, list, ind + 1);
		}
	}
	public static void addNum(String s, ArrayList<Integer> list, int ind) {
		int left = ind, right = ind;
		while(left > 0 && Character.isDigit(s.charAt(left - 1)))
			left--;
		while(right + 1 < s.length() && Character.isDigit(s.charAt(right + 1)))
			right++;
		list.add(Integer.parseInt(s.substring(left, right + 1)));
		
	}
}
