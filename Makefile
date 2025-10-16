all :
	javac -encoding UTF-8 -cp ".:utils/jar/mysql-connector-java-8.0.30.jar:utils/jar/bcrypt.jar" Main.java utils/*.java app/*.java factory/*.java DAO/*.java menu/*.java src/*.java
	java -cp ".:utils/jar/mysql-connector-java-8.0.30.jar:utils/jar/bcrypt.jar" Main

clean :
	rm -f Main.class
	rm -f utils/*.class
	rm -f src/*.class
	rm -f DAO/*.class
	rm -f app/*.class
	rm -f factory/*.class
	rm -f menu/*.class