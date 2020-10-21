import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NBA {
    public static void main(String[] args) throws Exception{

        Scanner scan  = new Scanner(new File("shots_data.csv"));

        scan.nextLine();

        List<Node> wholeNode = new ArrayList<>();

        while(scan.hasNext()){
            String str = scan.nextLine();
            String[] data = str.split(",");
            double xCord = Double.parseDouble(data[0]);
            double yCord = Double.parseDouble(data[1]);
            int num = Integer.parseInt(data[2]);

            Node node = new Node(xCord, yCord, num);

            wholeNode.add(node);
        }

        solution(wholeNode);

        scan.close();
    }

    public static void solution(List<Node> wholeNode){

        List<Node> twoPoint = new ArrayList<>();
        List<Node> threePoint = new ArrayList<>();

        for(Node node : wholeNode) {
            if (checkThreePoint(node)) {
                threePoint.add(node);
            } else {
                twoPoint.add(node);
            }
        }

        int totalTwoPoint = twoPoint.size();
        int totalThreePoint = threePoint.size();
        int twoPointScored = 0;
        int threePointScored = 0;

        for(Node n : twoPoint){

            if(n.number == 1) twoPointScored++;

        }

        for(Node n : threePoint){
            if(n.number == 1) threePointScored++;
        }

        double twoPointPercentage = twoPointScored / (totalTwoPoint * 1.0);
        double threePointPercentage = threePointScored / (totalThreePoint * 1.0);

        System.out.println("2PT Percentage is:" + twoPointPercentage +
                " and 3PT Percentage is: " + threePointPercentage);


    }

    public static boolean checkThreePoint(Node node){

        if(node.y > 23.75) return true;
        if(Math.abs(node.x) > 22.0) return true;

        double sqResult = node.x * node.x + node.y * node.y;
        double dis = Math.sqrt(sqResult);

        if(dis <= 23.75) return false;

        return true;
    }

    static class Node{

        double x;
        double y;
        int number;

        public Node(){}
        public Node(double x, double y, int number){

            this.x = x;
            this.y = y;
            this.number = number;
        }
    }
}
