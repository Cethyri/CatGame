package edu.neumont.csc150.finalProject.Stage;

import edu.neumont.csc150.finalProject.Main.MainFrame;

public class stages {
	
	public static boolean[][] testStage() {
		boolean[][] stage = new boolean[MainFrame.CONTENT_WIDTH_IN_TILES][MainFrame.CONTENT_HEIGHT_IN_TILES];
		
		for (int x = 0; x < stage.length; x++) {
			for (int y = 0; y < stage[x].length; y++) {
				stage[x][y] = false;
			}
		}
		
		for (int x = 0; x < stage.length; x++) {
			stage[x][stage[x].length - 1] = true;
		}
		
		stage[5][stage[5].length - 4] = true;
		stage[6][stage[6].length - 4] = true;
		
		stage[9][stage[9].length - 6] = true;
		stage[10][stage[10].length - 6] = true;
		
		stage[13][stage[13].length - 8] = true;
		stage[14][stage[14].length - 8] = true;
		
		stage[17][stage[17].length - 6] = true;
		stage[18][stage[18].length - 6] = true;
		
		stage[21][stage[21].length - 4] = true;
		stage[22][stage[22].length - 4] = true;
		
		return stage;
	}
}
