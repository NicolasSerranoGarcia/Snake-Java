package org.example.snake;

public class Vector2D {
    int x, y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public boolean equals(Object v){
        if(!(v instanceof Vector2D)){
            return false;
        }

        final Vector2D other = (Vector2D) v;

        if(other == this){
            return true;
        }

        if(this.x != other.x){
            return false;
        }

        if(this.y != other.y){
            return false;
        }

        return true;
    }
}
