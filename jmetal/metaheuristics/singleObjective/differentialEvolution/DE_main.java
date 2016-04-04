//  DE_main.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
package jmetal.metaheuristics.singleObjective.differentialEvolution;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.util.JMException;

import jmetal.problems.ProblemFactory;

import java.util.HashMap;

/**
 * This class runs a single-objective DE algorithm.
 */
public class DE_main {

  public static void main(String [] args) throws JMException, ClassNotFoundException {
    Problem   problem   ;         // The problem to solve
    Algorithm algorithm ;         // The algorithm to use
    Operator  crossover ;         // Crossover operator
    Operator  mutation  ;         // Mutation operator
    Operator  selection ;         // Selection operator
            
    HashMap  parameters ; // Operator parameters

    if (args.length == 1) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
    } // if
    else if (args.length == 2) {
      Object [] params = {"Real"};
      problem = (new ProblemFactory()).getProblem(args[0],params);
    } // if
    else { // Default problem
      System.out.println("Select problem to run from problems folder");
      return;
    } // else
    
    algorithm = new DE(problem) ;   // Asynchronous cGA
    
    /* Algorithm parameters*/
    algorithm.setInputParameter("populationSize",100);
    algorithm.setInputParameter("maxEvaluations", 1000000);
    
    // Crossover operator 
    parameters = new HashMap() ;
    parameters.put("CR", 0.5) ;
    parameters.put("F", 0.5) ;
    parameters.put("DE_VARIANT", "rand/1/bin") ;

    crossover = CrossoverFactory.getCrossoverOperator("DifferentialEvolutionCrossover", parameters);                   
    
    // Add the operators to the algorithm
    parameters = null;
    selection = SelectionFactory.getSelectionOperator("DifferentialEvolutionSelection", parameters) ;

    algorithm.addOperator("crossover",crossover);
    algorithm.addOperator("selection",selection);
 
    /* Execute the Algorithm */
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;
    System.out.println("Total execution time: " + estimatedTime);

    /* Log messages */
    System.out.println("Objectives values have been writen to file "+args[0]+"_DE_FUN");
    population.printObjectivesToFile(args[0]+"_DE_FUN");
    System.out.println("Variables values have been writen to file "+args[0]+"_DE_VAR");
    population.printVariablesToFile(args[0]+"_DE_VAR");          
  } //main
} // DE_main
