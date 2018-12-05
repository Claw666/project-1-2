package application;

public class Backtracking { 

  public int color[]; 
  private int vertices;

  //Construction method with an argument vertices
  public Backtracking(int vertices) {
    this.vertices = vertices;

  }
  
  // this method checks if color assignment is valid for the vertix
  public boolean colorSafe(int v, int graph[][], int color[], int c) { 
    for (int i = 0; i < vertices; i++) {
      if (graph[v][i] == 1 && c == color[i]){
        return false;
      }     
    }
    return true;
  }

  // A recursive function to solve m coloring problem
  public boolean graphColoringHelper(int graph[][], int m, int color[], int v) { 
    // base case: If all vertices are assigned a color then return true
    if (v == vertices) {
      return true; 
    } else {
      // Consider this vertex v and try different colors
      for (int c = 1; c <= m; c++) { 
        // Check if assignment of color c to v is fine
        if (colorSafe(v, graph, color, c)) { 
          color[v] = c; 

          // recursive part where the code assign colors to the rest of the vertices
          if (graphColoringHelper(graph, m, color, v + 1)) {
            return true; 
          }
          // If c color does not lead to solution, change it back to 0
          color[v] = 0; 
        } 
      } 

      // If no color can be assigned to this vertex then return false
      return false; 
    }
  }

  //if it cannot assign the m colors it should return false, otherwise return true
  public boolean graphColoring(int graph[][], int m) { 

    color = new int[vertices]; 

    //initially set all color values to 0
    for (int i = 0; i < vertices; i++)  {
      color[i] = 0; 
    }

    //print if solution exist or not

    if (!graphColoringHelper(graph, m, color, 0)) { 
      //System.out.println("Solution does not exist"); 
      return false; 
    } 

    //System.out.println("Solution does exist");
    return true; 
  }
  
  public int[] getColor() {
	  return color;
  }
}