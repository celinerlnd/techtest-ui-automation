--- Tech Test Automation — UI (Sauce Labs Demo App) ---

This project focuses on UI Automation Testing for the Android App 
(https://github.com/saucelabs/my-demo-app-android) using:

- Java (JDK 17)
- Maven 3.9.11
- Appium Java Client 9.5.0
- Selenium 4.28.0 (pinned)
- Cucumber / Gherkin (BDD)
- TestNG
- IntelliJ IDEA
- Android Emulator

## 📲 Test Flow (E2E)
This UI test automates the end-to-end flow of buying a product and completing the checkout:

1. Open product catalog & select "Sauce Lab Back Packs"
2. Choose color "Blue"
3. Set quantity to 2
4. Add product to cart
5. Verify cart icon shows 2 items
6. Tap cart & review cart detail
7. Proceed to checkout
8. Login with credentials (provided)
9. Fill shipping form
10. Fill payment method form
11. Review order
12. Place order
13. Verify Checkout Complete page
14. Tap “Continue Shopping” to return to product catalog page

## Tech Stack
- **Java JDK 17** – programming language
- **Maven 3.9.11** – build & dependency management
- **Appium Java Client 9.5.0** – mobile automation
- **Selenium 4.28.0** – UI automation (pinned for compatibility)
- **Cucumber / Gherkin** – BDD feature files
- **TestNG** – test runner
- **Android Emulator** – device testing

# Environment Setup (macOS)
Make sure these already being installed:
- [Node.js & Appium](https://appium.io/)
- [Android SDK & Emulator](https://developer.android.com/studio)

## 🛠 Dependency Note
Appium Java Client 9.x masih bergantung pada `LocationContext` Selenium yang dihapus di rilis terbaru.  
Untuk mencegah error build:

```xml
<dependency>
  <groupId>io.appium</groupId>
  <artifactId>java-client</artifactId>
  <version>9.5.0</version>
  <exclusions>
    <exclusion>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
    </exclusion>
  </exclusions>
</dependency>

<dependency>
  <groupId>org.seleniumhq.selenium</groupId>
  <artifactId>selenium-java</artifactId>
  <version>4.28.0</version>
</dependency>
```

# Project Structure
android-ui-automation/
├── pom.xml
├── testng.xml
├── .gitignore
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │   └── com/demo/
│       │       ├── core/                    <-- Base & Hooks
│       │       ├── pages/                   <-- Page Object Classes
│       │       │   ├── CartPage
│       │       │   ├── CheckoutReviewPage
│       │       │   ├── LoginPage
│       │       │   ├── ProductDetailPage
│       │       │   ├── ProductsPage
│       │       │   └── ShippingPage
│       │       ├── runner/
│       │       │   └── CucumberTestRunner
│       │       └── steps/                   <-- Step Definitions
│       └── resources/
│           ├── apps/
│           │   └── mda-1.0.13-15.apk
│           └── features/
│               ├── buy_backpack.feature        <-- feature file Cucumber / Gherkin (BDD)
│               └── sanity.feature
└── target/

# How to Run the Test
1. Start Android Emulator
2. Start Appium Server 
3. Open project in IntelliJ IDEA 
4. Run:
   a. Click gutter on file Runner
   b. Click gutter on Feature file
   c. or use terminal using command "mvn test"

# Test Report
TestNG & Cucumber menghasilkan report bawaan di: target/surefire-reports/

Author
👤 @celinerlnd
🧪 UI Automation with Appium & Java
💻 macOS Monterey 12.6 | Android Emulator API 29
