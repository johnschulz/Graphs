# Graphs


To use the Trip application:

Download all files.

Open terminal/bash and change to the directory in which Graphs is downloaded.

Compile all files in the terminal:

     javac -g -Xlint:unchecked trip/*.java
     
Run:
    
    java trip.main [-m MAP] [-o OUT] Start_Place Place ... End Place
    
Examples:
    
    java trip.Main -m testing/trip/trip01.map Berkeley San_Francisco Santa_Cruz
    
    java trip.Main -m testing/trip/trip01.map San_Francisco Santa_Cruz
    
Enjoy!
