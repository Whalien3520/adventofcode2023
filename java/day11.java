import java.io.*;
import java.math.BigInteger;
import java.util.*;
public class day11 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String input;
		while((input = scan.readLine()) != null)
			list.add(input);
		
		char[][] map = new char[list.size()][];
		for(int i = 0; i < list.size(); i++)
			map[i] = list.get(i).toCharArray();
		int[][] prefixes = createPrefixes(list, 1);
		
		ArrayList<int[]> coords = new ArrayList<>();
		int ret = 0;
		for(int r = 0; r < map.length; r++)
			for(int c = 0; c < map[r].length; c++) {
				if(map[r][c] != '#')
					continue;
				int[] arr = {r + prefixes[0][r], c + prefixes[1][c]};
				for(int[] coord : coords) {
					ret += Math.abs(coord[0] - arr[0]);
					ret += Math.abs(coord[1] - arr[1]);
				}
				coords.add(arr);
			}
		
		System.out.println(ret);
		scan.close();
	}
	public static int[][] createPrefixes(ArrayList<String> list, int i) {
		int[][] ret = new int[2][];
		
		ret[0] = new int[list.size()];
		ret[0][0] = 0;
		if(list.get(0).indexOf('#') == -1)
			ret[0][0] += i;
		for(int r = 1; r < list.size(); r++) {
			ret[0][r] = ret[0][r - 1];
			if(list.get(r).indexOf('#') == -1)
				ret[0][r] += i;
		}
		
		ret[1] = new int[list.get(0).length()];
		ret[1][0] = 0;
		boolean found = false;
		for(int j = 0; j < list.size() && !found; j++)
			found = list.get(j).charAt(0) == '#';
		if(!found)
			ret[1][0] += i;
		for(int c = 1; c < list.get(0).length(); c++) {
			ret[1][c] = ret[1][c - 1];
			found = false;
			for(int r = 0; r < list.size() && !found; r++)
				found = list.get(r).charAt(c) == '#';
			if(!found)
				ret[1][c] += i;
		}
		
		return ret;
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String input;
		while((input = scan.readLine()) != null)
			list.add(input);
		
		char[][] map = new char[list.size()][];
		for(int i = 0; i < list.size(); i++)
			map[i] = list.get(i).toCharArray();
		int[][] prefixes = createPrefixes(list, 999999);
		
		ArrayList<int[]> coords = new ArrayList<>();
		BigInteger ret = BigInteger.ZERO;
		for(int r = 0; r < map.length; r++)
			for(int c = 0; c < map[r].length; c++) {
				if(map[r][c] != '#')
					continue;
				int[] arr = {r + prefixes[0][r], c + prefixes[1][c]};
				for(int[] coord : coords) {
					ret = ret.add(BigInteger.valueOf(Math.abs(coord[0] - arr[0])));
					ret = ret.add(BigInteger.valueOf(Math.abs(coord[1] - arr[1])));
				}
				coords.add(arr);
			}
		System.out.println(ret);
		scan.close();	
	}
}
