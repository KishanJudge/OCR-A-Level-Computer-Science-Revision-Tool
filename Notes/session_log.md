#Session 1:
- For this session I need to begin designing the backend of the application. The SQLite database will need to hold all the relevant information that will be used across the system, crucially in the Quiz System where the user's performance will need to be used in the question selection algorithm that specifically targets their weakest targets and aims for improvement of performance.
- Once I begin designing the question selection algorithm I will have a clearer idea of exactly what information is needed from the user to accurately and efficiently provide them with questions that will prove the most effective in improving their knowledge and results. Therefore, following my design plan, I should setup the database with the relevant information that I require up to this stage, and design it so that later in production the other needed fields can be addede to the database.
- First, I need to research database design. I began with the simple article from Geeks for Geeks: "https://www.geeksforgeeks.org/dbms/database-design-ultimate-guide/"
- Requirements Analysis: I need two primary things, 1. User Account information, allowing them to login and logoff, and allowing an anchor for the user information to be stored in other tables, 2. Performance Information, which provides the details from the user's quiz activity that will be used in the question selection algorithm.
- I don't yet know the exact requirements for the performance information, so to begin with I will outline only what I need for the User Account Information with an Entity Relationship Diagram.
- I should bear in mind and make performance testing plans to ensure the database works correctly throughout the project.
- [![alt text](<Image Bank/Users_table_ERD.png>)]
- Once I'd designed the ERD, I went reminded myself of how to write the code to setup the database connection and create tables using the sqlite java tutorial page: "https://www.sqlitetutorial.net/sqlite-java/"

#Session 2:
- After some thought I realised that I would also need to store my question bank and all relevant info for the questions as well as the performance data and user account data. This will be important as It will also hold information on the topic the question falls under, which will be needed with the topic selection option.
- For the Question Bank table, I thought that I would be able to more clearly outline what information will be needed even at this stage without having written any questions. To do this, I referred to my analysis and came up with the following required information: 1. Question topic, 2. The Question Itself, 3. The Answer Options, 4. The Correct Answer, 5. Question Feedback. These can be changed or added to at a later date, but for now these are satisfactory.
- Upon writing the code for the question bank table, I realised that SQLite did not have a native array type, which i'd need to store the answer options, so I had to figure a work around for this. To resolve, I decided to create another table solely to store the answer options. Each row would be a different option, and it would be tied to the question via a question_id column, which would be a primary key in the question bank table, and a foreign key in the answer options table. I decided to add a column for the answer option index, and for the answer option itself.
- Written the tables, need to test them at home with DB Browser for SQLite.
- Next, I will the application will need to be able to interact with the database. User actions from the frontend should be able to retrieve from the database, write to it, and any other needed operations. 

#Session 3:
- Today I am starting with creating the repository layer, I will begin with the user repository, and will need to define basic functions such as creating a new user, editing user information, deleting users and such. I reminded myself of correct structure using this webpage: "https://softwaresystemdesign.com/low-level-design/layered-code-structure/"

#Session 4:
- back to the user repository, finished creating all functions i can predict that I will need at this stage, that includes adding users, get user information, deleting users, and editing user information.
- Now to the user Service
