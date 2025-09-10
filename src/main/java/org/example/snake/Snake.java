package org.example.snake;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Vector2D> snake;
    private int mapSize;

    public Snake(int size){
        if(size <= 0){
            mapSize = 8;
        } else{
            mapSize = size;
        }

        snake = new ArrayList<Vector2D>();

        //initialize two body parts

        int x = (int) (mapSize / 2) - 1;
        int y = (int) (mapSize / 2) + 1;
        Vector2D newPart = new Vector2D(x, y);
        snake.add(newPart);

        y = (int) (mapSize / 2) + 2;
        newPart = new Vector2D(x, y);
        snake.add(newPart);
    }

    public Snake(Snake s){
        mapSize = s.mapSize;
        snake = new ArrayList<Vector2D>();

        for(int i = 0; i < s.snake.size(); i++){
            snake.add(new Vector2D(s.snake.get(i)));
        }
    }

    public int length(){
        return snake.size();
    }

    //deep copy
    public Vector2D get(int index){
        return new Vector2D(snake.get(index).x, snake.get(index).y);
    }

    public boolean colidesBody(){
        Vector2D head = snake.get(0);

        for(int i = 1; i < snake.size(); i++){
            if(snake.get(i).equals(head)){
                return true;
            }
        }

        return false;
    }

    public boolean colidesWall(){
        Vector2D head = snake.get(0);

        return (head.x == 0) || 
                (head.x == mapSize) || 
                (head.y == 0) || 
                (head.y == mapSize);
    }

    public void move(char c){
        
        //move everyone except for the head
        for(int i = snake.size() - 1; i > 0; i--){
            Vector2D prev = snake.get(i - 1);
            
            Vector2D _this = snake.get(i);
            
            _this.x = prev.x;
            _this.y = prev.y;
        }
        
        Vector2D head = snake.get(0);
        switch(c){
            case 'w':
                head.y--;
                break;
            case 'a':
                head.x--;
                break;
            case 's':
                head.y++;
                break;
            case 'd':
                head.x++;
                break;
        }
    }

    public boolean isEating(Apple a){
        Vector2D head = snake.get(0);

        if((a.getX() == head.x) && (a.getY() == head.y)){
            return true;
        }

        return false;
    }

    public void addPart(char dir){

        Vector2D newPart = new Vector2D(snake.getLast());
        switch(dir){
            case 'w':
                newPart.y++;
                break;
            case 'a':
                newPart.x++;
                break;
            case 's':
                newPart.y--;
                break;
            case 'd':
                newPart.x--;
                break;
        }

        snake.add(newPart);
    }
}
