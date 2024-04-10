package tests.menu_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import bank_Account.Menu;
import user_information.user_profile;

public class Menu_tests {
    
    Menu testMenu;
    user_profile testUser;

    @BeforeEach
    void setup() {
        testMenu = new Menu();
        
        testUser = new user_profile();
    }
    
}
