package org.flaig.selenium.customization;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class SelectElementWrapperHandler implements InvocationHandler {
    private final ElementLocator locator;

    public SelectElementWrapperHandler(ElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        WebElement element;
        try {
            element = locator.findElement();
        } catch (Exception e) {
            if ("toString".equals(method.getName())) {
                return "Proxy select(element) for: " + locator.toString();
            }
            else throw e;
        }
        CustomSelect el = new CustomSelect(element);
        
        try {
            return method.invoke(el, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}