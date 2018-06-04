package org.flaig.selenium.customization;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CustomSelect extends Select implements SelectInput {
    
    public CustomSelect(WebElement element) {
        super(element);
    }

    /*
     * Selects multiple text, applies only to multiple select 
     */
    @Override
    public void selectByVisibleText(String... texts) {
        for (String text : texts) {
            super.selectByVisibleText(text);
        }       
    }
    // more custom methods here...
}
