import java.io.*;
import java.util.*;
public class day2 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String s;
		int cnt = 1;
		int ret = 0;
		while((s = scan.readLine()) != null) {
			String[] removecolon = s.split(": ");
			String[] sets = removecolon[1].split("; ");
			boolean flag = true;
			for(String set : sets) {
				String[] colors = set.split(", ");
				for(String color : colors) {
					String[] info = color.split(" ");
					int i = Integer.parseInt(info[0]);
					if(info[1].equals("red"))
						flag &= (i <= 12);
					if(info[1].equals("green"))
						flag &= (i <= 13);
					if(info[1].equals("blue"))
						flag &= (i <= 14);
				}
			}
			if(flag)
				ret += cnt;
			cnt++;
		}
		System.out.println(ret);
		scan.close();
		
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String s;
		int ret = 0;
		while((s = scan.readLine()) != null) {
			String[] removecolon = s.split(": ");
			String[] games = removecolon[1].split("; ");
			int r = 0, g = 0, b = 0;
			for(String game : games) {
				String[] colors = game.split(", ");
				for(String color : colors) {
					String[] info = color.split(" ");
					int i = Integer.parseInt(info[0]);
					if(info[1].equals("red"))
						r = Math.max(i, r);
					if(info[1].equals("green"))
						g = Math.max(g, i);
					if(info[1].equals("blue"))
						b = Math.max(b, i);
				}
			}
			ret += r * g * b;
		}
		System.out.println(ret);
		scan.close();
	}
}
