package org.example.snake;

import java.io.IOException;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class SnakeApp {
    static private Apple apple;
    static private Snake snake;
    public static void main(String[] args){

        try{
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            terminal.enterPrivateMode();
            terminal.clearScreen();
            TextGraphics tg = terminal.newTextGraphics();
            
            terminal.setCursorVisible(false);

            final int mapSize;
            
            //ask for map size
            while(true){
                
                tg.putString(0, 0, "Choose the size of the map:");
                tg.putString(0, 1, "1. 8 tiles");
                tg.putString(0, 2, "2. 16 tiles");
                tg.putString(0, 3, "3. 32 tiles");
                tg.putString(0, 4, "4. 64 tiles");
                
                terminal.flush();

                int num = -1;
                KeyStroke key = terminal.readInput();
                
                if(key.getKeyType() == KeyType.Character){

                    Character c = key.getCharacter();
                    
                    if(c != null && Character.isDigit(c)){
                        num = Character.digit(c, 10);
                    }
                }
                
                if(num < 1 || num > 4){
                    tg.putString(0, 0, "Invalid size!");
                    terminal.flush();
                } else{
                    switch(num){
                        case 1:
                            mapSize = 8;
                            break;
                        case 2:
                            mapSize = 16;
                            break;
                        case 3: 
                            mapSize = 32;
                            break;
                        case 4: 
                            mapSize = 64;
                            break;
                        default: 
                            mapSize = 8;
                    }
                    break;
                }
            }

            terminal.clearScreen();
            terminal.setCursorPosition(0,0);

            apple = new Apple(mapSize);
            snake = new Snake(mapSize);
            Map map = new Map(mapSize, apple, snake);
            map.printMap(tg);
            terminal.flush();
            
            long previous = System.currentTimeMillis();
            
            char direction = 'w';
            
            boolean running = true;
            while(running){
                KeyStroke key = terminal.pollInput();
                if (key != null){
                    if (key.getKeyType() == KeyType.Escape){
                        break;
                    }
                
                    if(key.getKeyType() == KeyType.Character){
                        Character c = key.getCharacter();

                        if((c != null) && ((c == 'w') || (c == 'a') || (c == 's') || (c == 'd'))){
                            direction = (char) c;
                        }
                    }
                }
                
                
                final long now = System.currentTimeMillis();
                if(now - previous > 400){
                    snake.move(direction);
                    map.printMap(tg);
                    terminal.flush();
                    previous = now;
                }

                if(snake.colidesBody() || snake.colidesWall()){
                    running = false;
                }

                if(snake.isEating(apple)){
                    snake.addPart(direction);
                    apple.generateNewPos();
                }

                map.printMap(tg);
                terminal.flush();
                
            } //while(running)

            terminal.exitPrivateMode();
            terminal.close();
        } //try
        catch(IOException e){
            e.printStackTrace();
        }
    } //main
}
