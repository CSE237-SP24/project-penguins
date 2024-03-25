#!/bin/bash

# Compiles Menu.java (main method file) and outside package (user_profile.java)
javac bank_Account/Menu.java user_information/user_profile.java

# Runs main method file
java bank_Account.Menu