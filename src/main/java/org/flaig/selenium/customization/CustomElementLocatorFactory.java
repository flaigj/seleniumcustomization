package org.flaig.selenium.customization;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

public class CustomElementLocatorFactory implements ElementLocatorFactory {
    
    private final SearchContext searchContext;
    
    public CustomElementLocatorFactory(SearchContext context) {
        this.searchContext = context;
    }
    
    @Override
	public ElementLocator createLocator(Field field) {
        CustomFindBy annotation = field.getAnnotation(CustomFindBy.class);
        
        if (annotation == null) {
            // if our annotation is not present give it to selenium's defaults
            return new DefaultElementLocator(this.searchContext, new Annotations(field));
        }
        return new CustomElementLocator(this.searchContext, new CustomAnnotation(field));
    }
}