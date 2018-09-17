/**
 * 
 */

/**
 * @author nvladimi
 *
 */
public class Motor {
	public int RotaryState;
	public static final int CCW=1;
	public static final int CW=0;
	public static final double Hmb=10.3;//Hill coef. for mb(cheYp)
	private static double Mbias0;//0.65 is default steady-state motor bias;
	public static final double Tccw=1.33;// s, default aver. time of CCW rotation, 1.33
	public static double Tcw;//Tccw*(1.0/Mbias0-1.0), default aver. time of CW rotation
//	public final double Mbias0=Tccw/(Tccw+Tcw);//steady-state motor bias, if Tccw and Tcw given
	public double CCWintervalDuration=0;
	public double CWintervalDuration=0;
	protected double dt=Model.dt;
	private Cell hostcell;
	private static int motorModel;//0 if t_cw=const, 1 if t_cw and t_ccw depend on omega
	public Motor (Cell myCell){
		hostcell=myCell;
		RotaryState=CCW;
	}
//	Getters:
	public double getCCWtoCWswitchFreq(){
		if (motorModel==0) return (1.0/Tcw)*(1.0/getMotorBias()-1.0);
		else return getMotorReversalFrequency()/(2.0*getMotorBias());
	}
	
	public double getCWtoCCWswitchFreq(){
		if (motorModel==0) return (1.0/Tcw);
		else return getMotorReversalFrequency()/(2.0*(1.0-getMotorBias()));
	}

	public double getMotorReversalFrequency(){
		double omega=0.5*Hmb*(1.0/Mbias0-1.0)*Math.pow(hostcell.chemotaxisNetwork.cheYp,Hmb-1)/Math.pow((1.0+(1.0/Mbias0-1.0)*Math.pow(hostcell.chemotaxisNetwork.cheYp,Hmb)),2);
		return omega;
	}
	
	public double getCCWintervalDuration(){
		return CCWintervalDuration;
	}	
	public double getCWintervalDuration(){
		return CWintervalDuration;
	}	
	public double getMotorBias(){// mb(CCW)
		double bias=(1.0/(1.0+(1.0/Mbias0-1.0)*Math.pow(hostcell.chemotaxisNetwork.cheYp,Hmb)));
		return bias;
	}
	public static double getMbias0(){return Mbias0;}
// Adders:
	public void addDtToCCWinterval(){
		CCWintervalDuration=CCWintervalDuration+dt;
	}
	public void addDtToCWinterval(){
		CWintervalDuration=CWintervalDuration+dt;
	}
// Resetters:
	public void resetCCWintervalDuration(){
		CCWintervalDuration=0;
	}
	public void resetCWintervalDuration(){
		CWintervalDuration=0;
	}

// Setters:
	public static void setMotorBias0(double MbiasNew, int newMotorModel){
		Mbias0=MbiasNew;
		Tcw=Tccw*(1.0/MbiasNew-1.0);
		motorModel=newMotorModel;
	}
}
