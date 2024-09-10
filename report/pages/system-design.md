# System design

## Technologies used:
* **Languages used:** JAVA SE
* **Big APIs:** JDBC 
* **Tools:**
    - **Project management:** Notion
    - **Version control:** git
    - **Design:** no tools for design because it is a console application
* **Database:** I used mySql database for working with JDBC API.
* **Design database:**
    !["Database Entity-Relationship Diagram (ERD)"](../img/ERD.png)
    
## Architecture and design
### **System Architecture**

```mermaid
    flowchart LR
BPS(Basic project structure)
MP(Main Panel)
GI(Git integration)
LA(Login as admin)
LE(Login as employee)
LU(Login as user)
RP(Register panel)
RA(Register account)
ET(Execute tasks)

BPS --> GI <--> MP

MP <--> RP
MP -- DAO --> LA & LE & LU
AP & EP & UP ---> MP

RP -- DAO --> RA
RA --> RP
   
LA --> AP(Admin panel)
LU --> UP(User panel)
LE --> EP(Employee panel)

AP & EP & UP -- DAO ---> ET
```

### Design patterns:
* __Singleton pattern:__ Used in MySql.java for handling centralized connection with the database.
* __DAO pattern:__ The UserDAO interface, with other DAO interfaces that may be in the project, will abstract database operations relating to different entities—in this case, users, employees, and transactions.
