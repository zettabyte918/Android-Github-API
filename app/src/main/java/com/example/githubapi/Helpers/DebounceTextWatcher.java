package com.example.githubapi.Helpers;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

public class DebounceTextWatcher implements TextWatcher {
    private static final long DEBOUNCE_DELAY_MS = 500; // Adjust the delay as needed
    private Handler handler = new Handler();
    private Runnable runnable;
    private DebounceListener debounceListener;

    public DebounceTextWatcher(DebounceListener debounceListener) {
        this.debounceListener = debounceListener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        handler.removeCallbacks(runnable);
        runnable = () -> {
            String username = editable.toString();
            debounceListener.onDebounce(username);
        };
        handler.postDelayed(runnable, DEBOUNCE_DELAY_MS);
    }

    public interface DebounceListener {
        void onDebounce(String text);
    }
}
