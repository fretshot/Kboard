package com.infamous_software.kboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;


public class Kboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard1;
    private Keyboard keyboard2;

    private boolean caps = false;
    private boolean kb2 = false;

    private AudioManager audioManager;

    @Override
    public View onCreateInputView() {

        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);

        keyboard1 = new Keyboard(this, R.xml.keys_layout1);
        keyboard2 = new Keyboard(this, R.xml.keys_layout2);

        keyboardView.setKeyboard(keyboard1);

        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    public Kboard() {
        super();
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Log.i("sempiternal", "Código: "+ primaryCode);
        playClick(primaryCode);
        InputConnection inputConnection = getCurrentInputConnection();
        if (inputConnection != null) {
            switch(primaryCode) {

                case Keyboard.KEYCODE_ALT:

                    if (!kb2){
                        kb2 = true;
                        keyboardView.setKeyboard(keyboard2);
                    }else{
                        kb2 = false;
                        keyboardView.setKeyboard(keyboard1);
                    }

                    keyboardView.invalidateAllKeys();keyboardView.invalidateAllKeys();

                    break;

                case Keyboard.KEYCODE_DELETE :
                    CharSequence selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0);
                    } else {keyboardView.invalidateAllKeys();
                        inputConnection.commitText("", 1);
                    }
                    break;

                case Keyboard.KEYCODE_SHIFT:
                    caps = !caps;
                    keyboard1.setShifted(caps);
                    keyboardView.invalidateAllKeys();
                    break;

                case Keyboard.KEYCODE_DONE:
                    inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    break;

                default :
                    char code = (char) primaryCode;
                    if(Character.isLetter(code) && caps){
                        code  = Character.toUpperCase(code);
                    }
                    inputConnection.commitText(String.valueOf(code), 1);
                    break;
            }
        }

    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    private void playClick(int i){

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        switch(i){
            case 32:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;

            case Keyboard.KEYCODE_DONE:

            case 10:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;

            case Keyboard.KEYCODE_DELETE:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;

            default:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }

    }

}
