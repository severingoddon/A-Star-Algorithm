import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static MyGraph graph = new MyGraph();
    private static ArrayList<Node> alreadyVisitedNodes = new ArrayList<>();
    private static int startNode;
    private static int endNode;
    private static String[][] basicGrid;
    private static ArrayList<Node> connectedNodes = new ArrayList<>();

    public static void main(String[] args) {
        startNode = 25;
        endNode = 9;
        //adjaszenzmatrix
        int[][] graphArray =
                {   //    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
                         {0,1,0,0,0,0,0,1,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
                         {1,0,1,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
                         {0,1,0,1,0,0,0,0,1,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
                         {0,0,1,0,1,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //3
                         {0,0,0,1,0,1,0,0,0,0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //4
                         {0,0,0,0,1,0,1,0,0,0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
                         {0,0,0,0,0,1,0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //6
                         {1,0,0,0,0,0,0,0,0,0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7
                         {0,0,1,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //8
                         {0,0,0,1,0,0,0,0,1,0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //9
                         {0,0,0,0,1,0,0,0,0,1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //10
                         {0,0,0,0,0,1,0,0,0,0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //11
                         {0,0,0,0,0,0,1,0,0,0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //12
                         {0,0,0,0,0,0,0,1,0,0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //13
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, //14
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //15
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //16
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //17
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, //18
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0}, //19
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //20
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, //21
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, //22
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0}, //23
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0}, //24
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0}, //25
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, //26
                         {0,0,0,0,0,0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0}, //27
                        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
        };

        //this is just the graph represented as a 2D array. I use this to calculate the f cost of each node
        basicGrid = new String[][]{
                {"0", "1", "2", "3", "4", "5", "6"},
                {"7", "x", "8", "9", "10", "11", "12"},
                {"13", "x", "x", "x", "x", "x", "14"},
                {"15", "16", "17", "18", "19", "x", "20"},
                {"21", "22", "23", "24", "25", "26", "27"},
        };

        //add all nodes to the graph and calculate their costs
        for(int i = 0; i< graphArray.length; i++){
            Node node = new Node();
            node.setId(i);
            int gCost = calculateGcost(node);
            int hCost = calculateHcost(node);
            node.setgCost(gCost);
            node.sethCost(hCost);
            node.setfCost(gCost+hCost);
            graph.addNode(node);
        }

        //add all connectons in the graph
        addConnections(graphArray);


        calculateShortestPath();

    }

    /*
    This method adds the connections between the nodes
     */
    public static void addConnections(int[][] graphArray){
        for(int i = 0; i<graphArray.length; i++){
            for(int j = 0; j<graphArray.length; j++){
                if(graphArray[j][i]==1){
                    NodeConnection connection = new NodeConnection();
                    connection.setConnection(graph.findNode(i), graph.findNode(j));
                    graph.addConnection(connection);
                }
            }
        }
    }

    public static void calculateShortestPath(){
        Node startNode = graph.findNode(25);
        Node endNode = graph.findNode(9);
        Node currentNode = startNode;

        //mainloop
        while(!currentNode.equals(endNode)){
            System.out.println("id: "+currentNode.getId());
            System.out.println("gCost: "+currentNode.getgCost());
            System.out.println("hCost: "+currentNode.gethCost());
            System.out.println(" ");
            alreadyVisitedNodes.add(currentNode);
            currentNode = findNextNode(currentNode);
        }
        System.out.println(currentNode.getId());
    }

    public static Node findNextNode(Node currentNode){
        Node nextNode = null;
        while (true) {
            ArrayList<Node> newConnectedNodes;
            newConnectedNodes = graph.getConnectedNodes(currentNode);
            for (Node newNode : newConnectedNodes) {
                if (!connectedNodes.contains(newNode)) {
                    connectedNodes.add(newNode);
                }
            }
            while (true) {
                Random rn = new Random();
                nextNode = connectedNodes.get(rn.nextInt(connectedNodes.size()));
                if (!(nextNode.equals(currentNode))) break;
            }

            for (Node node : connectedNodes) {
                if (node.getfCost() < nextNode.getfCost() && !(alreadyVisitedNodes.contains(node))) {
                    nextNode = node;
                }
            }
            if(!(alreadyVisitedNodes.contains(nextNode)))break;
        }
        return nextNode;
    }


    public static int calculateGcost(Node node){
        String id = Integer.toString(node.getId());
        String idFromStartNode = Integer.toString(startNode);
        int rowOfcurrentNode = 0;
        int columnOfCurrentNode = 0;
        int rowOfStartNode = 0;
        int columnOfStartNode = 0;
        for(int i = 0; i< basicGrid.length; i++){
            for(int j = 0; j< basicGrid[0].length; j++){
                if(basicGrid[i][j].equals(id)){
                    rowOfcurrentNode = i;
                    columnOfCurrentNode = j;
                }
                if(basicGrid[i][j].equals(idFromStartNode)){
                    rowOfStartNode = i;
                    columnOfStartNode = j;
                }
            }
        }
        int differenceBetweenRows = rowOfStartNode-rowOfcurrentNode;
        if(differenceBetweenRows<0) differenceBetweenRows = differenceBetweenRows*-1;
        int differenceBetweenColumns = columnOfStartNode-columnOfCurrentNode;
        if(differenceBetweenColumns<0) differenceBetweenColumns = differenceBetweenColumns*-1;

        return (differenceBetweenColumns+differenceBetweenRows)*10;
    }
    public static int calculateHcost(Node node){
        String id = Integer.toString(node.getId());
        String idFromEndNode = Integer.toString(endNode);
        int rowOfcurrentNode = 0;
        int columnOfCurrentNode = 0;
        int rowOfEndNode = 0;
        int columnOfEndNode = 0;
        for(int i = 0; i< basicGrid.length; i++){
            for(int j = 0; j< basicGrid[0].length; j++){
                if(basicGrid[i][j].equals(id)){
                    rowOfcurrentNode = i;
                    columnOfCurrentNode = j;
                }
                if(basicGrid[i][j].equals(idFromEndNode)){
                    rowOfEndNode = i;
                    columnOfEndNode = j;
                }
            }
        }
        int differenceBetweenRows = rowOfEndNode-rowOfcurrentNode;
        if(differenceBetweenRows<0) differenceBetweenRows = differenceBetweenRows*-1;
        int differenceBetweenColumns = columnOfEndNode-columnOfCurrentNode;
        if(differenceBetweenColumns<0) differenceBetweenColumns = differenceBetweenColumns*-1;

        return (differenceBetweenColumns+differenceBetweenRows)*10;
    }

}
