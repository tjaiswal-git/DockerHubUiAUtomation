# DockerHubUiAUtomation

To run this project

1) Currently using ChromeDriver 93.0.4577.63 it should be the same chrome browser version , if you don't have same chrome browser version then you need to download same 
chrome driver from download site.
https://chromedriver.chromium.org/downloads

2) Selenium 3.4 is using for this project

3) Run this DockerHUBWebTest.java
4) Another way to run through maven command
     mvn clean install -Dmaven.native.skip=true
     
5) to generate allure report
     download binary from https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.15.0/
     
     run this command 
     
     allure.bat serve "path_allure-results"
