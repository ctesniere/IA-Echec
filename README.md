# IA Echec
[![Build Status](https://travis-ci.org/ctesniere/IA-Echec.png?branch=master)](https://travis-ci.org/ctesniere/IA-Echec)
[![Dependency Status](https://gemnasium.com/ctesniere/IA-Echec.png)](https://gemnasium.com/ctesniere/IA-Echec)
[![Coverage Status](https://coveralls.io/repos/ctesniere/IA-Echec/badge.png)](https://coveralls.io/r/ctesniere/IA-Echec)  
![Codeship Status](https://www.codeship.io/projects/1cba7b50-3595-0131-8c4c-4ace70bdb565/status)]

## Présentation

Le projet est constituer d'une application swing JAVA (partie IHM) et un webService qui sera l'Intelligence Artificielle (partie Serveur) qui gérera les déplacement des pions d'un échiquiers.
  
A chaque coup de l'IA, le joueur aura la possibilité de revoir le coup d'avant ou suivant grâce a l'historique des coups.
  
L'IA jouera jusqu'à ce que le coté blanc ou noir ait gagné ou que plus aucun coup ne soit possible. Le joueur aura la possibilité de recommencer une autre partie. 

## Installation

Executer l'IA dans le teminal avec la commande ``mvn clean install tomcat:run``
Executer dans eclipse l'IHM avec ``Java Application`` sur App.java
