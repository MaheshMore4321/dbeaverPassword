package sd.sym.dbeaversecurity;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

public class DbeaverSecurity {

	private static final byte[] PASSWORD_ENCRYPTION_KEY = "sdf@!#$verf^wv%6Fwe%$$#FFGwfsdefwfe135s$^H)dg".getBytes();
    
    public String encrypt(String unencryptedString) throws Exception {
        if(unencryptedString == null) {
            throw new IllegalArgumentException("Empty string");
        } else {
            try {
                byte[] e = unencryptedString.getBytes("UTF8");
                byte[] plainBytes = Arrays.copyOf(e, e.length + 2);
                plainBytes[plainBytes.length - 2] = 0;
                plainBytes[plainBytes.length - 1] = -127;
                this.xorStringByKey(plainBytes);
                return Base64.getEncoder().encodeToString(plainBytes); 
            } catch (Exception var4) {
                throw new Exception(var4);
            }
        }
    }

    private void xorStringByKey(byte[] plainBytes) throws UnsupportedEncodingException {
        int keyOffset = 0;

        for(int i = 0; i < plainBytes.length; ++i) {
            byte keyChar = PASSWORD_ENCRYPTION_KEY[keyOffset];
            ++keyOffset;
            if(keyOffset >= PASSWORD_ENCRYPTION_KEY.length) {
                keyOffset = 0;
            }

            plainBytes[i] ^= keyChar;
        }

    }

    public String decrypt(String encryptedString) throws Exception {
        if(encryptedString != null && encryptedString.trim().length() > 0) {
            try {
                byte[] e = Base64.getDecoder().decode(encryptedString); 
                this.xorStringByKey(e);
                if(e[e.length - 2] == 0 && e[e.length - 1] == -127) {
                    return new String(e, 0, e.length - 2, "UTF8");
                } else {
                    throw new Exception("Invalid encrypted string");
                }
            } catch (Exception var3) {
                throw new Exception(var3);
            }
        } else {
            throw new IllegalArgumentException("Empty encrypted string");
        }
    }
}
