# auth-service
The goal of this project is to create a microservice that handles Users and the authentication process.

# TODO items
- [x] User login page
- [x] User creation page
- [x] Admin only page where I can see all users
- [x] Admin Login should lead to Admin Page
- [x] After user creation give a toast to notify the user has been created
- [x] Update @Id to be natural Ids
- [x] Handle when a user tries an existing username
- [ ] User login should lead to edit user page
- [ ] Handle user session - this should allow for user authentication for other services
- [ ] Forgot password
- [ ] Edit user page - including user thumbnail
- [ ] landing page to redirect to other service - home.html
- [ ] email verification with unique email

# Login Page
Main Login page is found at http://localhost:8080/login

The default admin user is:
user: AdminUser
password: password

A list of created users can be accessed at by Admin users only: http://localhost:8080/listUsers