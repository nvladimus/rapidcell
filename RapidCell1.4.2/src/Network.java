public class Network {
	protected double dt=Model.dt;
	public double cheYp;
	private static double CheA_tot, CheR, CheBP, CheY_tot, CheZ, meth0;
	private static final double CheR_wt=1, CheB_wt=1;// 
	// parameters of MWC model:
	public static double Ka_off;// Tar, mM, 0.02 - MeAsp[Endres06], 1.7e-3 - Asp [Emonet05]; 0.014 - Tar, MeAsp[Sourjik, Temp. effects, unpublished]
	public static double Ka_on;// Tar, mM, 0.5 - MeAsp[Endres06], 12e-3 - Asp [Emonet05]
	public static double Kd=Math.sqrt(Ka_off*Ka_on);//
	private static double Ks_off;// Mm, 100 - Tsr, MeAsp[Endres06]
	private static double Ks_on;// mM, 1e+6 - Tsr, MeAsp[Endres06]
	private static double n_a;// 6 Tars in a cluster of 18
	private static double n_s;// 12 Tsrs in cluster of 18
	public double meth;// methylation state (0..8)
	public double cheAp;
	public double P_on;
	public double adaptRate;// adaptation rate
	public int groupID=0;// grouping variable, to simulate heterogeneous populations
	private static double adaptPrecision;// set 1.0 for a perfect adaptation (100%)
	private static double k_R=0.0182, k_B=0.0364;// effective catalytic rates
	private static double kA=5, kY=100, kZ=30, gY=0.1;// 

	public Network(Cell newCell){
		adaptRate=Model.commonAdaptRate;//default coefficient of adaptation rate, if the population is homogeneous
		meth=meth0;
		P_on=1./3.;
	}

	public void updateMWCmodel(double S){
		double eps_meth=0, sum_fa, sum_fs, F;
		// update methylation level, by simple ordinary differential equation
		if(meth<0) meth=0;
		else if (meth>8) meth=8.0;
		else meth=meth+dt*adaptRate*(k_R*CheR*(1.0-P_on)-k_B*CheBP*P_on);
		// compute free-energy offset from methylation:
		if(meth<0) eps_meth=1.0; // Endres & Wingreen, 2006, piece-wise linear
			else if(meth<2) eps_meth= 1.0-0.5*meth;
				else if (meth<4) eps_meth=-0.3*(meth-2.0);
					else if  (meth<6) eps_meth=-0.6-0.25*(meth-4.0);
						else if (meth<7) eps_meth=-1.1-0.9*(meth-6.0);
							else if (meth<8) eps_meth=-2.0-(meth-7.0);
								else eps_meth=-3.0;
		sum_fa= n_a*(eps_meth + Math.log((1+S/Ka_off)/(1+S/Ka_on)));
		sum_fs= n_s*(eps_meth + Math.log((1+S/Ks_off)/(1+S/Ks_on)));
		F=sum_fa+sum_fs;
		P_on=1.0/(1.0+Math.exp(F)); //probability that rec. cluster is ON
		cheAp=CheA_tot*P_on*kA/(P_on*kA+kY*CheY_tot); //amount of phosphorylated CheA
		double scaling=19.3610;// scales cheYp linearly so that cheYp=1 at rest (p_on=1/3)
		cheYp=adaptPrecision*scaling*CheY_tot*kY*cheAp/(kY*cheAp+kZ*CheZ+gY);// phosphorylated CheY-P, scaled to 1.0 at rest
	}
	public static void setNreceptors(int Ntar, int Ntsr){
		n_a=Ntar;// Tars in a cluster 
		n_s=Ntsr;// Tsrs in a cluster
	}
	public void setAdaptRate(double newAdaptRate){
		adaptRate=newAdaptRate;
	}

	public static void setDissConstants(double Ka_off1, double Ka_on1, double Ks_off1, double Ks_on1){
		Ka_off=Ka_off1;
		Ka_on=Ka_on1;
		Kd=Math.sqrt(Ka_off*Ka_on);
		Ks_off=Ks_off1;
		Ks_on=Ks_on1;
	}
	public static void setAdaptPrecision(double AP){adaptPrecision=AP;}
	public static void setRelativeCheA(double A) {CheA_tot = A;}
	public static void setRelativeCheRCheB(double R, double B){	CheR=R*CheR_wt; CheBP=B*CheB_wt;}
	public static void setRelativeCheYCheZ(double Y, double Z){	CheY_tot=Y; CheZ=Z;}
	public static void setIniMethState(double m){meth0=m;};
	public static void setRates(double kR0, double kB0, double kA0, double kY0, double kZ0){
		k_R=kR0;
		k_B=kB0;
		kA=kA0;
		kY=kY0;
		kZ=kZ0;
	}
}
