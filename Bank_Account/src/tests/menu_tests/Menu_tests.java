package tests.menu_tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import bank_Account.Menu;
import user_information.user_profile;

public class Menu_tests {
    
    Menu testMenu;
    user_profile testUser;

    @BeforeEach
    void setup() {
        testMenu = new Menu();
        testUser = new user_profile();
        testMenu.songPlayerCycler();
    }

    // Tests getAudioInputStreamFromFile's exception handling
    @Test
    void testAudioInputStream() throws UnsupportedAudioFileException, IOException {

        File badFile = new File("script.sh");
        assertEquals(null, testMenu.getAudioInputStreamFromFile(badFile));

        File noFile = new File("");
        System.out.println(noFile.getAbsolutePath());
        assertEquals(null, testMenu.getAudioInputStreamFromFile(noFile));
        
    }

    // Tests if clips have different thread allocation
    @Test
    void testGetClip() throws LineUnavailableException {
        Clip audioClip = testMenu.getClipFromFile();
        assertEquals(false, AudioSystem.getClip().equals(audioClip));

    }

}
