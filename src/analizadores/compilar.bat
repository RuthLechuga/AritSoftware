javac JLex/Main.java
java JLex.Main Lexico
java -jar Flex/jflex-1.6.1.jar Lexico
pause
java -jar Cup/java-cup-11b.jar -parser Sintactico Sintactico
pause