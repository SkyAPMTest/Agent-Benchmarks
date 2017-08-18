#!/usr/bin/env bash

PRG="$0"
PRGDIR=`dirname "$PRG"`
[ -z "$WEB_HOME" ] && WEB_HOME=`cd "$PRGDIR/.." >/dev/null; pwd`

CLASSPATH="$WEB_HOME/config:$CLASSPATH"
for i in "$WEB_HOME"/libs/*.jar
do
    CLASSPATH="$i:$CLASSPATH"
done

java -classpath $CLASSPATH org.skywalking.apm.benchmark.example.Main
