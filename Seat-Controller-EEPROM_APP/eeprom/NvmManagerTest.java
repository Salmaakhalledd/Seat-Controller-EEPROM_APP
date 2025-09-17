package eeprom;

import org.junit.Test;
import static org.junit.Assert.*;

public class NvmManagerTest {

    @Test
    public void testReadStartup() {
        NvmManager nvm = new NvmManager();
        String res = nvm.readStartup();
        assertTrue(res.contains("READ_STARTUP"));
    }

    @Test
    public void testWriteStartup() {
        NvmManager nvm = new NvmManager();
        byte[] data = {0x01, 0x02, 0x03};
        String res = nvm.writeStartup(data);
        assertTrue(res.contains("WRITE_STARTUP"));
    }
}
