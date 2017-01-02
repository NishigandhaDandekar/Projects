CS542 Design Patterns
Spring 2016
PROJECT 2 README FILE

Due Date: Monday, February 29, 2016
Submission Date: Monday, February 29, 2016
Grace Period Used This Project: 0 days
Grace Period Remaining: N.A.
Author: Nishigandha Girish Dandekar
e-mail: ndandek1@binghamton.edu


PURPOSE:
The purpose of this project is to implement the multithreading and the 
synchronization of these multiple threads in java. 

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
->CreateWorkers.java
->WorkerThread.java
->Results.java
->IsPrime.java
->StdoutDisplayInterface.java
->Logger.java
->StoreData.java
->input.txt

SAMPLE OUTPUT:
bingsuns2% ant -buildfile src/build.xml all
Buildfile: src/build.xml

prepare:

primeThreads:

compile_all:

all:

BUILD SUCCESSFUL
Total time: 1 second
bingsuns2% ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=5 -Darg2=0
Buildfile: src/build.xml

jar:

run:
     [java] The sum of all prime numbers is : 29237

BUILD SUCCESSFUL
Total time: 2 seconds
bingsuns2% 

TO COMPILE:
Please  compile by entering the following commands:
-> ant -buildfile src/build.xml all


TO RUN:
Please  execute by entering the following commands:
-> ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=5 -Darg2=0


DATA STRUCTURE USED:
Multiple threads which access the input file and store the prime numbers in the data structure, 
thus there are multiple threads accessing the data structure so Vector data structure is used since it is thread safe 
and will not result in synchronization problems.Also as the size of the input.txt file is unknown so 
vector is a suitable option since it can grow dynamically. 

EXTRA CREDIT:
N/A

BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
Nishigandha Girish Dandekar

-> https://www.oracle.com/java/index.html

-> http://www.tutorialspoint.com/java/

ACKNOWLEDGEMENT:

