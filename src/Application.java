import java.io.*;
import java.util.*;

public class Application {

    public static void main(String argus[]) {

        try {
            //Get data from input
            FileReader fileReader = new FileReader("5.in");
            Scanner scanner = new Scanner(fileReader);
            int n = scanner.nextInt();
            Node[] nodes = new Node[n * 2];
            //Write entry into node in nodes array
            for (int i = 0; i < n; i++) {
                nodes[2 * i] = new Node(scanner.nextInt(), i);
                nodes[2 * i + 1] = new Node(scanner.nextInt(), i);
            }

            //Sort nodes array by hour
            Arrays.sort(nodes);
//            for (Node nn : nodes) {
//                System.out.println("index = " + nn.index+",time = "+nn.hour);
//            }

            int[] hourSolo = new int[n];
            int hourCovered = 0;
            int hourPrev = 0;
            LinkedList<Node> indexPool = new LinkedList<>();

            for (int j = 0; j < nodes.length; j++) {
                if (indexPool.size()==1) {
                    //System.out.println("indexPool getFirst = " + indexPool.getFirst());
                    hourSolo[indexPool.getFirst().index] += nodes[j].hour - hourPrev;
                }
                if (!indexPool.isEmpty()) {
                    hourCovered += nodes[j].hour - hourPrev;
                }
                if (indexPool.contains(nodes[j])){
                    indexPool.remove(nodes[j]);
                }else {
                    indexPool.add(nodes[j]);
                }
                hourPrev = nodes[j].hour;
                Collections.sort(indexPool);
                //System.out.println("indexPool sort = " + indexPool);
            }

            int result = 0;
            for (int entry : hourSolo) {
                result = Math.max(result, hourCovered - entry);
            }
            System.out.println(result);
            //Write answer to output
            FileWriter fileWriter = new FileWriter("5.out");
            fileWriter.write(String.valueOf(result));
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

        @Override
        public boolean equals(Object o) {
            return this.index == ((Node)o).index;
        }

    }
}
