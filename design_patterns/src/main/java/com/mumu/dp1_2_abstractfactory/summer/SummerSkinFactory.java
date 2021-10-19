package com.mumu.dp1_2_abstractfactory.summer;

import com.mumu.dp1_2_abstractfactory.core.Button;
import com.mumu.dp1_2_abstractfactory.core.ComboBox;
import com.mumu.dp1_2_abstractfactory.core.SkinFactory;
import com.mumu.dp1_2_abstractfactory.core.TextField;

public class SummerSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SummerButton();
    }

    public TextField createTextField() {
        return new SummerTextField();
    }

    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
