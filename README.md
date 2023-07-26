# Movie Booking System

The Movie Booking System is a backend application for booking a movie show at a particulat theatre, developed using Spring Boot and MySQL. It enables customers to Sign Up, Login, make a Booking for a Show. It uses Bcrypt for encrying the passwords and then stores in the Database.

## Prerequisites

- JDK 11 or higher
- Maven
- MySQL Database
- IntelliJ IDEA Ultimate

## Installation

1. Clone the repository: `git clone <repository_url>`
2. Navigate to the project directory: `cd bookmyshowjuly23`

## Configuration

1. Configure MySQL Database:
   - Create a new database schema for the application in mySQL Workbench.
   - Update the database configuration in `src/main/resources/application.properties` file with your MySQL connection details like url, password.

2. Open the downloaded project in IntelliJ IDEA Ultimate
   
3. Build and Run the application:
   - Click on the green arrow button to run the application.

4. Test the Sign Up feature
   - You can create DTOs for testing as: In the 2nd and 3rd line change the Email and Password, run the application and see in the "users_table" table created in the Database. You will see a new User with the given Email and Password getting added.
   - SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
   - signUpRequestDto.setEmail("ajaz@gmail.com");
   - signUpRequestDto.setPassword("password");
   - SignUpResponseDto response = userController.signUp(signUpRequestDto);
   - System.out.println(response.getResponseStatus());

## Usage

### Customer Features:

1. **Sign Up:**
   - Customers can Sign Up by providing their details, such as email and password.
   - To store the password, it uses **Bcrypt Encryption Algorithm** to generate a Hash which is stores in the Database.
2. **Login:**
   - Customers can Login with their email and password.
   - To verify the user, it uses **Bcrypt Encryption Algorithm** to match with the User Hash present in the Database and the given password.

2. **Movie Show Booking:**
   - Customers can book a Movie Show in a particular Theatre by providing some deatils like: All Seats selected, showId and userId.
   - The system checks the status of all the Seats for availability provided by the User.
   - If all the seats are available, system calculates the price for the selected seats and returns the total amount to be paid.
   - The system handles **concurrency** also by allowing only 1 User to book the selected seats if there are some other users trying to book some common seats.


## Contributing

Contributions to the **Movie Booking System** are welcome. If you find any issues or have suggestions for improvement, please create a new issue or submit a pull request.
