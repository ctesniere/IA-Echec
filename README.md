# IA Echec
[![Build Status](https://travis-ci.org/ctesniere/IA-Echec.png?branch=master)](https://travis-ci.org/ctesniere/IA-Echec)

## Équipe

Philippe YUEN (@pyuen), Cédric TESNIERE (@ctesniere), Valérie MALET (@vmalet)

## Sujet

IA de jeu d'échecs

## Promesse

Permet l’apprentissage d'un jeu d'échecs.

## Présentation

Nous souhaitons créer une application swing JAVA (partie IHM) et un webService qui sera l'Intelligence Artificielle (partie Serveur) qui gérera les déplacement des pions d'un échiquiers. Le but est de permettre à l'utilisateur d'apprendre à jouer, de façon ludique et interactive, aux échecs.
  
A chaque coup de l'IA, le joueur aura la possibilité de revoir le coup d'avant ou suivant grâce a l'historique des coups.
  
L'IA jouera jusqu'à ce que le coté blanc ou noir ait gagné ou que plus aucun coup ne soit possible. Le joueur aura la possibilité de recommencer une autre partie. 

## Installation

Executer l'IA dans le teminal avec la commande ``mvn clean install tomca:run``
Executer dans eclipse l'IHM avec ``Java Application`` sur App.java

## Difficultés technique :

* La principale difficultés est de créer une Intelligence Artificielle.
* Gérer les différents coups d'échecs possibles. 
* L'héritage