/*
ID: ***
LANG: JAVA
PROB: spin
*/
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
public class spin {
    final static int ANG = 360;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("spin.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
        List<Wedge> wedges = new ArrayList<Wedge>();
        int[] divs = new int[5 + 1];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int speed = Integer.parseInt(st.nextToken());
            int wed = Integer.parseInt(st.nextToken());
            divs[i + 1] = divs[i] + wed;
            for(int j = 0; j < wed; j++) {
                wedges.add(new Wedge(speed, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
        }
        int i = 0;
        boolean flag = true;
        while(i <= 360) {
            for(int j = 0; j < 360; j++) {
                int numPass = 0;
                for(int k = 0; k < wedges.size(); k++) {
                	if(wedges.get(k).light(j)) {
                		numPass++;
                	}
                }
                if(numPass == 5) {
                	out.println(i);
                	out.close();
                	return;
                }
            }
            i++;
            for(Wedge w: wedges) {
                w.turn();
            }
        }
        out.println("none");
        out.close();
       
    }
    static class Wedge {
        int speed, start, end;
        public Wedge(int speed, int start, int end) {
            this.speed = speed;
            this.start = start;
            this.end = (start + end) % ANG; //end angle location
        }
       
        public void turn() {
            start = (speed + start) % ANG;
            end = (speed + end) % ANG;
        }
       
        public boolean light(int num) { //returns true if given angle can pass through, false otherwise
            if(end < start) { //this means that it loops around
                return (end >= num || start <= num);
            } else {
                return (start <= num && end >= num); //between start & end
            }
        }
       
        public String toString() {
            return speed + "," + start + "," + end;
        }
    }
}
