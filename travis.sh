#!/bin/bash

echo "TEST IA Echec"
cd "IA Echec"
mvn install test

cd ..

echo "TEST IHM Java"
cd "IHM Java"
mvn install test