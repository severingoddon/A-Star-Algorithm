import java.util.Locale;


/**
 * This class holds the the graph as an 2D-Array with strings. It has a method that generates an
 * adjacency matrix out of the 2D string array. This saves a lot of time.
 *
 * The graph that is currently in use is the "basicGrid" graph. The graphs can be changed if variable names are changed
 *
 * @author Severin Goddon
 */
public class GraphSource {
    private int[][] adjacencyMatrix;
    private int[][] generatedAdjacencyMatrix;
    private String[][] basicGrid;
    private final int startnode = 108;
    private final int endnode = 13;

    public GraphSource(){

        //-------------------> this one is currentyl in use!
        //startnode is 108 and endnode is 13 ==> graph in /resources/bigGraph
        basicGrid= new String[][]{
                {"0","1","2","3","4","5","6","7","8","9"},
                {"10","11","12","x","13","14","15","16","17","18"},
                {"19","20","21","x","22","23","24","25","26","27"},
                {"28","29","30","x","x","x","x","x","x","31"},
                {"32","33","34","35","36","37","x","38","39","40"},
                {"41","42","43","44","45","46","x","x","47","48"},
                {"49","50","51","52","53","54","55","56","57","58"},
                {"59","60","61","62","63","64","65","66","67","68"},
                {"x","x","x","x","x","69","70","71","72","73"},
                {"74","75","76","77","78","79","x","x","x","x"},
                {"80","81","82","83","84","85","x","86","87","88"},
                {"89","90","x","x","x","x","x","91","92","93"},
                {"94","95","x","96","97","98","99","100","101","102"},
                {"103","104","x","105","106","107","x","108","109","110"},
                {"111","112","113","114","115","116","x","117","118","119"},
                {"120","121","122","123","124","125","126","127","128","129"},
        };

        //startnode is 35 and endnode is 9 ==> graph in /resources/graph1
        String[][] basicGrid2 = new String[][]{
                {"0","1","2","3","4","5","6","7"},
                {"8","9","x","10","x","x","11","12"},
                {"13","14","x","15","16","x","17","18"},
                {"x","x","x","x","19","20","21","22"},
                {"23","24","25","x","26","27","28","29"},
                {"30","31","32","x","x","x","x","33"},
                {"34","35","36","37","38","39","40","41"},
                {"42","43","44","45","46","47","48","49"},

        };

        //startnode is 25 and endnode is 9 ==> graph in /resources/graph1
        String [][] basicGrid3 = new String[][]{
                {"0",  "1",  "2",  "3",  "4",  "5",  "6"},
                {"7",  "x",  "8",  "9",  "10", "11", "12"},
                {"13", "x",  "x",  "x",  "x",  "x",  "14"},
                {"15", "16", "17", "18", "19", "x",  "20"},
                {"21", "22", "23", "24", "25", "26", "27"},
        };


        generatedAdjacencyMatrix = createAdjacencyMatrix();
    }


    /*
    This method generates the adjacency matrix out of the basicGrid.
     */
    public int[][] createAdjacencyMatrix(){
        int nodeCount = 0;
        for(int i = 0; i<basicGrid.length; i++){
            for(int j = 0; j<basicGrid[1].length; j++){
                if(!(basicGrid[i][j].equals("x"))) nodeCount++;
            }
        }

        int[][]adjacencyMatrix = new int[nodeCount][nodeCount];
        for(int i = 0; i<adjacencyMatrix.length;i++){
            for(int j = 0; j<adjacencyMatrix.length; j++){
                adjacencyMatrix[i][j]=0;
            }
        }

        //loop trough basicGrid and create the adjacency matrix
        //this is much boilerplate code but I don't care
        for(int i = 0; i<basicGrid.length; i++){
            for(int j = 0; j<basicGrid[0].length; j++){
                if(!(basicGrid[i][j].equals("x"))){

                    int nodeIndex = Integer.parseInt(basicGrid[i][j]);
                    //case if current node is not a border node
                    if(i>0 && j>0 && i<basicGrid.length-1 && j<basicGrid[0].length-1){
                        String upperNode = basicGrid[i-1][j];
                        String leftNode = basicGrid[i][j-1];
                        String rightNode = basicGrid[i][j+1];
                        String lowerNode = basicGrid[i+1][j];
                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if the current node is on top line but not in the left or right edge
                    if(i==0 && j>0 && j<basicGrid[0].length-1){
                        String leftNode = basicGrid[i][j-1];
                        String rightNode = basicGrid[i][j+1];
                        String lowerNode = basicGrid[i+1][j];

                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if the current node is on bottom line but not in the left or right edge
                    if(i==basicGrid.length-1 && j>0 && j<basicGrid[0].length-1){
                        String upperNode = basicGrid[i-1][j];
                        String leftNode = basicGrid[i][j-1];
                        String rightNode = basicGrid[i][j+1];

                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                    }
                    //case if the current node is on left vertical line but not in an edge
                    if(i>0 && j==0 && i<basicGrid.length-1){
                        String upperNode = basicGrid[i-1][j];
                        String rightNode = basicGrid[i][j+1];
                        String lowerNode = basicGrid[i+1][j];
                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if the current node is on the right vertical line but not in an edge
                    if(i>0 && i<basicGrid.length-1 && j==basicGrid[0].length-1){
                        String upperNode = basicGrid[i-1][j];
                        String leftNode = basicGrid[i][j-1];
                        String lowerNode = basicGrid[i+1][j];
                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if node is upper left edge node
                    if(i==0 && j == 0){
                        String rightNode = basicGrid[i][j+1];
                        String lowerNode = basicGrid[i+1][j];
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if node is upper right node
                    if(i==0 && j==basicGrid[0].length-1){
                        String leftNode = basicGrid[i][j-1];
                        String lowerNode = basicGrid[i+1][j];
                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                        if(!(lowerNode.equals("x"))){
                            int lowerNodeIndex = Integer.parseInt(lowerNode);
                            adjacencyMatrix[nodeIndex][lowerNodeIndex] = 1;
                        }
                    }
                    //case if node is lower left node
                    if(i==basicGrid.length-1 && j==0){
                        String upperNode = basicGrid[i-1][j];
                        String rightNode = basicGrid[i][j+1];
                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(rightNode.equals("x"))){
                            int rightNodeIndex = Integer.parseInt(rightNode);
                            adjacencyMatrix[nodeIndex][rightNodeIndex] = 1;
                        }
                    }
                    //case if node is lower right node
                    //case if current node is not a border node
                    if(i==basicGrid.length-1 && j == basicGrid[0].length-1){
                        String upperNode = basicGrid[i-1][j];
                        String leftNode = basicGrid[i][j-1];

                        if(!(upperNode.equals("x"))){
                            int upperNodeIndex = Integer.parseInt(upperNode);
                            adjacencyMatrix[nodeIndex][upperNodeIndex] = 1;
                        }
                        if(!(leftNode.equals("x"))){
                            int leftNodeIndex = Integer.parseInt(leftNode);
                            adjacencyMatrix[nodeIndex][leftNodeIndex] = 1;
                        }
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    public int[][] getAdjacencyMatrix(){
        return this.adjacencyMatrix;
    }
    public String[][] getBasicGrid(){
        return basicGrid;
    }
    public int getStartnode(){
        return startnode;
    }
    public int getEndnode(){
        return endnode;
    }

    public int[][] getGeneratedAdjacencyMatrix(){
        return generatedAdjacencyMatrix;
    }

}
