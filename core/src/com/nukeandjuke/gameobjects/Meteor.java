package com.nukeandjuke.gameobjects;

public class Meteor{
	private float x, y, beginX, beginY;
	private float width;
	public Meteor(float x, float y, float w) {
		beginX = x;
		beginY = y - w;
		this.x = x;
		this.y = beginY;
		width = w;
		// TODO Auto-generated constructor stub
	}
	
	public void updateMovement(float delta){
		x = x - delta;
		y = y + delta;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public float getWidth(){
		return this.width;
	}
	
	public void reset(){
		this.x = beginX;
		this.y = beginY;
	}
}
