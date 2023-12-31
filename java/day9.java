import java.io.*;
import java.util.*;
public class day9 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String line;
		int ret = 0;
		while((line = scan.readLine()) != null) {
			//System.out.println("Hello World!");
			int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			int[][] tab = new int[arr.length + 1][];
			tab[0] = new int[arr.length + 1];
			for(int i = 0; i < arr.length; i++)
				tab[0][i] = arr[i];
			int t = 0;
			while(!zeros(tab[t])) {
				t++;
				tab[t] = new int[tab[t - 1].length - 1];
				for(int i = 0; i < tab[t - 1].length - 2; i++)
					tab[t][i] = tab[t - 1][i + 1] - tab[t - 1][i];
			}
			//not necessary since java intarray values are initialized to 0
			//tab[t][tab[t].length - 1] = 0;
			t--;
			while(t >= 0) {
				tab[t][tab[t].length - 1] = tab[t][tab[t].length - 2] + tab[t + 1][tab[t + 1].length - 1];
				t--;
			}
			ret += tab[0][tab[0].length - 1];
		}
		System.out.println(ret);
		scan.close();
	}
	public static boolean zeros(int[] arr) {
		boolean ret = true;
		for(int i = 0; i < arr.length && ret; i++)
			ret = arr[i] == 0;
		return ret;
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String line;
		int ret = 0;
		while((line = scan.readLine()) != null) {
			//System.out.println("Hello World!");
			int[] arr = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			int[][] tab = new int[arr.length + 1][];
			tab[0] = new int[arr.length + 1];
			for(int i = 1; i < arr.length; i++)
				tab[0][i] = arr[i - 1];
			int t = 0;
			while(!zeros(tab[t])) {
				t++;
				tab[t] = new int[tab[t - 1].length - 1];
				for(int i = 1; i < tab[t - 1].length - 2; i++)
					tab[t][i] = tab[t - 1][i + 1] - tab[t - 1][i];
			}
			t--;
			while(t >= 0) {
				tab[t][0] = tab[t][1] - tab[t + 1][0];
				t--;
			}
			ret += tab[0][0];
		}
		System.out.println(ret);
		scan.close();
	}
}
