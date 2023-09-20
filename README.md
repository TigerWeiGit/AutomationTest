# Automated Image Search and Validation

This Java project demonstrates automated testing of image search functionality using Selenium WebDriver and TestNG. The purpose of this project is to perform image search on Baidu's website and validate the search results.

## Project Structure

The project structure includes the following files and directories:

- `ImageSearchTest.java`: The main test class that contains the test methods for image search and validation.
- `TestRunner.java`: The test runner class to execute TestNG tests programmatically.
- `testng.xml`: The TestNG configuration file specifying test suites and test classes.
- `chromedriver-win64`: The directory containing the Chrome WebDriver executable for Windows (64-bit).
- `tiantan.jpg`: The sample image file to be used for image search.
- `config.properties`: Configuration file that may contain parameters like `VISIT_RESULT` to specify which search result to visit.

## Prerequisites

Before running the tests, ensure you have the following prerequisites installed on your system:

- Java Development Kit (JDK)
- Google Chrome
- WebDriver executable for Chrome (compatible with your Chrome version)
- Apache Maven (for managing project dependencies, optional but recommended)

## Setup and Execution

Follow these steps to set up and execute the automated tests:

1. Clone or download this repository to your local machine.

2. Open the project in your preferred Java IDE , I'm using Visual Studio Code, you can also use IntelliJ IDEA or Eclipse.

3. Ensure that the following variables in `ImageSearchTest.java` point to the correct paths:
   - `RESOURCE_PATH`: Root path to the Chrome WebDriver and image file to search. Both are included in the src/mail/resources.
   - `CHROME_DRIVER`: Path to the Chrome WebDriver executable.
   - `SEARCHED_IMG`: Path to the image file to be used for the search.

4. Customize the `config.properties` file if needed, e.g., you can specify the `VISIT_RESULT` to select which search result to visit.

5. Run Test Runner (`TestRunner.java`) to execute the tests programmatically from IDE.

6. Alternatively, you can execute the tests using TestNG by running the `testng.xml` configuration file.

7. The tests will navigate to Baidu's image search, upload the specified image, validate the search results, and capture screenshots(both search result page and last visited page). The screen shot file will be in the project root directory. There are 2 sample files there in this project.

## TestNG and Test Report

The project uses TestNG for test execution and reporting. TestNG generates detailed test reports that you can view to check the test results, in the test-output\MySuite directory.

## Notes

- The provided image (`tiantan.jpg`) is a sample image used for testing. You can replace it with your own image for different tests.

- For image validation, the project checks if the keyword "天坛" (Tiantan) is present in the search result page source. In a real project, more sophisticated image comparison techniques could be used.

- The provided waiting logic uses a simple `Thread.sleep` method, which is not recommended for production tests. In practice, you should implement proper waiting strategies.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
