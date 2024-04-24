# base-project
## authors
The Penguin group is Nevin Ndonwi, Mario Rodriguez, Andrew Hartmann, Ben Gondzur.

(Penguin 🐧 in chinese is 企鹅 where 企 means business👩‍💼and 鹅 means goose🪿 because penguins look like geese in businessy suits!)

## project description
This project is a Java Banking App simulator operated through the terminal. The app should store user profiles (usernames/passwords), the banking accounts associated with those profiles (checking/saving), as well as functionality to deposit, withdraw, and other banking functions.
## iteration 1
NOTE: All code is currently in the **dev branch**

**During this iteration, we accomplished the following:**
- `user_profile` class with first name, last name, username (profile ID), SSN, security questions
  - Note: SSN and security questions have not been implemented yet, they are currently just instance variables
- `Bank_Account` class, the parent class for `Checking_Account` and `Savings_Account` with a balance, name, history, getters, deposit, and withdraw.
- `Savings_Account` subclass to `Bank_Account`
- `Checking_Account` subclass to `Bank_Account`
- `Menu` class with the main method that runs a simulation of the banking app
  - helper methods: makeProfile, getUsername, getPassword, getNewAccountType, displayingOptions, getNewAccountName, createNewAccount, getUserSelectionToCreateOrView, getUserToSelectBankAccount, getUserAction, getDepositAmount, getWithdrawAmount.
- `script.sh` file which runs Menu.java

**To run the program, while in the `project-penguins` directory terminal, type `./script.sh`**

**For our next interation, we intend to**
- Write JavaDocs for our methods
- Store multiple users. We currently only allow one user to be created and worked through.
  - in this vein, allow logins that recognize your profile and say "Welcome Back, xxxx!"
- Clean up the main method and other classes methods to follow coding best practices
- Expand user security to need Social Security Number, and a Security Question when creating a profile.
- Add a class/option for an `Offshore Bank Account` with financial loopholes which allow it to accrue interest at high rates

**Is there anything we've implemented that doesn't work?**
- There are some user account fields that aren't being used (but not necessarily not working), so we will implement those in our methods going forward.

## iteration 2

**During this iteration, we accomplished the following:**
- enabled background music with one availible (for now) track. (Useability may depend on updated version of Java and can be hardware dependent.)
 -- added tests for sound in `Menu` class
- Users can make multiple accounts, and can log in and out of them at will.
- majorly refactored main, created utility classes and changed how username and passwords are stored to help in following best coding practices.
  -- added test suite for `Pair` class, other `ParseUtils` class is entirely dependent on user input, and all user input is self contained within the class, so no test suites are possible.

**For our next interation, we intend to**
- Create a cleaner interface for the `Menu` class, so that much of the menu can be boiled down to less hard coded menus.
- Expand library of music choices, and allow user to choose music from a curated list. 
- Expand usage of `Bank_Account` classes, e.g. `Savings_Accounts` have interest applied over time and have a withdrawal limit set based on total money in the account.
- Create new utility classes to help reduce code density, and do away with repetitive code throughout the code base.

**Is there anything we've implemented that doesn't work?**
- Music can be finnicky depending on hardware, some e.g. some antiviruses block Eclipse from accessing speakers, etc.
- Menu testing is largely dependent on user input, however methods can can be tested should be
- Code makes use of many effectively while(true) loops, it is entirely possible that certain inputs could cause an infinite while loop.



## iteration 3
Overall, Users should be able to create a profile with our esteemed banking company and create bank accounts to store currency for operations and management within 
the personal and private sector. Users can create multiple bank accounts, log in, log out, and conduct other functionalities. Users are able to store their personal information being
first name, last name, and/or social security number in our system for storage and efficient retrieval. Use our application to get acquainted and let your 
wildest dreams unfold!

**During this iteration, we accomplished the following:**
- Removed the Sound Track Background Music Implimentation. 
- Organized branches by deleting unneeded ones
- Fixed File Naming Conventions across some packages
- Added First Name, Last Name, and Social Security Number as user fields that are stored with user profiles and user bank account information
- refactored methods to require less lines
- implimented bank account history (each account stores what happens to it regarding deposits, withdrawals and other bank account operations)
- users are able to see the bank account history data once it is updated when requested within the application. 



**For our next interation, we intend to**
- This is the final iteration in this course

**Is there anything we've implemented that doesn't work?**
- Not that we are aware of.
  
  
