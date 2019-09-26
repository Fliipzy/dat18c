# **Chat Application Exercise**
![chatapp-pic](https://i.imgur.com/tzlRx0V.png)

## **Launch options (Windows only so far)**
### **1.** Import into preferred IDE and debug.

### **2.** Generate class files and run the .bat files.

## **Communications Protocol**
## Server -> Client
J_OK
- Tells client that autentication request was successful.

J_ER <<*error_message*>>
- Tells client whenever a request hasn't been handled correctly.

LIST <<*name1 name2 name3 ...*>>
- List of all active users. Is sent each time a client connects/disconnects.
    
## Client -> Server
JOIN <<*user_name*>> 
- Sends authentication request to server.

DATA <<*message*>> 
- Sends message to be broadcasted to everyone

QUIT 
- Tells the server that client is closing connection.

