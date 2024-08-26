
# to compile
javac -d ./target ./src/java/fr.42.printer/*/*.java;

#to run
java -cp ./target fr/_42/printer/app/Program  char1 char2 path_to_image

#example
java -cp ./target fr/_42/printer/app/Program  . 0 ./it.bmp
