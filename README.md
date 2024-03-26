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

