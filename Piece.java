import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//single Piece in Tetris

public class Piece extends Actor
{
    //color:  1:red  2:orange  3:yellow  4:green  5:blue  6:magenta  7:pink
    public Piece(int color) {
        GreenfootImage img = new GreenfootImage("textures/games/tetris/tetris_block_" + color + ".png");
        img.scale(40, 40);
        setImage(img);
    }
}
