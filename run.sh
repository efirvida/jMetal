#!/bin/bash
rm *.log  2>> /dev/null
export JMETALHOME=$PWD/jmetal


if [[ "$1" == "PSO" ]]; then
	export metaheuristics="singleObjective.particleSwarmOptimization.PSO_main"
	export metaheuristics_name="Particle Swarm Optimization"
elif [[ "$1" == "CMAES" ]];then
	export metaheuristics="singleObjective.cmaes.CMAES_main"
	export metaheuristics_name="CMA-ES"
elif [[ "$1" == "DE" ]];then
	export metaheuristics="singleObjective.differentialEvolution.DE_main"
	export metaheuristics_name="Differential Evolution"
elif [[ "$1" == "ES" ]];then
	export metaheuristics="singleObjective.evolutionStrategy.ES_main"
	export metaheuristics_name="Evolution Strategy"
elif [[ "$1" == "GA" ]];then
	export metaheuristics="singleObjective.geneticAlgorithm.pgGA_main"
	export metaheuristics_name="Genetic Algorithm"
elif [[ "$1" == "NSGAII" ]]; then
	export metaheuristics="nsgaII.NSGAII_main"
	export metaheuristics_name="NSGAII"
elif [[ "$1" == "NSGAIIAdaptive" ]];then
	export metaheuristics="nsgaII.NSGAIIAdaptive_main"
	export metaheuristics_name="NSGAII Adaptive"
elif [[ "$1" == "pNSGAII" ]];then
	export metaheuristics="nsgaII.pNSGAII_main"
	export metaheuristics_name="Parallel NSGAII"
elif [[ "$1" == "MOEAD" ]];then
	export metaheuristics="moead.MOEAD_main"
	export metaheuristics_name="MOEAD"
elif [[ "$1" == "pMOEAD" ]];then
	export metaheuristics="moead.pMOEAD_main"
	export metaheuristics_name="Parallel MOEAD"
elif [[ "$1" == "OMOPSO" ]];then
	export metaheuristics="omopso.OMOPSO_main"
	export metaheuristics_name="OMOPSO"
elif [[ "$1" == "SMPSO" ]];then
	export metaheuristics="smpso.SMPSO_main"
	export metaheuristics_name="SMPSO"
elif [[ "$1" == "pSMPSO" ]];then
	export metaheuristics="smpso.pSMPSO_main"
	export metaheuristics_name="Parallel SMPSO"
elif [[ "$1" == "SMPSOhv" ]];then
	export metaheuristics="smpso.SMPSOhv_main"
	export metaheuristics_name="SMPSOhv"
elif [[ "$1" == "RandomSearch" ]];then
	export metaheuristics="randomSearch.RandomSearch_main"
	export metaheuristics_name="Random Search"
else 
	echo "Method \"$1\" not found"
	echo "Select One of the following methos"
	echo "======================================"
	echo "Single Objetive Methods"
	echo "======================================"
	echo "PSO   for Particle Swarm Optimization"
	echo "GA    for Genetic Algorithm"
	echo "CMAES for CMA-ES"
	echo "DE    for Differential Evolution"
	echo "ES    for Evolution Strategy"
	echo ""
	echo "======================================"
	echo "Multi Objetive Methods"
	echo "======================================"
	echo "NSGAII            for NSGAII"
	echo "NSGAIIAdaptive    for NSGA Adaptive"
	echo "pNSGAII           for Parallel NSGAII"
	echo "MOEAD             for MOEAD"
	echo "pMOEAD            for pMOEAD"
	echo "OMOPSO            for OMOPSO"
	echo "SMPSO             for SMPSO"
	echo "pSMPSO            for Parallel SMPSO"
	echo "SMPSOhv           for SMPSOhv"
	echo "RandomSearch      for RandomSearch"
	return;
fi

echo "==============================="
echo -ne Building problems in \"$JMETALHOME/problems/\" 
find $JMETALHOME/problems/ -name "*.java" > sources.txt
javac @sources.txt
rm sources.txt
echo " Done!!"


echo Runing Optimization Problem: \"$2\" with \"$metaheuristics_name\" method

java jmetal.metaheuristics.$metaheuristics $2

echo "==============================="
echo Ploting $2_$1_FUN file results with gnuplot 
echo "==============================="
gnuplot -p -e "plot \"$2_$1_FUN\" using 1:2"



