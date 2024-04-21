package tests.user_profile_general_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user_information.UserProfile;

class user_profile_tests {

    /*
     * Test the functionality of adding a username and password
     */

    // TEST BATCH 1 an invalid username with a valid password (setLoginInformation
    // should return false )

    // set up the objects needed for the test suite
    UserProfile sampleUser;

    @BeforeEach
    void setup() {
        sampleUser = new UserProfile();

    }

    @Test // username starting with a numeric character should be false/ invalid
    void testInvalidUsernameNumeric() {

        assertEquals(false, sampleUser.setLoginInformation("9Nevin", "Stan@L131"));
    }

    @Test // show a invalid username test that contains spaces
    void testInvalidUsernameContainsSpaces() {
        assertEquals(false, sampleUser.setLoginInformation("Ne  vinkokoko", "Stan@L237"));
    }

    @Test // show a invalid username test that contains filing characters
    void testInvalidUsernameContainsFilingCharacters() {

        assertEquals(false, sampleUser.setLoginInformation("Nevin^", "Stan@L332"));

        assertEquals(false, sampleUser.setLoginInformation("Ne~vin", "Stan@L337"));

    }

    // TEST BATCH 2 a valid username with an invalid password (setLoginInformation
    // should return false )

    @Test // password starting with a numeric character should be false/ invalid
    void testInvalidPasswordNumeric() {
        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "247Stan@L"));
    }

    @Test // passwords shorter than 8 characters should be false/ invalid
    void testInvalidPasswordLength() {
        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "Stan2@1"));
    }

    @Test // password must start with a numeric character
    void testInvalidPasswordNoSpecialCharacter() {

        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "StanL240"));

    }

    @Test // show a invalid password test that contains spaces
    void testInvalidPasswordContainsSpaces() {
        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "Stan@L 132 "));

    }

    @Test // show a invalid password test that contains filing characters (tests for ^)
    void testInvalidPasswordContainsFilingCharacterPart1() {

        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "Stan@L^425"));

    }

    @Test // show a invalid password test that contains filing characters (test for ~)
    void testInvalidPasswordContainsFilingCharacterPart2() {

        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00L", "Sta~n@L256"));
    }

    // TEST BATCH 3 a valid username with an valid password (setLoginInformation
    // should return true )

    @Test // password starting with a numeric character should be false/ invalid
    void testvalidUsernameAndPassword() {
        assertEquals(true, sampleUser.setLoginInformation("Nevin@isc00L", "Stan@L330"));
    }

    @Test
    void testvalidPasswordAndUsername() {

        assertEquals(true, sampleUser.setLoginInformation("Nevin@isc00Ler", "Stan@L231"));
    }

    @Test
    void testUpdatevalidPasswordAndUsername() {
        assertEquals(true, sampleUser.setLoginInformation("Nevin@isc00Ler", "Stan@L468"));
    }

    @Test
    void testUpdateInvalidPasswordAndUsername() {
        assertEquals(false, sampleUser.setLoginInformation("Nevin@isc00Ler", "Stan@L"));
    }

}
