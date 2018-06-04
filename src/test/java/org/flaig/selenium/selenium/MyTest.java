package org.flaig.selenium.selenium;

import org.flaig.selenium.customization.CustomFindBy;
import org.flaig.selenium.customization.SelectInput;

public class MyTest {

	@CustomFindBy(format = "//label[text()='%s']/following-sibling::select", param = "Select Gender:")
	public SelectInput drpGender;
	
	
	
}
