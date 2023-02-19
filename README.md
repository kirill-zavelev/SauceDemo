<h1>This is project for SauceDemo automation tests</h1>

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