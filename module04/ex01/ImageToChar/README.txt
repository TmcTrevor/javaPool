# to clean
rm -rf target

# to compile
javac -d ./target/fr.42.printer ./src/java/fr.42.printer/*/*.java

# to copy resources
mkdir -p target/resources
cp -r resources/* target/resources/

# to create jar file
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.mf -C target/fr.42.printer .

# to run jar file
java -jar ./target/images-to-chars-printer.jar char1 char2 path_to_image

# example
java -jar ./target/images-to-chars-printer.jar . 0  ./target/resources/it.bmp
