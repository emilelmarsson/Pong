public class Pad extends Rectangle{
	private float speed;
	
	private boolean UP, DOWN;
	
	private int score;
	
	public Pad(float x, float y, float w, float h){
		super(x, y, w, h);
		
		speed = 4;
		
		UP = false; DOWN = false;
		
		score = 0;
	}
	
	public void update(){
		if(UP){
			if(y - speed >= 0)
				y -= speed;
			else
				y = 0;
		}else if(DOWN){
			if(y + h + speed <= Main.HEIGHT)
				y += speed;
			else
				y = Main.HEIGHT - h;
		}
	}
	
	public void update(Ball ball){
		if(ball.isStarted())
			return;
		
		if(ball.getDX() > 0){
			if(ball.getY() + ball.getH() > y + h){
				if(y + h + speed <= Main.HEIGHT)
					y += speed;
				else{
					y = Main.HEIGHT - h;
				}
			}else if(ball.getY() < y){
				if(y + h - speed >= 0)
					y -= speed;
				else
					y = 0;
			}
		}
	}
	
	public void incrementScore(){
		score++;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setUp(boolean UP){
		this.UP = UP;
	}
	
	public void setDown(boolean DOWN){
		this.DOWN = DOWN;
	}
}