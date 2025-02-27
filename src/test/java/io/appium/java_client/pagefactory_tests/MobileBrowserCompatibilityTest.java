/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.java_client.pagefactory_tests;

import static java.time.Duration.ofSeconds;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MobileBrowserCompatibilityTest {

    private WebDriver driver;

    private AppiumDriverLocalService service;

    @AndroidFindBy(className = "someClass")
    @AndroidFindBy(xpath = "//someTag")
    private RemoteWebElement btnG; //this element should be found by id = 'btnG' or name = 'btnG'

    @FindBy(name = "q")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/someId\")")
    private WebElement searchTextField;

    @AndroidFindBy(className = "someClass")
    @FindBys({@FindBy(className = "r"), @FindBy(tagName = "a")})
    private List<WebElement> foundLinks;

    /**
     * The setting up.
     */
    @BeforeEach
    public void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options()
                .withBrowserName(MobileBrowserType.BROWSER)
                .setDeviceName("Android Emulator");
        driver = new AndroidDriver(service.getUrl(), options);
        //This time out is set because test can be run on slow Android SDK emulator
        PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(5)), this);
    }

    /**
     * finishing.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (service != null) {
            service.stop();
        }
    }

    @Test
    public void test() {
        driver.get("https://www.google.com");

        searchTextField.sendKeys("Hello");
        btnG.click();
        Assertions.assertNotEquals(0, foundLinks.size());
    }

}
