# DigiLibrary
### An application for sharing what you are reading.

I developed an interactive website using Java and Spring framework that allows a user to create an account and store a personal “library” of books they have read or would like to read in the future. Additionally, users are able to “follow” other users and view their library to discover more books to read. In this current version, users are also able to add books to the database.


## Use

#### Home Page
![1](https://user-images.githubusercontent.com/101597675/194771591-24bbb3be-00c2-419a-b606-28c10e3fae40.PNG)



#### Login/Registration
![2](https://user-images.githubusercontent.com/101597675/194771609-8fcb76b9-3a0d-4e6f-80cf-d20ce5fb1314.PNG)
![3](https://user-images.githubusercontent.com/101597675/194771623-a3a91bb0-0747-4a6d-a4a4-b7e4afc64ed7.PNG)



#### Main Page
![4](https://user-images.githubusercontent.com/101597675/194771713-ce4d260e-ac59-4e63-af45-fe722cc0790a.png)



#### View One Book
![5](https://user-images.githubusercontent.com/101597675/194771780-751b0c81-e630-4a23-9a1b-d71faa13916a.PNG)



#### User's Profile
![6](https://user-images.githubusercontent.com/101597675/194771850-bce48b83-fe67-4bde-ad47-9831c060e8a9.PNG)




#### Other User's Profile
![7](https://user-images.githubusercontent.com/101597675/194771867-b80d769d-1a90-424e-92f4-d8ad4d7ff8a7.PNG)



#### Search
![8](https://user-images.githubusercontent.com/101597675/194772127-79726274-f0c3-4534-8a52-36f2e01b13e9.PNG)



#### Add Book
![9](https://user-images.githubusercontent.com/101597675/194772140-73564e74-d857-4e28-b925-0f9cc27e0209.PNG)



## Technical Stuff
- Application built end-to-end in Java using the Spring Framework
- Designed a database with MySQL and implemented multiple one-to-many and many-to-many relationships 
- Utlizied Bcrypt for password-hashing
- Used Http Session to store user's identity
- Incorporated a mix of Bootstrap styling and personal CSS
- Previously deployed using AWS/EC2, will deploy again once new features are added (see below)

#### Running the Application
You can run this application as a Spring Boot app using Spring Tool Suite, but the database is not public at this time. 

## Future Additions
I am currently working on implementing the Google Books API. This will basically get rid of the need to enter books in manually, avoiding "bad" entries. 
