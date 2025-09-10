package org.example.snake;

import java.io.IOError;
import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


public class Map {
    private int mapSize;
    private Apple apple;
    private Snake snake;

    public Map(){
        mapSize = 8;
        apple = new Apple(mapSize);
        snake = new Snake(mapSize);
    }
    public Map(int Size, Apple a, Snake s){
        if(Size <= 0){
            mapSize = 8;
        } else{
            mapSize = Size;
        }
        apple = a;
        snake = s;
    }

    public void printMap(TextGraphics tg) throws IOException{

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (i == 0 || i == mapSize - 1 || j == 0 || j == mapSize - 1) {
                    tg.setCharacter(j, i, '#');
                    continue;
                }

                if (i == apple.getY() && j == apple.getX()) {
                    tg.setCharacter(j, i, '@');
                    continue;
                }

                boolean isBody = false;
                for (int k = 0; k < snake.length(); k++) {
                    if (snake.get(k).x == j && snake.get(k).y == i) {
                        tg.setCharacter(j, i, '0');
                        isBody = true;
                        break;
                    }
                }

                if (!isBody) {
                    tg.setCharacter(j, i, ' ');
                }
            }
        }
    }
}
