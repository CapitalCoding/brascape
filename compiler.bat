@echo OI
javac -encoding ISO-8859-1 -cp "bin;data/libs/*;data/libs/slf4j/*;" -d bin $(find src -name *.java)
pause