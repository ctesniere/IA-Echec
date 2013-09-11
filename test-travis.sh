#!/bin/bash

echo "TEST IA ECHEC"
cd "IA Echec"

mvn install test

cd ..

echo "TEST IHM JAVA"
cd "IHM Java"
mvn install test