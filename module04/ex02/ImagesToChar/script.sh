#!/bin/bash

# Clean up the target directory
rm -rf target

# mkdir -p target/fr.42.printer
# mkdir -p target/com/beust
# mkdir -p target/com/diogonunes

# Compile the Java files
javac -cp "./lib/*" -d ./target/ ./src/java/fr.42.printer/*/*.java

# Copy the compiled classes of the libraries to the target
(cd target && jar xf ../lib/jcommander-*.**.jar)
(cd target && jar xf ../lib/JCDP-*.*.*.jar || jar xf ../lib/JCOLOR-*.*.*.jar)

# Copy resources to the target directory
cp -r ./src/resources ./target/

# Create the JAR file
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.mf -C target/ .

# Ensure the lib directory is available for runtime
# cp -r ./lib ./target/

# echo "Build completed. You can now run your application using the JAR file located in the target directory."

#run
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN  -i=target/resources/it.bmp
