import java.io.*;
import java.util.*;

public class Application {

    public static void main(String argus[]) {

        try {
            //Get data from input
            FileReader fileReader = new FileReader("2.in");
            Scanner scanner = new Scanner(fileReader);
            int n = scanner.nextInt();
            Node[] nodes = new Node[n*2];
            //Write entry into node in nodes array
            for (int i = 0; i < n; i++) {
                nodes[2 * i] = new Node(scanner.nextInt(), i);
                nodes[2 * i + 1] = new Node(scanner.nextInt(), i);
            }

            //Sort nodes array by hour
            Arrays.sort(nodes);

            int[] table= new int[n];
            for (int j=0; j<n; j++){
                table[j]= nodes[2*j+1].hour-nodes[2*j].hour;
            }
            int hourCovered1 = 0;
            for (int k=0; k<nodes.length; k++){
                while(k<nodes.length) {
                    hourCovered1 += nodes[k + 1].hour - nodes[k].hour;
                }
            }

            int result1 = 0;
            System.out.println(hourCovered1);
            for (int entry: table) {
                result1 = Math.max(result1, hourCovered1 - entry);
            }
            System.out.println(result1);
            //Write answer to output
            FileWriter fileWriter = new FileWriter("1.out");
            fileWriter.write(String.valueOf(result1));
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Node implements Comparable<Node> {

        public int hour, index;

        public Node(int hour, int index) {
            this.hour = hour;
            this.index = index;
        }

        public int compareTo(Node node) {
            return hour - node.hour;
        }

    }
}
