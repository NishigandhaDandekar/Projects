CS542 Design Patterns
Spring 2016
PROJECT 3 README FILE

Due Date: Tuesday, March 22, 2016
Submission Date: Tuesday, March 22, 2016
Grace Period Used This Project: 0 days
Grace Period Remaining: N.A.
Author: Nishigandha Girish Dandekar
e-mail: ndandek1@binghamton.edu

PURPOSE:
The purpose of this project is to implement the Observer Pattern.
This project uses the Observer Pattern to update the value of the backup 
listener nodes when the value of their subject node is updated.
It also applies the concept of Filters, where the backup1 node is 
updated only if the update value is even and backup2 is updated 
only if the update value is odd.

PERCENT COMPLETE:
I believe I have completed 100% of this project

PARTS THAT ARE NOT COMPLETE:
None

BUGS:
None

FILES:
Files necessary for executing this project:
->Driver.java
->FileProcessor.java
->BST.java
->BSTBuilder.java
->OddEvenFilterI.java
->EvenFilterImpl.java
->OddFilterImpl.java
->ObserverI.java
->SubjectI.java
->Node.java
->Data.java
->bstInput.txt
->DisplayElementI.java


SAMPLE OUTPUT:
bingsuns2% ant -buildfile src/build.xml all
Buildfile: src/build.xml

prepare:

studentRecordsBackup:

compile_all:

all:

BUILD SUCCESSFUL
Total time: 1 second
bingsuns2% ant -buildfile src/build.xml run -Darg0=bstInput.txt -Darg1=3
Buildfile: src/build.xml

jar:

run:
     [java] Original Tree
     [java] 1
     [java] 2
     [java] 3
     [java] 5
     [java] 7
     [java] 8
     [java] 22
     [java] Backup 1
     [java] 1
     [java] 2
     [java] 3
     [java] 5
     [java] 7
     [java] 8
     [java] 22
     [java] Backup 2
     [java] 1
     [java] 2
     [java] 3
     [java] 5
     [java] 7
     [java] 8
     [java] 22
     [java] Bnumber in listener 4
     [java] Bnumber in listener 5
     [java] Bnumber in listener 6
     [java] Bnumber in listener 8
     [java] Bnumber in listener 10
     [java] Bnumber in listener 11
     [java] Bnumber in listener 28
     [java] Original Tree
     [java] 4
     [java] 5
     [java] 6
     [java] 8
     [java] 10
     [java] 11
     [java] 28
     [java] Backup 1
     [java] 1
     [java] 2
     [java] 3
     [java] 5
     [java] 7
     [java] 8
     [java] 22
     [java] Backup 2
     [java] 4
     [java] 5
     [java] 6
     [java] 8
     [java] 10
     [java] 11
     [java] 28
     [java] Sum of all nodes in the original BST is 72
     [java] Sum of all nodes in Backup1 BST is 48
     [java] Sum of all nodes in the Backup2 BST is 72

BUILD SUCCESSFUL
Total time: 2 seconds
bingsuns2% 



TO COMPILE:
Please  compile by entering the following commands:
-> ant -buildfile src/build.xml all


TO RUN:
Please  execute by entering the following commands:
->  ant -buildfile src/build.xml run -Darg0=bstInput.txt -Darg1=3


DATA STRUCTURE USED:
An ArrayList has been used to store the observer and the corresponding filter for each subject node.
This is because an ArrayList can grow in size dynamically to add more observers or 
filters for the subject. 

EXTRA CREDIT:
N/A

BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
Nishigandha Girish Dandekar

-> https://www.oracle.com/java/index.html

-> http://www.tutorialspoint.com/java/

ACKNOWLEDGEMENT:

