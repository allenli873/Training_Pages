/*
ID: ***
LANG: JAVA
PROB: contact
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class contact {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		String seq = br.readLine();
		while(seq != null) {
			sb.append(seq);
			seq = br.readLine();
//			System.out.println("hi");
		}
		String sequence = sb.toString();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = a; i <= b; i++) {
			for(int j = 0; j < sequence.length() - i + 1; j++) {
				String substring = sequence.substring(j, j + i);
				if(map.containsKey(substring))
					map.replace(substring, map.get(substring) + 1);
				else
					map.put(substring, 1);
			}
		}
		
		
		Set set = map.entrySet();
		List<Entry<String, Integer>> memes = new ArrayList<Entry<String, Integer>>();
		memes.addAll(set);
		List<Pair> moreMemes = new ArrayList<Pair>();
//		System.out.println(memes);
		for(int i = 0; i < set.size(); i++) {
			Entry<String, Integer> e = memes.get(i);
			moreMemes.add(new Pair(e.getKey(), e.getValue()));
		}
		
//		for (Entry<String, Integer> e : map.entrySet())
//			moreMemes.add(new Pair(e.getKey(), e.getValue()));
		
		Collections.sort(moreMemes);
		
		long currNumber = moreMemes.get(0).y;
		out.println(currNumber);
		int rows = 1;
		boolean flag = false;
		int numElements = 0;
		int num = 0;
		boolean noSpace = false;
		for(int i = 0; i < moreMemes.size(); i++) {
			noSpace = false;
			if(num % 6 == 5)
				noSpace = true;
			numElements = 0;
			flag = false;
			
			if(currNumber == moreMemes.get(i).y) {
				if(i + 1 != moreMemes.size() && currNumber == moreMemes.get(i + 1).y && !noSpace) {
					out.print(moreMemes.get(i).x + " ");
					num++;
					numElements++;
//					System.out.println(moreMemes.get(i).y + " " + moreMemes.get(i + 1).y);
				}
				else {
					num++;
					numElements++;
					out.print(moreMemes.get(i).x);
				}
			} else {
				num = 0;
				out.println();
				flag = true;
				currNumber = moreMemes.get(i).y;
				rows++;
				if(rows > n)
					break;
				else {
					out.println(currNumber);
					if(i + 1 != moreMemes.size() && currNumber == moreMemes.get(i + 1).y) {
						numElements++;
						num++;
						out.print(moreMemes.get(i).x + " ");
//						System.out.println(moreMemes.get(i).y + " " + moreMemes.get(i + 1).y);
					}
					else {
						numElements++;
						num++;
						out.print(moreMemes.get(i).x);
					}
				}
				
			}
			if(rows > n)
				break;
			if(num % 6 == 0 && num != 0 && i + 1 != moreMemes.size() && currNumber == moreMemes.get(i + 1).y) {
				out.println();
			}
		}
//		System.out.println(numElements);
//		System.out.println(flag);
		if(!flag || numElements == 1) {
//			System.out.println("hi");
			out.println();
		}
		out.close();
		System.exit(0);
	}
	static class Pair implements Comparable<Pair> {
		String x;
		long y;
		public Pair(String x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if(Long.compare(o.y, y) != 0)
				return Long.compare(o.y, y);
			else if(Long.compare(x.length(), o.x.length()) != 0)
				return Long.compare(x.length(), o.x.length());
			else
				return Long.compare(Long.parseLong(x), Long.parseLong(o.x));
//			return 0;
		}
		public String toString() {
			return x + "," + y;
		}
		
	}
}
