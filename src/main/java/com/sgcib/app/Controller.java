package com.sgcib.app;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    @FXML
    private TextArea value;

    @FXML
    private TextArea result;

    private Crypter crypter;

    @FXML
    public void initialize() {
        try {
            crypter = new Crypter();
        } catch (Crypter.Exception e) {
            logError(e);
        }
    }

    @FXML
    public void encrypt() {
        try {
            if (crypter != null) {
                this.result.setText(crypter.encrypt(this.value.getText()));
            } else {
                throw new Crypter.Exception("Crypter is null");
            }
        } catch (Crypter.Exception e) {
            logError(e);
        }
    }

    @FXML
    public void decrypt() {
        try {
            if (crypter != null) {
                this.result.setText(crypter.decrypt(this.value.getText()));
            } else {
                throw new Crypter.Exception("Crypter is null");
            }
        } catch (Exception e) {
            logError(e);
        }
    }

    @FXML
    public void clear() {
        this.value.clear();
        this.result.clear();
    }

    private void logError(Exception e) {
        this.result.setText(Stream.of(e.getStackTrace()).
                map(st -> st.toString()).
                collect(Collectors.joining("\r\n")));
    }
}
