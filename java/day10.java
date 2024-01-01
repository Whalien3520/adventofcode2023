import java.io.*;
import java.util.*;
public class day10 {
	public static void main(String[] args) throws IOException {
		//part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String input;
		int x = -1, y = -1;
		while((input = scan.readLine()) != null) {
			list.add(input);
			for(int i = 0; i < input.length(); i++)
				if(input.charAt(i) == 'S') {
					x = list.size() - 1;
					y = i;
				}
		}
		if(x == -1 || y == -1) {
			System.out.println("S was not found.");
			System.exit(1);
		}
		char[][] map = new char[list.size()][];
		for(int i = 0; i < list.size(); i++)
			map[i] = list.get(i).toCharArray();
		int[] arr = moveFromS(map, x, y);
		if(arr == null) {
			System.out.println("no connections were found from S");
			System.exit(1);
		}
		int ret = 1;
		while(map[arr[0]][arr[1]] != 'S') {
			int ind = -1;
			for(int i = 0; i < moves.length && ind == -1; i++)
				if(moves[i] == map[arr[0]][arr[1]])
					ind = i;
			if(ind == -1) {
				System.out.println("current char was not found");
				System.exit(1);
			}
			if(dirs[ind][0] == (3 - arr[2]))
				arr[2] = dirs[ind][1];
			else
				arr[2] = dirs[ind][0];
			//row:
			//-1 if 0
			//+1 if 3
			//0 otherwise
			arr[0] = (arr[2] % 3 != 0) ? arr[0] : (arr[2] == 0) ? arr[0] - 1 : arr[0] + 1;
			//col:
			//+1 if 1
			//-1 if 2
			//0 otherwise
			arr[1] = (arr[2] % 3 == 0) ? arr[1] : (arr[2] == 1) ? arr[1] + 1 : arr[1] - 1;
			ret++;
		}
		System.out.println(ret / 2);
		scan.close();
	}
	static char[] moves = {'|', '-', 'L', 'J', '7', 'F'};
	//NEWS
	static int[][] dirs = {{0, 3}, {1, 2}, {0, 1}, {0, 2}, {2, 3}, {1, 3}};
	//returns new coordinates plus most recent movement
	public static int[] moveFromS(char[][] map, int r, int c) {
		if(r > 0) {
			char check = map[r - 1][c];
			if("|7F".indexOf(check) > -1)
				return new int[] {r - 1, c, 0};
		}
		if(c > 0) {
			char check = map[r][c - 1];
			if("-LF".indexOf(check) > -1)
				return new int[] {r, c - 1, 2};
		}
		if(r < map.length - 1) {
			char check = map[r + 1][c];
			if("|LJ".indexOf(check) > -1)
				return new int[] {r + 1, c, 3};
		}
		if(c < map[0].length - 1) {
			char check = map[r][c + 1];
			if("-J7".indexOf(check) > -1)
				return new int[] {r, c + 1, 1};
		}
		return null;
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		ArrayList<String> list = new ArrayList<>();
		String input;
		int x = -1, y = -1;
		while((input = scan.readLine()) != null) {
			list.add(input);
			for(int i = 0; i < input.length(); i++)
				if(input.charAt(i) == 'S') {
					x = list.size() - 1;
					y = i;
				}
		}
		if(x == -1 || y == -1) {
			System.out.println("S was not found.");
			System.exit(1);
		}
		char[][] map = new char[list.size()][];
		for(int i = 0; i < list.size(); i++)
			map[i] = list.get(i).toCharArray();
		boolean[][] loop = new boolean[map.length][map[0].length];
		int[] arr = moveFromS(map, x, y);
		if(arr == null) {
			System.out.println("no connections were found from S");
			System.exit(1);
		}
		int dir1 = arr[2];
		while(map[arr[0]][arr[1]] != 'S') {
			int ind = -1;
			for(int i = 0; i < moves.length && ind == -1; i++)
				if(moves[i] == map[arr[0]][arr[1]])
					ind = i;
			if(ind == -1) {
				System.out.println("current char was not found");
				System.exit(1);
			}
			loop[arr[0]][arr[1]] = true;
			if(dirs[ind][0] == (3 - arr[2]))
				arr[2] = dirs[ind][1];
			else
				arr[2] = dirs[ind][0];
			arr[0] = (arr[2] % 3 != 0) ? arr[0] : (arr[2] == 0) ? arr[0] - 1 : arr[0] + 1;
			arr[1] = (arr[2] % 3 == 0) ? arr[1] : (arr[2] == 1) ? arr[1] + 1 : arr[1] - 1;
		}
		setS(map, loop, dir1, arr[2], arr[0], arr[1]);
		int ret = 0;
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				if(loop[r][c])
					continue;
				int borders = countBorders(map, loop, r, c);
				if(borders == -1) {
					System.out.println("Direction could not be identified");
					System.exit(1);
				}
				if(borders % 2 == 1)
					ret++;
			}
		}
		//216 too low
		//288 too low
		//362 wrong
		System.out.println(ret);
		scan.close();
	}
	public static void setS(char[][] map, boolean[][] loop, int out, int in, int r, int c) {
		loop[r][c] = true;
		switch(out * 4 + in) {
		//North out, North in
		case 0:
		//South out, South in
		case 15:
			map[r][c] = '|';
			break;
		//East out, East in
		case 5:
		//West out, West in
		case 10:
			map[r][c] = '-';
			break;
		//North out, West in
		case 2:
		//East out, South in
		case 7:
			map[r][c] = 'L';
			break;
		//North out, East in
		case 1:
		//West out, South in
		case 11:
			map[r][c] = 'J';
			break;
		//West out, North in
		case 8:
		//South out, East in
		case 13:
			map[r][c] = '7';
			break;
		//East out, North in
		case 4:
		//South out, West in
		case 14:
			map[r][c] = 'F';
			break;
		default:
			System.out.println("setS failed");
			System.exit(1);
		}
	}
	public static int countBorders(char[][] map, boolean[][] loop, int r, int c) {
		int rDiff = map.length - r, cDiff = map[r].length - c;
		int ret = 0, flag = 0;
		if(r <= rDiff && r <= c && r <= cDiff)
			while(r >= 0) {
				if(loop[r][c]) {
					switch(map[r][c]) {
					case '-':
						ret++;
						break;
					case 'L':
					case 'F':
						//if previous encounter was J/7
						if(flag == -1)
							ret++;
						//from -1 or 1 to 0, from  0 to 1
						flag = (flag + 1) % 2;
						break;
					case 'J':
					case '7':
						if(flag == 1)
							ret++;
						flag = (flag - 1) % 2;
					}
				}
				r--;
			}
		else if(rDiff <= r && rDiff <= c && rDiff <= cDiff)
			while(r < map.length) {
				if(loop[r][c]) {
					switch(map[r][c]) {
					case '-':
						ret++;
						break;
					case 'L':
					case 'F':
						if(flag == -1)
							ret++;
						flag = (flag + 1) % 2;
						break;
					case 'J':
					case '7':
						if(flag == 1)
							ret++;
						flag = (flag - 1) % 2;
					}
				}
				r++;
			}
		else if(c <= cDiff && c <= r && c <= rDiff) {
			while(c >= 0) {
				if(loop[r][c]) {
					switch(map[r][c]) {
					case '|':
						ret++;
						break;
					case 'L':
					case 'J':
						if(flag == -1)
							ret++;
						flag = (flag + 1) % 2;
						break;
					case '7':
					case 'F':
						if(flag == 1)
							ret++;
						flag = (flag - 1) % 2;
					}
				}
				c--;
			}
		}
		else if(cDiff <= c && cDiff <= r && cDiff <= rDiff) {
			while(c < map[r].length) {
				if(loop[r][c]) {
					switch(map[r][c]) {
					case '|':
						ret++;
						break;
					case 'L':
					case 'J':
						if(flag == -1)
							ret++;
						flag = (flag + 1) % 2;
						break;
					case '7':
					case 'F':
						if(flag == 1)
							ret++;
						flag = (flag - 1) % 2;
					}
				}
				c++;
			}
		}
		else
			return -1;
		if(flag != 0) {
			System.out.println("flag did not end at 0");
			System.exit(1);
		}
		/*if(ret % 2 == 1) {
			System.out.println(rr + " " + cc);
			System.out.println(save);
		}*/
		return ret;
	}
}
