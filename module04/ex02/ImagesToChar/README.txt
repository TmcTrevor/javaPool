

1 - make sure you are inside ImagesTochar folder
2 - you can execute script.sh file or  follow the next steps

	1 - # to clean
		rm -rf target

	2 - # to compile
		javac -d ./target/fr.42.printer ./src/java/fr.42.printer/*/*.java

	3 - # Copy the compiled classes of the libraries to the target
		(cd target && jar xf ../lib/jcommander-*.**.jar)
		(cd target && jar xf ../lib/JCDP-*.*.*.jar || jar xf ../lib/JCOLOR-*.*.*.jar)

	4 - # to copy resources
		cp -r ./src/resources ./target/

	5 - # to create jar file
		jar cfm ./target/images-to-chars-printer.jar ./src/manifest.mf -C target/ .

	6 - # to run jar file
		java -jar ./target/images-to-chars-printer.jar --options
			  Options:
  					* --black, -b   color to switch with black
  					* --image, -i   Path to the image
  					* --white, -w   color to switch with white

	# example
		java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN  -i=target/resources/it.bmp
