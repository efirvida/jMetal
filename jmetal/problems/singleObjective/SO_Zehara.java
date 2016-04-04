//  SO_Zehara.java

package jmetal.problems.singleObjective;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;
import jmetal.util.wrapper.XReal;

/**
 * Class representing problem SO_Zehara
*/
public class SO_Zehara extends Problem{
  /**
   * Constructor
   * Creates a default instance of the SO_Zehara problem
   * @param solutionType The solution type must "Real" or "BinaryReal".
   */
  public SO_Zehara(String solutionType) {
    numberOfVariables_  = 7;
    numberOfObjectives_ = 1;
    numberOfConstraints_= 2;
    problemName_        = "SO_Zehara";
        
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];        

    // LIMITES INFERIORES
    lowerLimit_[0] = 797.0;
    lowerLimit_[1] = 252.0;        
    lowerLimit_[2] = 1.6;
    lowerLimit_[3] = 4.8;        
    lowerLimit_[4] = 0.10;
    lowerLimit_[5] = 0.0;        
    lowerLimit_[6] = 1.33;
    
    // LIMITES SUPERIORES
    upperLimit_[0] = 800.0;
    upperLimit_[1] = 260.0;
    upperLimit_[2] = 6.0;
    upperLimit_[3] = 8.0;
    upperLimit_[4] = 0.15;
    upperLimit_[5] = 100.0;
    upperLimit_[6] = 1.58;
        
    if (solutionType.compareTo("BinaryReal") == 0)
      solutionType_ = new BinaryRealSolutionType(this) ;
    else if (solutionType.compareTo("Real") == 0)
    	solutionType_ = new RealSolutionType(this) ;
    else {
    	System.out.println("Error: solution type " + solutionType + " invalid") ;
    	System.exit(-1) ;
    }  
  } // ConstrEx
     
  /** 
  * Evaluates a solution 
  * @param solution The solution to evaluate
   * @throws JMException 
  */
  public void evaluate(Solution solution) throws JMException {
		XReal vars = new XReal(solution) ;
		
    double fx; // function values     
    double [] x = new double[numberOfVariables_] ;
    for (int i = 0 ; i < numberOfVariables_; i++)
    	x[i] = vars.getValue(i);
    	
    double De = x[0];
    double de = x[1];
    double rm = x[2];
    double rp = x[3];
    double miu = x[4];
    double Ppc = x[5];
    double Beta = x[6];

    double pi = 3.1415;
    double kf1 = 540.0;
    double kf2 = 540.0;
    double to = 0.6;
    double Beta_o = 9;

    double D = (0.77 * De/de + 0.23)*de;
    double kfm = (kf1 + kf2)/2;
    double Fid = D * kfm * Math.log(D/de);
    double Fapc = D*miu*Ppc*de/to*(Beta_o -1)/2*Beta;
    double Far = Math.exp(miu*pi/2)*Fid+Fapc;
    double Fre = pi * D * to * kf1 * to/(4*rm);
    
    fx = Fid + Fapc + Far + Fre;        
    
    solution.setObjective(0,fx);
  } // evaluate

 /** 
  * Evaluates the constraint overhead of a solution 
  * @param solution The solution
 * @throws JMException 
  */  
  public void evaluateConstraints(Solution solution) throws JMException {
    double [] constraint = new double[this.getNumberOfConstraints()];

    double x0 = solution.getDecisionVariables()[0].getValue();
    double x1 = solution.getDecisionVariables()[1].getValue();
    double x2 = solution.getDecisionVariables()[2].getValue();
    double x3 = solution.getDecisionVariables()[3].getValue();
    double x4 = solution.getDecisionVariables()[4].getValue();
    double x5 = solution.getDecisionVariables()[5].getValue();
    double x6 = solution.getDecisionVariables()[6].getValue();
    
    double De = x0;
    double de = x1;
    double rm = x2;
    double rp = x3;
    double miu = x4;
    double Ppc = x5;
    double Beta = x6;
    
    double sigma = 515.0;
    double pi = 3.1415;
    double kf1 = 540.0;
    double kf2 = 540.0;
    double to = 0.6;
    double Beta_o = 9;
    
    double D = (0.77 * De/de + 0.23)*de;
    double kfm = (kf1+kf2)/2;
    double Fid = D * kfm * Math.log(D/de);
    double Fapc = D * miu * Ppc * de/to * (Beta_o -1)/2 * Beta;
    double Far = Math.exp( miu * pi/2 ) * Fid + Fapc;
    double Fre = pi * D * to * kf1 * to/(4*rm);
    double Fmax = Fid + Fapc + Far + Fre;        

    constraint[0] = Fmax - pi * De * to * sigma;
    constraint[1] = 3 * rm - 6 * rp;
        
    double total = 0.0;
    int number = 0;
    for (int i = 0; i < this.getNumberOfConstraints(); i++)
      if (constraint[i]<0.0){
        total+=constraint[i];
        number++;
      }
        
    solution.setOverallConstraintViolation(total);    
    solution.setNumberOfViolatedConstraint(number);         
  } // evaluateConstraints  
} // SO_Zehara
