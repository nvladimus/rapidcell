public class Cell2D extends Cell{
	protected double PositionX, PositionY, Orientation;
	private static long seed;
	private static MersenneTwister RG;
	public Cell2D(double posX, double posY, double ori, double rtstate){
	 PositionX=posX;
	 PositionY=posY;
	 Orientation=ori;
	 RunTumbleState=rtstate;
	 lastID++;
	 CellID=lastID;
 }
	public Cell2D(double posx, double posy, double ori){
		Orientation=ori; //
		PositionX=posx;
		PositionY=posy;
		RunTumbleState=Run;
		lastID++;
		CellID=lastID;
	}

	public Cell2D(double posx, double posy){
		double rand=RG.nextDouble();
		Orientation=2.0*Math.PI*rand; //
		PositionX=posx;
		PositionY=posy;
		RunTumbleState=Run;
		lastID++;
		CellID=lastID;
	}
	public void setOrientation(double newOri){		Orientation=newOri;	}
	public double getOrientation(){ return Orientation;	}
	public void reverseOrientation(int border){
		if ((border==0)||(border==2)) Orientation=Math.PI-Orientation; //left or right wall
		else Orientation=-Orientation; // up or down wall
	}
	// Isotropic tumble:
	public double tumble(){	
		double rand=RG.nextDouble();
		int direction=1;
		if(RG.nextBoolean()) direction=-1;
		double tumbling_angle=Math.acos(2.0*Math.sqrt(rand)-1.0);
		Orientation=Orientation+direction*tumbling_angle;
		return tumbling_angle;
		}
	// Anisotropic tumble:
	public double tumbleCWdependent(double CWmotors){
		double tumbling_angle=0;
		if(Cell.Nmotors==2){
			if(CWmotors==1.0) tumbling_angle=61.55/180*Math.PI;
			else if(CWmotors==2.0) tumbling_angle=80.0/180*Math.PI;
		}
		else if(Cell.Nmotors==3){
			if(CWmotors==1.0) tumbling_angle=48.5/180*Math.PI; //
			else if(CWmotors==2.0) tumbling_angle=80.0/180*Math.PI;
			else if(CWmotors==3.0) tumbling_angle=100.0/180*Math.PI;
		}
		else if(Cell.Nmotors==4){
			if(CWmotors==1.0) tumbling_angle=23.23/180*Math.PI;
			else if(CWmotors==2.0) tumbling_angle=80.0/180*Math.PI;
			else if(CWmotors==3.0) tumbling_angle=100.0/180*Math.PI;
			else if(CWmotors==4.0) tumbling_angle=120.0/180*Math.PI;
		}
		else if(Cell.Nmotors==5){
			if(CWmotors==1.0) tumbling_angle=0;
			else if(CWmotors==2.0) tumbling_angle=67.82/180*Math.PI;//67.45
			else if(CWmotors==3.0) tumbling_angle=100.0/180*Math.PI;
			else if(CWmotors==4.0) tumbling_angle=120.0/180*Math.PI;
			else if(CWmotors==5.0) tumbling_angle=140.0/180*Math.PI;
		}
		else {tumbling_angle=0; System.out.print("No tumble: The number of motors should be 2, 3, 4 or 5\n");}
		int direction=1;
		if(RG.nextBoolean()) direction=-1;
		Orientation=Orientation+direction*tumbling_angle;
		return tumbling_angle;
	}
	//rotational diffusion:
	public void addRotDiffusion(){
		double rand;
		rand=RG.nextGaussian()*Math.sqrt(2.0*Drotation*dt);
		Orientation=Orientation+rand;
	}
	//Setters&Getters
	public void setPosition(double newPosX,double newPosY){	PositionX=newPosX; PositionY=newPosY;	}
	public double getPositionX(){		return PositionX;	}
	public double getPositionY(){		return PositionY;	}
	public static void setRandSeed(long s){
			seed=s;
			RG = new MersenneTwister(seed);
		 }
}
