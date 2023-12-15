import java.util.*;
import java.io.*;
public class day1 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String line;
		int ret = 0;
		while((line = scan.readLine()) != null) {
			int left = 0, right = 0;
			for(int i = 0; i < line.length(); i++)
				if(Character.isDigit(line.charAt(i))) {
					left = line.charAt(i) - '0';
					break;
				}
			for(int i = line.length() - 1; i > -1; i--)
				if(Character.isDigit(line.charAt(i))) {
					right = line.charAt(i) - '0';
					break;
				}
			ret += (left * 10) + right;
		}
		System.out.println(ret);
		scan.close();
		
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String s;
		int ret = 0;
		String[] checks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		while((s = scan.readLine()) != null) {
			int[][] inds = new int[18][2];
			
			for(int i = 0; i < 18; i++) {
				inds[i][0] = s.indexOf(checks[i]);
				inds[i][1] = s.lastIndexOf(checks[i]);
			}
			int min = Integer.MAX_VALUE, max = -1;
			int minInd = -1, maxInd = -1;
			for(int i = 0; i < 18; i++) {
				if(inds[i][0] > -1 && inds[i][0] < min) {
					min = inds[i][0];
					minInd = i;
				}
				if(inds[i][1] > -1 && inds[i][1] > max) {
					max = inds[i][1];
					maxInd = i;
				}
			}
			minInd = (minInd > 8) ? (minInd - 8) : (minInd + 1);
			maxInd = (maxInd > 8) ? (maxInd - 8) : (maxInd + 1);
			ret += minInd * 10 + maxInd;
		}
		System.out.println(ret);
		scan.close();
	}
}

