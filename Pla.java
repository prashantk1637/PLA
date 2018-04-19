import java.util.*;
class Pla
{	// 100 data points to train the model
	static double x[][]=new double[100][3];
	// 100 classes
	static double y[]=new double[100];
	static double w0,w1,w2;
	static double RandomNumber(int min,int max)
	{
		Random r=new Random();
		double num=min+r.nextDouble()*(max-min);
		return num;	
	}
	static void pla()
	{
		double label,sum;
		
		for(int j=0;j<100;j++)// 100 epoch
		{
			for(int i=0;i<100;i++)
			{
				sum=w0*x[i][0]+w1*x[i][1]+w2*x[i][2];
				if(sum>0)
				label=1;
				else label=-1;
				
				if(label!=y[i])
				{
					w0=w0+x[i][0]*y[i];
					w1=w1+x[i][1]*y[i];
					w2=w2+x[i][2]*y[i];
				
				}
		
			}
			
		}
		
	}
	public static void main(String[] args)
	{
		
		for(int i=0;i<100;i=i+2)
		{	
			
			// 50 class -1 data
			x[i][0]=1;
			x[i][1]=RandomNumber(0,1);
			x[i][2]=RandomNumber(0,1);
			y[i]=-1;
			// 50 class 1 data
			x[i+1][0]=1;
			x[i+1][1]=RandomNumber(1,2);
			x[i+1][2]=RandomNumber(1,2);
			y[i+1]=1;
		}
		//Initial weights 
		w0=RandomNumber(0,2);
		w1=RandomNumber(0,2);
		w2=RandomNumber(0,2);
		//PLA Learning method call
		pla();
		
		System.out.println("\n");
		//boundary line
		System.out.println(w0+"+"+w1+"x"+"+"+w2+"y");
		//testing
		System.out.println("Testing......");
		for(int i=0;i<3000;i++)
		{
			double a=RandomNumber(0,1);
			double b=RandomNumber(0,1);
			if(w0+w1*a+w2*b<0)
			System.out.println("Expected class 0, Got class 0");
		else System.out.println("Expected class 1 , Got class 0");
			
		}
	}
}