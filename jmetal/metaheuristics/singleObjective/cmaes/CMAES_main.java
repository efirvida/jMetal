//  CMAES_main.java
//
//  Author:
//       Esteban López-Camacho <esteban@lcc.uma.es>
//
//  Copyright (c) 2013 Esteban López-Camacho
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
package jmetal.metaheuristics.singleObjective.cmaes;

import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.util.JMException;

import jmetal.problems.ProblemFactory;


/**
 * This class runs a single-objective CMA-ES algorithm.
 */
public class CMAES_main {

  public static void main(String [] args) throws JMException, ClassNotFoundException {
    int numberOfVariables = 20;
    int populationSize = 10;
    int maxEvaluations = 1000000;

    Problem problem   ;         // The problem to solve
    Algorithm algorithm ;         // The algorithm to use


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


    algorithm = new CMAES(problem) ;
    
    /* Algorithm parameters*/
    algorithm.setInputParameter("populationSize", populationSize);
    algorithm.setInputParameter("maxEvaluations", maxEvaluations);
 
    /* Execute the Algorithm */
    long initTime = System.currentTimeMillis();
    SolutionSet population = algorithm.execute();
    long estimatedTime = System.currentTimeMillis() - initTime;
    System.out.println("Total execution time: " + estimatedTime);

    /* Log messages */
    System.out.println("Objectives values have been written to file"+args[0]+"_CMAES_FUN");
    population.printObjectivesToFile(args[0]+"_CMAES_FUN");
    System.out.println("Variables values have been written to file "+args[0]+"_CMAES_VAR");
    population.printVariablesToFile(args[0]+"_CMAES_VAR");

  } //main

} // CMAES_main
