import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

public class Main{
	public static final int WIDTH = 800, HEIGHT = 500;
	public static final String TITLE = "Pong";
	
	public static State state;
	
	public static Random r = new Random();
	
	public static Pad left, right;
	
	public static Ball ball;
	
	public static void main(String args[]){
		createWindow();
		
		init();
		
		while(!Display.isCloseRequested()){
			render();
			
			input();
			
			update();
		}
		
		destroy();
	}
	
	public static void resetGame(){
		state = State.GAME;
		ball = new Ball(WIDTH / 2 - WIDTH / 80, HEIGHT / 2 - HEIGHT / 80, WIDTH / 40, WIDTH / 40);
		left = new Pad(WIDTH / 40, HEIGHT / 2 - HEIGHT / 8, WIDTH / 40, HEIGHT / 4);
		right = new Pad(WIDTH - WIDTH / 20, HEIGHT / 2 - HEIGHT / 8, WIDTH / 40, HEIGHT / 4);
	}
	
	public static void createWindow(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			// Skapar fönstret.
			Display.create();
		}catch(LWJGLException e){
			// Om något går fel kommer vi hit.
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}
	
	public static void init(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		resetGame();
	}
	
	public static void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		
		ball.render();
		left.render();
		right.render();
		renderLine();
		
		if(state == State.PAUSED){
			
		}
	}
	
	public static void input(){
		if(state == State.GAME){
			while(Keyboard.next()){
				if(Keyboard.getEventKey() == Keyboard.KEY_W)
					left.setUp(Keyboard.getEventKeyState());
				else if(Keyboard.getEventKey() == Keyboard.KEY_S)
					left.setDown(Keyboard.getEventKeyState());
				
				if(Keyboard.getEventKey() == Keyboard.KEY_UP)
					right.setUp(Keyboard.getEventKeyState());
				else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN)
					right.setDown(Keyboard.getEventKeyState());
			}
		}
	}
	
	public static void update(){
		if(state == State.GAME){
			ball.update(left, right);
			left.update();
			right.update(ball);
		}
		
		Display.update();
		
		Display.sync(60);
	}
	
	public static void renderLine(){
		float thickness = 2.0f;
		int lines = 32;
		
		glLineWidth(thickness);
		glBegin(GL_LINES);
			for(int i = 0; i <= lines; i+=2){
				glVertex2f(WIDTH / 2, 4 + (HEIGHT / lines) * i);
				glVertex2f(WIDTH / 2, 4 + (HEIGHT / lines) * (i + 1));
			}
		glEnd();
	}
	
	public static void destroy(){
		Display.destroy();
		System.exit(0);
	}
}