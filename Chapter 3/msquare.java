/*
ID: ***
LANG: JAVA
PROB: msquare
*/
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
 
public class msquare {
    private final static int N = 8;
    public static void main(String[] args) throws IOException {
    	long start = System.currentTimeMillis();
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("msquare.in"));
        @SuppressWarnings("resource")
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String initial = "12345678";
        String target = "";
        for(int i = 0; i < N; i++) {
            target += st.nextToken();
        }
        Queue<Point> q = new LinkedList<Point>();
        Set<String> seen = new HashSet<String>();
        
        seen.add(initial);
        q.add(new Point(initial, "", 0));
        while(!q.isEmpty()) {
            Point p = q.poll();
            //printing
            if(p.equals(target)) {
                out.println(p.numSteps);
                char[] c = p.sequence.toCharArray();
                int i = 0;
                while(i < c.length) {
                    out.print(c[i]);
                    i++;
                    if(i % 60 == 0 && i != 0)
                        out.println();
                }
                if(!(i % 60 == 0) || i == 0)
                    out.println();
                out.close();
                return;
            }
            String temp = ""; //StringBuilder is actually much much slower in this case :\
            //add in step a
            for(int i = 0; i < N; i++)
                temp += p.state.charAt(N - 1 - i);
//            Collections.sort(seen);
            if(!seen.contains(temp)) { //not in the list
                q.add(new Point(temp, p.sequence + "A", p.numSteps + 1));
                seen.add(temp);
            }
            //step b : first row
            temp = "";
            for(int i = 0; i < 4; i++) {
                if(i == 0)
                    temp += p.state.charAt(3);
                else
                    temp += p.state.charAt(i - 1);
            }
            //step b : second row
            for(int i = 4; i < N; i++) {
                if(i == N - 1)
                    temp += p.state.charAt(4);
                else
                    temp += p.state.charAt(i + 1);
            }
//            Collections.sort(seen);
            if(!seen.contains(temp)) { //not in the list
                q.add(new Point(temp, p.sequence + "B", p.numSteps + 1));
                seen.add(temp);
            }
            //add in step c
            temp = "";
            for(int i = 0; i < N; i++) {
            	if(i == 1)
            		temp += p.state.charAt(6);
            	else if(i == 2)
            		temp += p.state.charAt(1);
            	else if(i == 5)
            		temp += p.state.charAt(2);
            	else if(i == 6)
            		temp += p.state.charAt(5);
            	else
            		temp += p.state.charAt(i);
            }
//            temp[2] = p.state[1];
//            temp[5] = p.state[2];
//            temp[6] = p.state[5];
//            temp[1] = p.state[6];
//            Collections.sort(seen);
            if(!seen.contains(temp)) { //not in the list
                q.add(new Point(temp, p.sequence + "C", p.numSteps + 1));
                seen.add(temp);
            }
        }
    }
    static class Point {
        String state;
        int numSteps;
        String sequence;
        public Point(String state, String sequence, int numSteps) {
            this.numSteps = numSteps;
            this.state = state;
            this.sequence = sequence;
        }
        public boolean equals(String str) {
            return state.equals(str);
        }
        public String toString() {
            return state;
        }
    }
}
