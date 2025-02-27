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

package io.appium.java_client.safari;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.remote.http.HttpClient;

import java.net.URL;

/**
 * GeckoDriver is an officially supported Appium driver
 * created to automate mobile Safari browser. The driver uses W3C
 * WebDriver protocol and is built on top of Apple's safaridriver
 * server. Read https://github.com/appium/appium-safari-driver
 * for more details on how to configure and use it.
 *
 * @since Appium 1.20.0
 */
public class SafariDriver extends AppiumDriver {
    private static final String PLATFORM_NAME = Platform.IOS.toString();
    private static final String AUTOMATION_NAME = AutomationName.SAFARI;

    public SafariDriver(HttpCommandExecutor executor, Capabilities capabilities) {
        super(executor, ensurePlatformAndAutomationNames(capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(URL remoteAddress, Capabilities capabilities) {
        super(remoteAddress, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(URL remoteAddress, HttpClient.Factory httpClientFactory, Capabilities capabilities) {
        super(remoteAddress, httpClientFactory, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(AppiumDriverLocalService service, Capabilities capabilities) {
        super(service, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(AppiumDriverLocalService service, HttpClient.Factory httpClientFactory,
                        Capabilities capabilities) {
        super(service, httpClientFactory, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(AppiumServiceBuilder builder, Capabilities capabilities) {
        super(builder, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(AppiumServiceBuilder builder, HttpClient.Factory httpClientFactory,
                        Capabilities capabilities) {
        super(builder, httpClientFactory, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(HttpClient.Factory httpClientFactory, Capabilities capabilities) {
        super(httpClientFactory, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    /**
     * Creates a new instance based on the given ClientConfig and {@code capabilities}.
     * The HTTP client is default client generated by {@link HttpCommandExecutor#getDefaultClientFactory}.
     * For example:
     *
     * <pre>
     *
     * ClientConfig clientConfig = ClientConfig.defaultConfig()
     *     .baseUri(URI.create("WebDriver URL"))
     *     .readTimeout(Duration.ofMinutes(5));
     * SafariOptions options = new SafariOptions();
     * SafariDriver driver = new SafariDriver(clientConfig, options);
     *
     * </pre>
     *
     * @param clientConfig take a look at {@link ClientConfig}
     * @param capabilities take a look at {@link Capabilities}
     *
     */
    public SafariDriver(ClientConfig clientConfig, Capabilities capabilities) {
        super(clientConfig, ensurePlatformAndAutomationNames(
                capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }

    public SafariDriver(Capabilities capabilities) {
        super(ensurePlatformAndAutomationNames(capabilities, PLATFORM_NAME, AUTOMATION_NAME));
    }
}
