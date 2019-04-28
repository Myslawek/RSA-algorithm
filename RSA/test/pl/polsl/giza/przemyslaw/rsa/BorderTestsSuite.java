/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.rsa;

import pl.polsl.giza.przemyslaw.rsa.markers.*;
import org.junit.experimental.categories.*;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Suite class used to run the tests marked with "BorderTests" markup
 * @author Przemysław Giza
 * @version 1.2
 * @since 1.2
 */

@RunWith(Categories.class)
@IncludeCategory(BorderTests.class)
@SuiteClasses(ModelTest.class)
public class BorderTestsSuite {
    
}
