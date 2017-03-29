PURPOSE:
This project aims to  extact the element names from the file "amazon.wsdl" and keeps 
track of the number of occurences of each element name.It also displays the element 
name that occurs most frequently in the document.


FILES:
Files necessary for executing this project:
->Driver.java
->FileProcessor.java
->StringOperations.java
->amazon.wsdl

SAMPLE OUTPUT:

bingsuns2% javac *.java
bingsuns2% java Driver amazon.wsdl
The most frequently occuring element name in this document is :
Element name ASIN Max Count  15
bingsuns2% java Driver a.txt b.txt
Only one command line arguement is permitted !
bingsuns2% java Driver 
A command line arguement should be entered
bingsuns2% java Driver a.txt 
The file entered is not present
java.io.FileNotFoundException: a.txt (No such file or directory)
        at java.io.FileInputStream.open(Native Method)
        at java.io.FileInputStream.<init>(FileInputStream.java:106)
        at java.io.FileInputStream.<init>(FileInputStream.java:66)
        at java.io.FileReader.<init>(FileReader.java:41)
        at Driver.main(Driver.java:40)
bingsuns2% javac *.java
bingsuns2% 


TO COMPILE:
Please  compile by entering the following commands:
-> javac *.java


TO RUN:
Please  execute by entering the following commands:
-> java Driver amazon.wsdl

DATA STRUCTURE USED:
For the purpose of finding the element name which is occuring most frequently in this document, I have used the LINKED LIST data structure.Linked list grows dynamically in size. Here 
we  are not aware as the number of element names in this document prior to coding. So by using a linked list which grows dynamically the space reuqired for storing the elements is 
reduced.


BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
Nishigandha Girish Dandekar

-> https://www.oracle.com/java/index.html

-> http://www.tutorialspoint.com/java/


