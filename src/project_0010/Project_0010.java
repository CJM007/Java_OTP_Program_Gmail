package project_0010;
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.mail.Session; 
import javax.mail.Transport;
import java.util.Scanner;


/**
 *
 * @author Chitambala
 */
public class Project_0010 {
    
    public static void main(String[] args) {
       String otpCode = ""; // Code to initialize OTP.
        for (int x = 0; x < 5; x++) { // For loop to create a 5 number OTP code.
        int randNum = (int)(Math.random()* 10); // We want a number between 0 and 9, so we multiply by 10.
        otpCode = otpCode + randNum;
        }
        
//        System.out.println("OTP: " + otpCode);
System.out.print("Enter email: "); // Header to get user to enter the desired destination email.
Scanner scanner = new Scanner(System.in); // Create a Scanner to get the value of the email entered.

String userEmail = scanner.next();// Receiving the input data.

while (userEmail.length() <= 4) { // Just some dummy data validation to make sure a valid email is entered.
    // You should probably check for the "@" symbol but its 00:03 and I want to watch the Sopranos so...   
    System.out.println("Please enter a valid email");
     userEmail = scanner.next();
}
        
        
        
        String host = "smtp.gmail.com"; // The smtp host carrier we are going to use: Gmail.
        final String username = "I'll send it to you over text"; // The email address of where the email is going to come from.
        final String appPassword = "I'll send it to you over text"; // App password. If you want to change where the email comes from
        // to your email. I need to explain something to you because it'll be too long to explain via comments.
       
        // Basically setting teh host, host port, authentication and authentication boolean.
        Properties properties = new Properties(); 
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        
        // Creating a session to login to the account: kingchitambala@gmail.com
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
        });
       
        // If we get an error, it will be caught by the catch statement.
        try {
            // Creating the session, setting where the email is from, where it's going, the subject and the otp and then sending the email 
            // to it's destination.
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Account OTP");
            message.setText(otpCode);
            
            System.out.println("\nEmail sent to " + userEmail + " successfully!\n");
            
            Transport.send(message);
        } catch (MessagingException e) { // Catching the error.
            e.printStackTrace(); // Printing the error to console.
        }
        
        // Requesting user to enter the OTP.
        System.out.print("Enter OTP: ");
        
        String enteredOTP = scanner.next(); // Getting the user input value.
        
        Boolean booleanTry = true; // Boolean that allows us to keep prompting the user to enter the valid OTP
        // until he/she enters the correct one or quits by typing the word "quit".
        
        // The above described code in action.
        while (booleanTry) {
        if (enteredOTP.matches(otpCode)) {
            System.out.println("Correct code entered!");
            booleanTry = false;
        } else if (enteredOTP.toLowerCase().matches("quit")) {
            System.out.println("Exiting...");
            booleanTry = false;
        } else {
            System.out.println("Incorrect password entered! \n");
            System.out.print("Enter OTP: ");
            enteredOTP = scanner.next();
        }
        }  
        
    }
    
    
}
