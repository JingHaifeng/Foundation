package com.alex.code.foundation.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {

    private KeyboardUtils() {
    }

    /**
     * 关闭软键盘
     *
     * @param view 一般应该传入 EditText , 但在动态布局中应该传入 layout 而不是 EditText
     * @return true 关闭成功，否则 false
     */
    public static boolean hideKeyboard(View view) {

        if (view == null) {
            throw new NullPointerException("View is null!");
        }

        try {
            InputMethodManager imm =
                    (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm == null) {
                return false;
            }

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
