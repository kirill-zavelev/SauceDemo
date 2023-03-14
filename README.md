<h1>This is project for SauceDemo automation tests</h1>

<h2>Maven commands</h2>
<ol type="1">
   <li>Check available updates of libraries</li>
    <ul>
        <li><b>Command:</b> "mvn versions:display-dependency-updates"</li>
        <li><b>Output:</b> [INFO] The following dependencies in Dependencies have newer versions:
[INFO]   org.seleniumhq.selenium:selenium-java ................. 4.8.0 -> 4.8.1
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.904 s
[INFO] Finished at: 2023-02-26T10:41:03+03:00
[INFO] ------------------------------------------------------------------------</li>
    </ul>
    <li>Update all of the libraries
        <ul>
            <li><b>Command:</b> "mvn versions:display-dependency-updates"</li>
            <li><b>Output:</b> [INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.965 s
[INFO] Finished at: 2023-02-26T10:46:56+03:00
[INFO] ------------------------------------------------------------------------
        </ul>
    </li>
    <li>Run all of the tests
        <ul>
            <li><b>Command:</b> "mvn clean test"</li>
            <li><b>Output:</b> [INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 35.407 s - in TestSuite
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  43.003 s
[INFO] Finished at: 2023-02-26T10:55:26+03:00
[INFO] ------------------------------------------------------------------------</li>
        </ul>
    </li>
    <li>Run 2 tests from com.saucedemo.CartTest class
        <ul>
            <li><b>Command:</b> "mvn -Dtest=com.saucedemo.CartTest#checkProductsAreAddedToCart+checkRemoveProductsFromCart test"</li>
            <li><b>Output:</b> [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.855 s - in com.saucedemo.CartTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.906 s
[INFO] Finished at: 2023-02-26T11:02:45+03:00
[INFO] ------------------------------------------------------------------------</li>
        </ul>
    </li>
    <li>Run 1 test with parameter set in command
        <ul>
            <li><b>Command:</b> "mvn clean test -Dtest=com.saucedemo.CheckOutTest#checkCheckoutFormWithValidData -Dproduct="Sauce Labs Onesie""</li>
            <li><b>Output:</b> [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.912 s - in com.saucedemo.CheckOutTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.477 s
[INFO] Finished at: 2023-02-26T11:37:56+03:00
[INFO] ------------------------------------------------------------------------</li>
        </ul>
    </li>
</ol>

<h2>Check-list</h2>

<h3>Login</h3>
<ol type="1">
   <li>Check login with all acceptable usernames</li>
   <li>Check login with valid username, unexisting password</li>
   <li>Check login with valid username, empty password</li>
   <li>Check login with unexisting username, valid password</li>
   <li>Check login with empty username, valid password</li>
   <li>Check login with unexisting username, unexisting password</li>
   <li>Check login with empty username, empty password</li>
</ol>

<h3>Product page</h3>
<ol type="1">
   <li>Check that there are 6 products in Product Page</li>
   <li>Check all products have names and prices</li>
   <li>Check that product(s) can be added to Cart by clicking "Add to cart"</li>
   <li>Check that product(s) can be removed from Cart by clicking "Remove"</li>
   <li>Check that products can be sorted by Alphabet using filter</li>
   <li>Check that products can be sorted by price using filter</li>
</ol>

<h3>Left-side Menu</h3>
<ol type="1">
   <li>Check that menu can be opened from Product Page, Cart Page and Checkout Page</li>
   <li>Check that menu has items:
    <ul>
        <li>ALL ITEMS</li>
        <li>ABOUT</li>
        <li>LOGOUT</li>
        <li>RESET APP STATE</li>
    </ul>
    </li>
   <li>Check that clicking to ALL ITEMS leads to Product Page</li>
</ol>

<h3>Cart page</h3>
<ol type="1">
   <li>Check that added product(s) are displayed with their names and prices in Cart</li>
   <li>Check that Product Page can be opened by clicking "Continue Shopping" btn</li>
   <li>Check that product(s) can be removed from Cart by clicking "Remove" btn</li>
   <li>Check that Checkout page is opened by clicking "Checkout" btn</li>
</ol>

<h3>Checkout page</h3>
<ol type="1">
   <li>Check that </li>
   <li>Check that Checkout page has form with the following fields:</li>
    <ul>
        <li>First Name</li>
        <li>Last Name</li>
        <li>Zip/Postal Code</li>
    </ul>
   <li>Check that it is impossible to continue with empty First Name</li>
   <li>Check that it is impossible to continue with empty Last Name</li>
   <li>Check that it is impossible to continue with empty Zip/Postal Code</li>
   <li>Check that it is possible to continue with filled First Name, Last Name and Zip/Postal Code</li>
   <li>Check that click at Cancel btn leads to Cart page</li>
   <li>Check that click at Continue btn with all filled fields leads to Checkout:Overview page</li>
</ol>

<h3>Checkout: Overview page</h3>
<ol type="1">
   <li>Check that added product(s) are displayed with their names and prices</li>
   <li>Check that "Payment Information" is displayed</li>
   <li>Check that "Shipping Information" is displayed</li>
   <li>Check that "Item total:${value}" is displayed</li>
   <li>Check that "Tax:${value}" is displayed</li>
   <li>Check that "Total:${item total value + tax value}" is displayed</li>
   <li>Check that click at Cancel btn leads to Cart page</li>
   <li>Check that click at Finish btn leads to Thank you page</li>
</ol>

<h3>Thank you page</h3>
<ol type="1">
   <li>Check that "THANK YOU FOR YOUR ORDER" message is displayed</li>
   <li>Check that "Your order has been dispatched, and will arrive just as fast as the pony can get there!" message is displayed below "THANK YOU FOR YOUR ORDER" message</li>
   <li>Check that clicking "BACK HOME" btn leads to Product Page</li>
</ol>