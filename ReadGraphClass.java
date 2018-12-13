package application;
import java.io.*;

class ColEdge2 {
  int u;
  int v;
}

public class ReadGraphClass {

  public final static boolean DEBUG = true;

  public final static String COMMENT = "//";

  public static String args;
  public static int vertices;
  public static int edges;

  public ReadGraphClass(String file) {
    args = file;
  }

  public int getVertices() {
    return vertices;
  }
  public int getEdges() {
    return edges;
  }

  public static int[][] ReadFile() {

    if( args.length() < 1 ) {
      System.out.println("Error! No filename specified.");
      System.exit(0);
    }

    String inputfile = args;

    boolean seen[] = null;

    // n is the number of vertices in the graph
    int n = -1;

    // m is the number of edges in the graph
    int m = -1;

    // e will contain the edges of the graph
    ColEdge2 e[] = null;

    try {

      FileReader fr = new FileReader(inputfile);
      BufferedReader br = new BufferedReader(fr);

      String record = new String();

      // THe first few lines of the file are allowed to be comments, staring with a // symbol.
      // These comments are only allowed at the top of the file.

      // -----------------------------------------
      while ((record = br.readLine()) != null) {
        if( record.startsWith("//") ) {
          continue;
        }
        break; // Saw a line that did not start with a comment -- time to start reading the data in!
      }

      if( record.startsWith("VERTICES = ") ) {
        n = Integer.parseInt( record.substring(11) );         
        //if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
        vertices=n;
      }

      seen = new boolean[n+1];  

      record = br.readLine();

      if( record.startsWith("EDGES = ") ) {
        m = Integer.parseInt( record.substring(8) );          
        //if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
        edges=m;
      }

      e = new ColEdge2[m];  

      for( int d=0; d<m; d++) {
        //if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
        record = br.readLine();
        String data[] = record.split(" ");
        if( data.length != 2 ) {
          System.out.println("Error! Malformed edge line: " + record);
          System.exit(0);
        }
        e[d] = new ColEdge2();

        e[d].u = Integer.parseInt(data[0]);
        e[d].v = Integer.parseInt(data[1]);

        seen[ e[d].u ] = true;
        seen[ e[d].v ] = true;

        //if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
      }
      br.close();     
    }
    catch (IOException ex) { 
      // catch possible io errors from readLine()
      System.out.println("Error! Problem reading file " + inputfile);
      System.exit(0);
    }

    for( int x=1; x<=n; x++ ) {
      if( seen[x] == false ) {
        if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
      }
    }
    /*
    * At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
    * e[1] will be the second edge...
    * (and so on)
    * e[m-1] will be the last edge
    * 
    * there will be n vertices in the graph, numbered 1 to ns 
    */

    int[] connect = new int[n];

    for (int i = 0; i < m; i++) {
      connect[e[i].u-1]++;
      connect[e[i].v-1]++;
    }

    int[][] graph = new int[n][n];
    
    fillMatrix(graph,e,n,m);

    return graph;
  }

  private static void fillMatrix(int[][] matrix, ColEdge2 e[], int n, int m) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n ;j++) {
        matrix[e[i].u-1][e[i].v-1] = 1;
        matrix[e[i].v-1][e[i].u-1] = 1;
      }
    }
  }
}