#!/bin/bash

BASE_DIR="$( cd "$( dirname "$1" )" >/dev/null 2>&1 && pwd )"

cd "$BASE_DIR/Bank_Account/src"

# Compiles Menu.java (main method file) and outside package (user_profile.java)
javac bank_Account/*.java user_information/*.java

# Runs main method file
cd "$BASE_DIR"

# this specifies the classpath, which tells where compiled classes are
java -cp "$BASE_DIR/Bank_Account/src" bank_Account.Menu