#!/bin/bash

# Variabel path proyek
project_path="c:/adrian/kuliah/SEMS4/OOP/tubes/TugasBesarOOPV3/TugasBesarOOP/TugasBesarOOP"

# Variabel path JDK
jdk_path="C:/Program Files/Java/jdk-19/bin/java"

# Masuk ke direktori proyek
cd "$project_path"

# Compile proyek
javac -d bin -sourcepath src -cp "lib/*" src/main/Main.java

# Jalankan proyek
"$jdk_path" -XX:+ShowCodeDetailsInExceptionMessages -cp "bin;lib/*" main.Main
