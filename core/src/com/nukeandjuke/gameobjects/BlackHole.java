package com.nukeandjuke.gameobjects;

public class BlackHole {
	private int pos_x, pos_y, rad;
	
	public BlackHole(int x, int y, int radius){
		pos_x = x;
		pos_y = y;
		rad = radius;
	}
	
	public int getX(){
		return pos_x;
	}
	
	public int getY(){
		return pos_y;
	}
	
	public int getRad(){
		return rad;
	}

	public void setLoc(int x, int y){
		pos_x = x;
		pos_y = y;
	}
}
