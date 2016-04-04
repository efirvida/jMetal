#!/bin/bash
export JMETALHOME=$PWD/jmetal
export CLASSPATH=$CLASSPATH:$JMETALHOME
find $JMETALHOME -name "*.java" > sources.txt
javac @sources.txt
rm sources.txt
echo build done!
