# Food-Delivery-Java--Uni

Libraries used:
<ul>
  <li>Lombok for @Getter and @Setter (also tried to use @Builder but had some buggs)</li>
</ul>
Services Available:
<ul>
  <li>Login as User -> View Menu, Order, Log Out, Delete Account </li>
  <li>Login as Driver -> Complete Order, View Expected Salary, Log Out, Delete Account</li>
  <li>Login as Admin -> View list of drivers/ users/ locals/ orders, Change local details, Add Local, Log Out</li>
  <li>Sign up as User</li>
  <li>Sign up as Driver</li>
</ul>
<h4>Collections Used:</h4>

<li><b>ArrayList</b> for keeping hold of Users, Drivers, Admins, Products</li>
<li><b>Set</b> for keeping hold of Locals</li>
<li><b>Map</b> for keeping hold of an order's Product<->Qunatity</li>

<h3>Models Folder:</h3>
accont:
Account-> Admin, User, Driver
<br>
local:
Local, Menu, Product
<br>
order:
Order
<br>
Company
<br>
Coordinate (x,y)

<h3>Services:</h3>
Basic Service (available to anyone who just ran the app) -> UserService (Users), DriverService (Drivers), Admin Service (Admins)
