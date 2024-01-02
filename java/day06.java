import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;
public class day06 {
	public static void main(String[] args) throws IOException {
		part1();
		part2();
	}
	public static void part1() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String times = scan.readLine();
		Pattern timePat = Pattern.compile("Time: +(\\d+) +(\\d+) +(\\d+) +(\\d+)");
		Matcher timeMat = timePat.matcher(times);
		if(!timeMat.matches()) {
			System.out.println("times match failed");
			System.exit(1);
		}
		int[] timesArr = new int[4];
		for(int i = 0; i < 4; i++)
			timesArr[i] = Integer.parseInt(timeMat.group(i + 1));
		
		String dists = scan.readLine();
		Pattern distPat = Pattern.compile("Distance: +(\\d+) +(\\d+) +(\\d+) +(\\d+)");
		Matcher distMat = distPat.matcher(dists);
		if(!distMat.matches()) {
			System.out.println("times match failed");
			System.exit(1);
		}
		int[] distsArr = new int[4];
		for(int i = 0; i < 4; i++)
			distsArr[i] = Integer.parseInt(distMat.group(i + 1));
		
		int[] ret = new int[4];
		for(int i = 0; i < 4; i++) {
			ret[i] = 0;
			int mid = timesArr[i] / 2;
			if(timesArr[i] % 2 == 0) {
				if(mid * mid <= distsArr[i])
					continue;
				mid -= 1;
				ret[i] ++;
			}
			while(mid * (timesArr[i] - mid) > distsArr[i]) {
				ret[i] += 2;
				mid--;
			}
		}
		int ans = 1;
		for(int i : ret)
			ans *= i;
		System.out.println(ans);
		scan.close();
	}
	public static void part2() throws IOException {
		BufferedReader scan = new BufferedReader(new FileReader("input.txt"));
		String times = scan.readLine();
		Pattern timePat = Pattern.compile("Time: +(\\d+) +(\\d+) +(\\d+) +(\\d+)");
		Matcher timeMat = timePat.matcher(times);
		if(!timeMat.matches()) {
			System.out.println("times match failed");
			System.exit(1);
		}
		String timeStr = "";
		for(int i = 1; i <= 4; i++)
			timeStr += timeMat.group(i);
		
		String dists = scan.readLine();
		Pattern distPat = Pattern.compile("Distance: +(\\d+) +(\\d+) +(\\d+) +(\\d+)");
		Matcher distMat = distPat.matcher(dists);
		if(!distMat.matches()) {
			System.out.println("times match failed");
			System.exit(1);
		}
		String distStr = "";
		for(int i = 1; i <= 4; i++)
			distStr += distMat.group(i);
		
		BigInteger time = new BigInteger(timeStr);
		//int time = Integer.parseInt(timeStr);
		BigInteger dist = new BigInteger(distStr);
		//int dist = Integer.parseInt(distStr);
		
		BigInteger mid = time.divide(new BigInteger("2"));
		//int mid = time / 2;
		int ret = 0;
		if(time.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) {
			if(mid.multiply(mid).compareTo(dist) < 0) {
				System.out.println(0);
				scan.close();
			}
			mid = mid.subtract(BigInteger.ONE);
			ret++;
		}
		while(mid.multiply(time.subtract(mid)).compareTo(dist) > 0) {
			mid = mid.subtract(BigInteger.ONE);
			ret += 2;
		}
		System.out.println(ret);
		scan.close();	
	}
}
