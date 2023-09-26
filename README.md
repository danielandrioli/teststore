# Test Store Project - Automated GUI testing with Selenium WebDriver  
This project is part of my Selenium WebDriver learning.  
For my tests I used http://teststore.automationtesting.co.uk/ website, which is a dummy e-commerce.  
Feel free to fork this project and create additional test cases.

***
#### 👨🏻‍💻 Technology Stack:
- Programming language - **Java (JDK 19)**
- Automation framework - **Selenium WebDriver**
- Testing framework - **TestNG**  
- Project management tool - **Maven**  


#### 🎨 Design Patterns:
- **Page Object Model** (through **Page Factory**)  
- **Fluent Page**  


***
### 🧪 Tests:
- **S1: Login**  
  **T1:** Login using valid credentials then logout  
  **T2:** Login using invalid credentials should fail  
  **T3:** Verify that the password is hidden after typed and shown after the "Show" button is clicked     

- **S2: Navigation**  
  **T1:** Verify that the click on the product opens its page   
  **T2:** Verify that the add to cart button opens the 'product added' frame  
  **T3:** Verify that the 'quick view' button opens the product frame  
  **T4:** Verify that the 'continue shopping' button leaves the user on the same page  
  **T5:** Verify that the search function leaves the user on the search page  

- **S3: Product details and cart**  
  **T1:** Verify that the cart page contains the correct product information after changing variant, quantity and proceeding to checkout    
  **T2:** Verify that the promo code 20OFF gives 20% discount on the cart page  

- **S4: Search functionality**  
  Verify that the sort filter...  
  **T1:** 'by name A-Z' sort the searched product list alphabetically  
  **T2:** 'by name Z-A' sort the searched product list in reverse alphabetical order  
  **T3:** 'by price low to high' sort the searched product list in ascending price order  
  **T4:** 'by price high to low' sort the searched product list in descending price order

***
### 🤖 Examples:
**S2T4:**   

https://github.com/danielandrioli/teststore/assets/60299141/e2fd24c3-9bef-41ae-8fb2-904a453d873e

**S3T1 and S3T2:**  

https://github.com/danielandrioli/teststore/assets/60299141/032a0103-70bb-4556-afdd-739f11d29c57


***
#### ⚠️ Notes:
- In order to execute the tests, you can create an execution configuration or just open the Maven tab, click on "Execute Maven Goal" and enter `mvn test`  
- In order to change the browser, go to the testng.xml file and change the value of the parameter "browser". If you want to change it for a specific test class, just open the class tag and add another parameter tag for the browser.
- A screenshot is taken if the test fails. The output folder is \target\screenshots.
