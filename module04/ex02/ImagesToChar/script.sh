#!bin/bash
rm -rf target

javac -cp ./lib/* -d ./target/fr.42.printer ./src/java/fr.42.printer/*/*.java;

mkdir -p target/resources
cp -r ./src/resources  ./target/

jar cfm ./target/images-to-chars-printer.jar ./src/manifest.mf -C target/fr.42.printer .


#run
java -jar target/images-to-chars-printer.jar -w . -b 0  -i target/resources/it.bmp

