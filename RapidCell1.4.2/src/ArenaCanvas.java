import java.awt.*;
import javax.swing.*;
public class ArenaCanvas extends Canvas {
	private Cell2D[] arrCells;
	private int x_ini, y_ini;
	private double Lx, Ly;
	private int n = 1;
	private int width;
	private int height;
	private boolean restart = false;
	
	public ArenaCanvas(double x, double y, double Lx0, double Ly0, int n0)
    {
		this.setForeground(Color.DARK_GRAY);
		this.setBackground(Color.CYAN);
		this.setSize(new Dimension(200, 200));
		this.setPreferredSize(new Dimension(200, 200));
		this.setMinimumSize(new Dimension(50, 50));
		width = this.getWidth();
		height = this.getHeight();
		x_ini=(int) (width*x);
		y_ini=(int) (height*y);
		Lx = Lx0;
		Ly = Ly0;
		n = n0;
    }
    public void paint(Graphics g)
    {
    	if(arrCells == null || restart) g.fillRect(x_ini, y_ini, 2, 2);
    	else for (int i=0; i<n; i++) g.fillRect((int)(arrCells[i].PositionX/Lx*width), (int)((1 - arrCells[i].PositionY/Ly)*height), 2, 2);
    }
    public void setCells(Cell2D[] cells){
    	arrCells = cells;
    }

    public void setCanvas(double x, double y, double Lx0, double Ly0, int n0){
		x_ini=(int) (width*x);
		y_ini=(int) (height*(1-y));
		Lx = Lx0;
		Ly = Ly0;
		n = n0;
    }
    public void restart() {restart = true;}
    public void start() {restart = false;}
}
