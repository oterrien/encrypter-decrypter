package com.sgcib.app;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    @FXML
    private TextArea value;

    @FXML
    private TextArea result;

    private SecretKey secretKey;

    @FXML
    public void initialize() {
        try  {
            byte[] desKeyData = {(byte) 0x04, (byte) 0x01, (byte) 0x07, (byte) 0x04, (byte) 0x02, (byte) 0x08, (byte) 0x02, (byte) 0x01};
            DESKeySpec desKeySpec = new DESKeySpec(desKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(desKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void encrypt() throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] text = value.getText().getBytes();
            byte[] textEncrypted = cipher.doFinal(text);
            this.result.setText(new BASE64Encoder().encode(textEncrypted));
        } catch (Exception e) {
            this.result.setText(Stream.of(e.getStackTrace()).
                    map(st -> st.toString()).
                    collect(Collectors.joining("\r\n")));
        }
    }

    @FXML
    public void decrypt() {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] text = new BASE64Decoder().decodeBuffer(value.getText());
            byte[] textEncrypted = cipher.doFinal(text);
            this.result.setText(new String(textEncrypted));
        } catch (Exception e) {
            this.result.setText(Stream.of(e.getStackTrace()).
                    map(st -> st.toString()).
                    collect(Collectors.joining("\r\n")));
        }
    }

    @FXML
    public void clear() {
        this.value.clear();
        this.result.clear();
    }
}
