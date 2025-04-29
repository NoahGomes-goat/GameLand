//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class GameLand implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too
    public Character astro; //step 1, declare
    public Image astroPic;

   // public Character naruto;
    public Image narutoPic;
    public Image Backround;
    public Image gameOver;
    public boolean astroIsIntersectingNaruto;

  //  public Character Blingblingboy;
    //public Image Blingblingboypic;

  //  public Character DrDoof;
   // public Image DrDoofpic;

   // public Character Benson;
  //  public Image Bensonpic;

    public Character[] hamburger;
    public Image hamburgerpic;

    public Character [] stone;
    public Image stonepic;

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;



    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() { // BasicGameApp constructor

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game
        astro = new Character(10, 10, 0, 0); //step 2, construct
        astro.printInfo();

        //creating an constructing images
        astroPic = Toolkit.getDefaultToolkit().getImage("astro-removebg-preview.png"); //step 3, construct image
        Backround = Toolkit.getDefaultToolkit().getImage("Backround.jpg");
        gameOver = Toolkit.getDefaultToolkit().getImage("game over.jpg");
        //naruto = new Character(7, 7, 3, 3);
       // naruto.printInfo();

       // narutoPic = Toolkit.getDefaultToolkit().getImage("naruto-removebg-preview.png");

       // Blingblingboy = new Character(10, 30, 3, 4);
        //Blingblingboypic = Toolkit.getDefaultToolkit().getImage("Blingblingboy-removebg-preview.png");

      //  DrDoof = new Character(40, 90, 1, 1);
    //    DrDoofpic = Toolkit.getDefaultToolkit().getImage("DrDoof-removebg-preview.png");

       // Benson = new Character(110, 145, 2, 1);
        //Bensonpic = Toolkit.getDefaultToolkit().getImage("Benson.png");

        hamburger = new Character[100]; //CONSTRUCT AND DECIDE A SIZE FOR OUR ARRAY
        hamburgerpic = Toolkit.getDefaultToolkit().getImage("betterhamburger.png");
        for (int i = 0; i < hamburger.length; i = i + 1) {
            int randx = (int) (900 * Math.random());
            int randy = (int) (-15000 * Math.random());
            hamburger[i] = new Character(randx, randy, 0, 2);
        }

        stone = new Character [100];
       stonepic = Toolkit.getDefaultToolkit().getImage("betterstone.png");
        for (int i = 0; i < stone.length; i = i + 1) {
            int randx = (int) (900 * Math.random());
            int randy = (int) (-15000 * Math.random());
            stone[i] = new Character(randx, randy, 0, 2);
        }
    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
            checkIntersections();

        }
    }

    public void moveThings() {
        //call the move() code for each object
        //if (astro.isAlive = true) {
        astro.controlMove();
        // }

       // naruto.bounceMove();
       // Blingblingboy.bounceMove();
        //DrDoof.wrapMove();
       // Benson.bounceMove();
        for (int x = 0; x < hamburger.length; x = x + 1) {
            hamburger[x].wrapMove();
        }
    for (int x = 0; x < stone.length; x = x + 1){
    stone[x].wrapMove();
}
    }

    public void checkIntersections() {
        //rec has a method called intersects
        //intersects takes in an input of another rec

        for (int g = 0; g < hamburger.length; g = g + 1) {
            if (astro.rec.intersects(hamburger[g].rec)) {
                System.out.println("ouch");
                hamburger[g].isAlive = false;
                hamburger[g].xpos = 2000;
                hamburger[g].dx = 0;
                hamburger[g].dy = 0;
            }
        }
//        if (astro.rec.intersects(naruto.rec) && astroIsIntersectingNaruto == false) {
//            astroIsIntersectingNaruto = true;
//            System.out.println("ouch");
//            naruto.height = naruto.height + 1;
//            naruto.width = naruto.width + 1;
        for(int s = 0; s < stone.length; s = s + 1){
        if (astro.rec.intersects(stone[s].rec) && stone[s].isIntersecting == false) {
            stone[s].isIntersecting=true;
            astro.health = astro.health - 3;
        }
            if (astro.rec.intersects(stone[s].rec)==false) {
                stone[s].isIntersecting=false;
            }
        }



    }
//        if (Blingblingboy.rec.intersects(naruto.rec)) {
//            Blingblingboy.height = Blingblingboy.height + 1;
//            Blingblingboy.width = Blingblingboy.width + 1;
//        }
//        if (DrDoof.rec.intersects(Blingblingboy.rec)) {
//            DrDoof.dx = DrDoof.dx + 1;
//            DrDoof.dy = DrDoof.dy + 1;
//        }
//        if (benson.rec.intersects(naruto.rec)) {
//            Benson.health = Benson.health - 1;
//      }


    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT); // these two lines stay at the top

        //draw the images

        g.drawImage(Backround, 0, 0, 1000, 700, null);
        g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
       // g.drawImage(narutoPic, naruto.xpos, naruto.ypos, naruto.width, naruto.height, null);
       // g.drawImage(Blingblingboypic, Blingblingboy.xpos, Blingblingboy.ypos, Blingblingboy.width, Blingblingboy.height, null);
      //  g.drawImage(DrDoofpic, DrDoof.xpos, DrDoof.ypos, DrDoof.width, DrDoof.height, null);
       // g.drawImage(Bensonpic, Benson.xpos, Benson.ypos, Benson.width, Benson.height, null);
        g.setColor(Color.GREEN);
       // g.fillRect(Benson.healthbanarutor.x, Benson.healthbar.y, Benson.healthbar.width, Benson.healthbar.height);
//        if (astro.rec.intersects(.rec)) {
//            g.drawString("ouch", 500, 500);

//        }
     g.fillRect(astro.xpos, astro.ypos-5, astro.health, 10);

        for (int h = 0; h < hamburger.length; h = h + 1) {
            g.drawImage(hamburgerpic, hamburger[h].xpos, hamburger[h].ypos, hamburger[h].width, hamburger[h].height, null);
        }
        for(int s = 0; s < stone.length; s = s + 1){
            g.drawImage(stonepic, stone[s].xpos, stone[s].ypos, stone[s].width, stone[s].height, null);
        }

        if(astro.health<0){
            g.drawImage(gameOver,0,0,WIDTH, HEIGHT, null);
        }

        // these two lines need to stay here at the bottom
        g.dispose();
        bufferStrategy.show();


    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
        canvas.addKeyListener(this);
        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // we can ignore this method but don't delete it
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar(); // this gets the character of the button you press and saves it under :key"
        int keyCode = e.getKeyCode(); // this gets the number associated with the button you press
        System.out.println("Key Pressed" + key + ", Code; " + keyCode);
        if (keyCode == 39) {
            astro.dx = 5;
        }
        if (keyCode == 38) {
            astro.dy = -5;
        }
        if (keyCode == 40) {
            astro.dy = 5;
        }
        if (keyCode == 37) {
            astro.dx = -5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar(); // this gets the character of the button you press and saves it under :key"
        int keyCode = e.getKeyCode(); // this gets the number associated with the button you press
        System.out.println("Key Released" + key + ", Code; " + keyCode);

        if (keyCode == 39) {
            astro.dx = 0;
        }
        if (keyCode == 38) {
            astro.dy = 0;
        }
            if (keyCode == 40) {
                astro.dy = 0;
            }
                if (keyCode == 37) {
                    astro.dx = 0;
            }
        }
    }


