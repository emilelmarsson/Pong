public class Ball extends Rectangle{
	private float dx, dy;
	private float defaultX, defaultY;
	
	private Timer timer;
	
	public Ball(float x, float y, float w, float h){
		super(x, y, w, h);
		
		defaultX = x; defaultY = y;
		timer = new Timer(500, false);

		setRandomAngle();
	}
	
	public void setRandomAngle(){
		float angle = ((Main.r.nextFloat() * 2 - 1) / 2) * (float) Math.PI / 2;
		if(dx > 0)
			angle = ((Main.r.nextFloat() * 2 - 1) / 2) * (float) Math.PI / 2 - (float) Math.PI;
		
		dx = (float) Math.cos(angle) * 7;
		dy = (float) Math.sin(angle) * 7;
		
		timer.start();
	}
	
	public void setDefaultPosition(){
		x = defaultX; y = defaultY;
		
		setRandomAngle();
	}
	
	public float getDX(){
		return dx;
	}
	
	public float getDY(){
		return dy;
	}
	
	public boolean isStarted(){
		return timer.isStarted();
	}

	public void update(Pad left, Pad right){
		if(timer.isStarted()){
			timer.isPastDelay();
			return;
		}
		
		if((y + h >= left.getY() && y <= left.getY() + left.getH()) && x > left.getX() && x < left.getX() + left.getW()){
			x = left.getX() + left.getW();
			dx = -dx;
		}
		
		else if((y + h >= right.getY() && y <= right.getY() + right.getH()) && x + w > right.getX() && x + w < right.getX() + right.getW()){
			x = right.getX() - right.getW();
			dx = -dx;
		}
		
		// Collision detection for walls.
		if((dx < 0 && x + dx >= 0) || (dx > 0 && x + w + dx <= Main.WIDTH)){
			x += dx;
		}else if(dx < 0 && x + dx < 0){
			right.incrementScore();
			setDefaultPosition();
		}else if(dx > 0 && x + w + dx > Main.WIDTH){
			left.incrementScore();
			setDefaultPosition();
		}
		
		if((dy < 0 && y + dy >= 0) || (dy > 0 && y + h + dy <= Main.HEIGHT)){
			y += dy;
		}else if(dy < 0 && y + dy < 0){
			y = 0;
			dy = -dy;
		}else if(dy > 0 && y + h + dy > Main.HEIGHT){
			y = Main.HEIGHT - h;
			dy = -dy;
		}
	}
}