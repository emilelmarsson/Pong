import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

public abstract class Rectangle{
	protected float x, y, w, h;
	
	public Rectangle(float x, float y, float w, float h){
		this.x = x; this.y = y;
		this.w = w; this.h = h;
	}
	
	public void render(){
		glBegin(GL_TRIANGLES);
			glVertex2f(x, y);
			glVertex2f(x, y + h);
			glVertex2f(x + w, y + h);
		
			glVertex2f(x, y);
			glVertex2f(x + w, y);
			glVertex2f(x + w, y + h);
		glEnd();
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getW(){
		return w;
	}
	
	public float getH(){
		return h;
	}
}
