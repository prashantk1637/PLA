
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Prashant
 */
public class PLANonLinearTransformation {
    
static int  n;
static double w0,w1,w2;
        static double RandomNumber(int min,int max)
	{
		Random r=new Random();
		double num=min+r.nextDouble()*(max-min);
		return num;	
	}
    
       static double transformation(double a)
        {
            return a*a;
        }
       static void pla(double x[][],double y[])
	{
		double label,sum;
		
		for(int j=0;j<1000;j++)// 100 epoch
		{
			for(int i=0;i<n;i++)
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
        public static void main(String[] args) throws Exception
	{       
		
            Scanner sc=new Scanner(System.in);
               System.out.println("Enter number of data points to train(there are 80 data --> first 40 for class -1 )");
               
               n=sc.nextInt();
		double x[][]=new double[n][3];
                double y[]=new double[n];
                String currentLine="";
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\Prashant\\Documents\\NetBeansProjects\\PLA\\LRNonLinearData.txt"));
		int i=0;
                while((currentLine=br.readLine())!=null &&i<n)
                {
                   StringTokenizer stk=new StringTokenizer(currentLine);
                    String str1=stk.nextToken();
                    String str2=stk.nextToken();
                       if(i<n/2)
                       {
                        x[i][0]=1;
                        x[i][1]=transformation(Double.parseDouble(str1));
                        x[i][2]=transformation(Double.parseDouble(str2));
                        y[i]=-1;
                       }
                       else if(i>=n/2)
                       {
                             x[i][0]=1;
                             x[i][1]=transformation(Double.parseDouble(str1));
                             x[i][2]=transformation(Double.parseDouble(str2));
                               y[i]=1;
                       }
                       
                       i++;
                 } 
                
                //Initial weights 
		w0=RandomNumber(0,10);
		w1=RandomNumber(0,10);
		w2=RandomNumber(0,10);
                pla(x,y);
                

                System.out.println("=================Boundary Line==============================");
		System.out.println(w0+"+"+w1+"x"+"+"+w2+"y=0");
                    
                System.out.println("======================Testing============================");
                for(i=0;i<40;i++)
                {   
                    double a=RandomNumber(-1, 1);
                    double b=RandomNumber(-1, 1);
                    double c=a;
                    double d=b;
                    a=transformation(a);
                    b=transformation(b);
                    
                    if(w0+w1*a+w2*b<0)
			System.out.println("Point->("+c+","+d+")"+" Expected class 0, Got class 0");
		else System.out.println("Point->("+c+","+d+")"+" Expected class 0, Got class 1");
			
                    
                }
                for(i=0;i<40;i++)
                {   
                    double a=RandomNumber(3, 4);
                    double b=RandomNumber(3, 4);
                    double c=a;
                    double d=b;
                    a=transformation(a);
                    b=transformation(b);
                    
                    if(w0+w1*a+w2*b>0)
			System.out.println("Point->("+c+","+d+")"+" Expected class 1, Got class 1");
		else System.out.println("Point->("+c+","+d+")"+" Expected class 1, Got class 0");
			
                    
                }
        }
}
