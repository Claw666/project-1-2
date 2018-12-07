package application;

public class LowerBound {

	private int vertices;
	
	public LowerBound(int vertices) {
		this.vertices = vertices;
	}
	
	public int lowerbound(int[] degree, int [][] adjacencyMatrix) {
		//assign the first degree to compare
		int vdegree = degree[0];
		// the vertice which we will use to compute the lower bound
		int lbVertice = 0; 
		int lb = 0;
    
    //we start searching for the highest degree
		for(int i = 1; i < vdegree; i++) {
			if(degree[i] > vdegree) {
				lbVertice = i;
				vdegree = degree[i];
			}
		}

    //we put all the adjacent vertices from the one with the highest degree in an array
		int[] adjacentvertices = new int[vdegree];
		int place = 0;
		for(int j = 0; j < vertices; j++) {
			if(adjacencyMatrix[lbVertice][j] == 1) {
			adjacentvertices[place] = j;
			place++;
			}
		}
    
    //we try to search how many of those are connected to all the others (of the same array)
		for(int p = 0; p < vdegree; p++) {
			int v = adjacentvertices[p];
			int value = 0;
			for(int c = 0; c < vdegree; c++) {
				if(adjacencyMatrix[v][c] == 1) {
					value++;
				}
			} 
			// if the amount of 1's is equal to the amount of vertices we want to check
			if(value == vdegree-1) 
				lb++;
		}
		// if our lower bound returns as zero, we change it to 1 (it cant be 0...)
		if(lb == 0) {
			lb++;
		} 
		return lb;
	}
}