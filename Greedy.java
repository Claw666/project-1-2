package application;

import java.util.*;

public class Greedy {
	
	int colours[];
	private int vertices;
	
	//Construction method with argument vertices
	public Greedy(int vertices) {
		this.vertices = vertices;
	}
	
	public static void colouring(int[] colour, int[][] graph) {
		// starts with vertice 2 and looks at them by their numoration
		for (int i = 1; i < graph.length; i++) {
			int j = 0;
			boolean test = true;
			while (test && j < i) {
				//looks for the first vertice with which it shares no edge
				if (graph[i][j] == 0){
					int relative = colour[j];
					boolean ok = true;
					int l = 0;
					//checks if the colour of the vertice we found (relative) is already taken
					// by other vertices and if the vertice in question has an edge with those
					while (ok && l < i) {
						if (colour[l] == relative)
							if (graph[i][l] == 1)
								ok = false;
							l++;
							}
					if (ok){
						colour[i] = relative;
						test = false;
					}
				}
				j++;
			}
		}
	}       
	
	//Get the number of different colours int the colourArray
	public static int Values(int[] colourArray){
		int result = 0;
		// diff is a list with all the different colours
		ArrayList<Integer> diff = new ArrayList<>();

		for(int i = 0; i < colourArray.length; i++){
			if(!diff.contains(colourArray[i])){
				diff.add(colourArray[i]);
			}
		}
        result = diff.size();
        return result;
    }
    
    public int Upper(int graph[][]){
    	colours = new int[vertices];
    	
    	//initiallies the colours
    	for (int i = 0; i < colours.length; i++)
				colours[i] = i+1;
    	
    	colouring(colours,graph);
    	return Values(colours);
    }
}