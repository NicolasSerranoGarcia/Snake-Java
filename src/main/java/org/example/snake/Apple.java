package org.example.snake;

import java.lang.Math;

public class Apple {
    private int x,y;
    private int mapSize;

    public Apple(int size){
        if(size <= 0){
            mapSize = 8;
        } else{
            mapSize = size;
        }

        x = (int) (mapSize / 2) - 1;
        y = (int) (mapSize / 2) - 2;
    }

    public Apple(Apple a){

        mapSize = a.mapSize;

        x = a.x; 

        y = a.y;
    }
    
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void generateNewPos(){
        x = (int) Math.floor(Math.random()*(mapSize));
        y = (int) Math.floor(Math.random()*(mapSize));
    }
}
