all :
	javac -encoding UTF-8 -cp ".:utils/jar/mysql-connector-java-8.0.30.jar" Main.java utils/*.java app/*.java factory/*.java DAO/*.java menu/*.java src/*.java
	java -cp ".:utils/jar/mysql-connector-java-8.0.30.jar" Main

clean :
	rm Main.class
	rm utils/*.class
	rm src/*.class
	rm DAO/*.class
	rm app/*.class
	rm factory/*.class
	rm menu/*.class