package org.flaig.selenium.customization;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.Annotations;

public class CustomAnnotation extends Annotations {
    private Field field;
    private String xpathPrefix;
    
    public CustomAnnotation(Field field, String xpathFormat, String xpathPrefix) {
        super(field);
        this.field = field;
    }
    
    public CustomAnnotation(Field field) {
    	super(field);
        this.field = field;
	}

	@Override
    public By buildBy() {
        By locateBy = null;
        CustomFindBy findBy = field.getAnnotation(CustomFindBy.class);

        if (findBy == null) {
            locateBy = super.buildByFromDefault(); 
            return locateBy;
        }

        String xpath;
        // lets check if there are multiple parameters
        if (findBy.params().length > 0) {
            Object[] objs = Arrays.copyOf(findBy.params(), findBy.params().length, Object[].class);
            xpath = String.format(findBy.format(), objs);
        }
        else { 
            xpath = String.format(findBy.format(), xpathPrefix, findBy.param());    
        }
        locateBy = By.xpath(xpath);

        if (locateBy == null) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + field);
        }
        return locateBy;
    }
}
