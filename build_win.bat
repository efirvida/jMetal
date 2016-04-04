@echo off
set JMETALHOME=%cd%
set PATH=%PATH%;%JMETALHOME%\java\bin
set CLASSPATH="%CLASSPATH%;%JMETALHOME%"

dir %JMETALHOME%\jmetal\*.java /S /B > sources.txt
javac @sources.txt

del sources.txt