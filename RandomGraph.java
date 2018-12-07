package application;

import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RandomGraph {

	private int edges;
	private int vertices;
	private static int FileNumber;
	
	public RandomGraph(int vertices,int edges){
		this.edges = edges;
		this.vertices = vertices;
	}
	
	public RandomGraph(){
		Random rnd = new Random();
		vertices = rnd.nextInt(30);
		edges = rnd.nextInt((int)(vertices*(vertices-1)/2));
	}
	
	public int[][] createMatrix(){
		int[][] Matrix = new int[vertices][vertices];
		
		Random rnd = new Random();
		
		if (edges <= (vertices*(vertices-1)/2)){
			for (int i=0; i<edges; i++){
				int x = rnd.nextInt(vertices);
				int y = rnd.nextInt(vertices);
				
				while (Matrix[x][y] == 1 || x == y) {
					x = rnd.nextInt(vertices);
					y = rnd.nextInt(vertices);
				}
				Matrix[x][y] = 1;
				Matrix[y][x] = 1;
			}
		}
		else {
			for (int i=0; i<vertices; i++){
				for (int j=0; j<vertices; j++) {
					if (i != j) Matrix[i][j] = 1;
				}
			}
		}
	//	doc(Matrix);
		return Matrix;
	}
	
	public int getEdges() {
		return edges;
	}
	public int getVertices() {
		return vertices;
	}
	
	public void doc(int[][] m){
		FileNumber++;
		File doc = new File("random"+FileNumber);
		try{
			FileWriter fw = new FileWriter(doc,true);
			fw.write("VERTICES = "+vertices);
			fw.write(System.lineSeparator());
			fw.write("EDGES = "+edges);
			fw.write(System.lineSeparator());
			for (int k=0; k<vertices; k++){
				for (int l=k+1; l<vertices; l++){
					if (m[k][l] ==1){
						fw.write((k+1)+" "+(l+1));
						fw.write(System.lineSeparator());
					}	
				}
			}
			fw.close();
		} catch (IOException ex){
            System.err.println("Couldn't log this");
        }

	}
}