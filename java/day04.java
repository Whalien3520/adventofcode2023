import java.io.*;
import java.util.*;
import java.util.regex.*;
public class day04 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String buffer;
		int ret = 0;
		while((buffer = scan.readLine()) != null) {
			String[] arr = buffer.split("\\|");
			HashSet<Integer> set = new HashSet<>();
			int pts = 0;
			for(String s : arr[0].split(" ")) {
				try {
					set.add(Integer.parseInt(s));
				} catch (NumberFormatException e) {}
			}
			for(String s : arr[1].split(" ")) {
				try {
					if(set.contains(Integer.parseInt(s)))
						pts = (pts == 0) ? 1 : pts * 2;
				} catch (NumberFormatException e) {}
			}
			ret += pts;
		}
		System.out.println(ret);
		scan.close();
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String buffer;
		int ind = 0;
		ArrayList<Integer> list = new ArrayList<>();
		while((buffer = scan.readLine()) != null) {
			String[] arr = buffer.split("\\|");
			HashSet<Integer> set = new HashSet<>();
			int cnt = 0;
			for(String s : arr[0].split(" ")) {
				try {
					set.add(Integer.parseInt(s));
				} catch (NumberFormatException e) {}
			}
			for(String s : arr[1].split(" ")) {
				try {
					if(set.contains(Integer.parseInt(s)))
						cnt++;
				} catch (NumberFormatException e) {}
			}
			if(list.size() < ind + 1)
				list.add(1);
			else
				list.set(ind, list.get(ind) + 1);
			for(int i = ind + 1; i <= ind + cnt; i++) {
				if(list.size() < i + 1)
					list.add(list.get(ind));
				else
					list.set(i, list.get(i) + list.get(ind));
			}
			ind++;
		}
		int ret = 0;
		for(int i : list)
			ret += i;
		System.out.println(ret);
		scan.close();	
	}
}
