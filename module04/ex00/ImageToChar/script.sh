#!bin/bash
javac -d ./target/fr.42.printer ./src/java/fr.42.printer/*/*.java;
java -cp ./target/fr.42.printer fr/_42/printer/app/Program . 0 it.bmp
