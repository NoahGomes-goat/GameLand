import java.awt.*;

public class Character {
    //variables
    public int xpos;
    public int ypos;
    public int dx; //dx is the speed on the x direction
    public int dy; //dy is the speed in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rec;
    public Image pic;
    public Rectangle healthbar;
    public int health;
    public boolean isIntersecting=false;

    //have a constructor
    public Character(int pXpos, int pYpos, int pDx, int pDy) {
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx;
        dy = pDy;
        width = 60;
        height = 80;
        isAlive = true;
        health = width;
        rec = new Rectangle(xpos, ypos, width, height);
        rec = new Rectangle(xpos, ypos, width, height);
        healthbar = new Rectangle(xpos, ypos, health, 5);

    }

    //printInfo
    public void printInfo() {
        System.out.println("X position: " + xpos);
        System.out.println("X position: " + ypos);
        System.out.println("x speed: " + dx);
        System.out.println("x speed: " + dy);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("Is your Hero alive" + isAlive);

    }

    public void bounceMove() {
        if (xpos > (1000 - width)) {
            dx = (-1) * dx;
        }
        if (xpos < 0) {
            dx = (-1) * dx;
        }
        if (ypos > (700 - height)) {
            dy = (-1) * dy;
        }
        if (ypos < 0) {
            dy = (-1) * dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
        healthbar = new Rectangle(xpos, ypos, health, 5);

    }

    public void wrapMove() {

        if (xpos > 1000) {
            xpos = 0 - width;
        }
        //DO Now: create a wrapping move that works on all four walls.
        if (ypos > 700) {
            ypos = 0 - height;
        }
        if (xpos < 0 && dx < 0) {
            xpos = 1000;
        }
        if (ypos < 0 && dy < 0) {
            ypos = 700;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
        healthbar = new Rectangle(xpos, ypos, health, 5);

    }

    public void controlMove() {

        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
}


