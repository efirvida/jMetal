set Algorith=PSO
set Problem=SO_Yuniel



@echo off
set JMETALHOME=%cd%
set PATH=%PATH%;%JMETALHOME%\java\bin
if /i "%Algorith%" == "PSO" goto PSO
if /i "%Algorith%" == "CMAES" goto CMAES
if /i "%Algorith%" == "DE" goto DE
if /i "%Algorith%" == "ES" goto ES
if /i "%Algorith%" == "GA" goto GA

if /i "%Algorith%" == "NSGAII" goto NSGAII
if /i "%Algorith%" == "NSGAIIAdaptive" goto NSGAIIAdaptive
if /i "%Algorith%" == "pNSGAII" goto pNSGAII
if /i "%Algorith%" == "MOEAD" goto MOEAD
if /i "%Algorith%" == "pMOEAD" goto pMOEAD
if /i "%Algorith%" == "OMOPSO" goto OMPSO
if /i "%Algorith%" == "SMPSO" goto SMPSO
if /i "%Algorith%" == "pSMPSO" goto pSMPSO
if /i "%Algorith%" == "SMPSOhv" goto SMPSOhv
if /i "%Algorith%" == "RandomSearch" goto RandomSearch 

goto commonExit

:PSO
set metaheuristics=singleObjective.particleSwarmOptimization.PSO_main
set metaheuristics_name=Particle Swarm Optimization
goto runOptimization

:CMAES
set metaheuristics=singleObjective.cmaes.CMAES_main
set metaheuristics_name=CMA-ES
goto runOptimization

:DE
set metaheuristics=singleObjective.differentialEvolution.DE_main
set metaheuristics_name=Differential Evolution
goto runOptimization

:ES
set metaheuristics=singleObjective.evolutionStrategy.ES_main
set metaheuristics_name=Evolution Strategy
goto runOptimization

:GA
set metaheuristics=singleObjective.geneticAlgorithm.pgGA_main
set metaheuristics_name=Genetic Algorithm
goto runOptimization

:NSGAII
set metaheuristics=nsgaII.NSGAII_main
set metaheuristics_name=NSGAII
goto runOptimization

:NSGAIIAdaptive
set metaheuristics=nsgaII.NSGAIIAdaptive_main
set metaheuristics_name=NSGAII Adaptive
goto runOptimization

:pNSGAII
set metaheuristics=nsgaII.pNSGAII_main
set metaheuristics_name=Parallel NSGAII
goto runOptimization

:MOEAD
set metaheuristics=moead.MOEAD_main
set metaheuristics_name=MOEAD
goto runOptimization

:pMOEAD
set metaheuristics=moead.pMOEAD_main
set metaheuristics_name=Parallel MOEAD
goto runOptimization

:OMOPSO
set metaheuristics=omopso.OMOPSO_main
set metaheuristics_name=OMOPSO
goto runOptimization

:SMPSO
set metaheuristics=smpso.SMPSO_main
set metaheuristics_name=SMPSO
goto runOptimization

:pSMPSO
set metaheuristics=smpso.pSMPSO_main
set metaheuristics_name=Parallel SMPSO
goto runOptimization

:SMPSOhv
set metaheuristics=smpso.SMPSOhv_main
set metaheuristics_name=SMPSOhv
goto runOptimization

:RandomSearch
set metaheuristics=randomSearch.RandomSearch_main
set metaheuristics_name=Random Search
goto runOptimization

:commonExit
echo Method "%Algorith%" not found
echo Select One of the following methos
echo ======================================
echo Single Objetive Methods
echo ======================================
echo PSO   for Particle Swarm Optimization
echo GA    for Genetic Algorithm
echo CMAES for CMA-ES
echo DE    for Differential Evolution
echo ES    for Evolution Strategy
echo ======================================
echo Multi Objetive Methods
echo ======================================
echo NSGAII            for NSGAII
echo NSGAIIAdaptive    for NSGA Adaptive
echo pNSGAII           for Parallel NSGAII
echo MOEAD             for MOEAD
echo pMOEAD            for pMOEAD
echo OMOPSO            for OMOPSO
echo SMPSO             for SMPSO
echo pSMPSO            for Parallel SMPSO
echo SMPSOhv           for SMPSOhv
echo RandomSearch      for RandomSearch
pause
goto:eof


:runOptimization
echo %metaheuristics%
echo =========================================================
echo |set /p=Building problems in "%JMETALHOME%/problems/" 
dir %JMETALHOME%\jmetal\problems\*.java /S /B > sources.txt
javac @sources.txt
del sources.txt
echo  Done!!
echo Runing Optimization Problem: "%Problem%" with "%metaheuristics_name%" method
java jmetal.metaheuristics.%metaheuristics% %Problem%
pause
