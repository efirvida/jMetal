//  SO_Yuniel.java

package jmetal.problems.singleObjective;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;
import jmetal.util.wrapper.XReal;

/**
 * Class representing problem SO_Yuniel
*/
public class SO_Yuniel extends Problem{
  /**
   * Constructor
   * Creates a default instance of the SO_Yuniel problem
   * @param solutionType The solution type must "Real" or "BinaryReal".
   */
  public SO_Yuniel(String solutionType) {
    numberOfVariables_  = 3;
    numberOfObjectives_ = 1;
    numberOfConstraints_= 0;
    problemName_        = "SO_Yuniel";
        
    lowerLimit_ = new double[numberOfVariables_];
    upperLimit_ = new double[numberOfVariables_];        

    // LIMITES INFERIORES
    lowerLimit_[0] = 215.0;
    lowerLimit_[1] = 70.0;        
    lowerLimit_[2] = 30.0;
        
    // LIMITES SUPERIORES
    upperLimit_[0] = 230.0;
    upperLimit_[1] = 80.0;
    upperLimit_[2] = 60.0;
            
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
    	
    double Tm = x[0];
    double Te = x[1];
    double Tw = x[2];

    double pi = 3.1415;
    double a = 0.06;
    double S = 2.5;
    double Peso = 240.7;
    double Cap = 118.0;
    
    double Tc = Math.pow(S,2)/(pi*a)*Math.log((8/Math.pow(pi,2)*a)*(Tm-Tw/Te-Tw));
    double Ti = (Peso/Cap);
        
    fx = Tc + Ti;        
    
    solution.setObjective(0,fx);
  } // evaluate
} // SO_Yuniel
