CS542 Design Patterns
Spring 2016
PROJECT 5 README FILE

Due Date: Sunday, May 8, 2016
Submission Date: Sunday, May 8, 2016
Grace Period Used This Project: 0 days
Grace Period Remaining: N.A.
Author: Nishigandha Girish Dandekar
e-mail: ndandek1@binghamton.edu


PURPOSE:
The purpose of this project is to crate a serialization library for the purpose of 
serialization and deserialization of objects.This project also implments the stategy pattern 
which allows the extension of the serialization library to serialize and deserialize objects in other formats like Json

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
->RestoreI.java
->StoreI.java
->StoreRestoreI.java
->MyAllTypesFirst.java
->MyAllTypesSecond.java
->ProxyCreator.java
->RandomGenerator.java
->SerializableObject.java
->DserStrategy.java
->SerStrategy.java
->SerializeTypes.java
->StoreRestoreHandler.java
->XMLDeserialization.java
->XMLSerialization.java

SAMPLE OUTPUT:
bingsuns2% ant -buildfile src/build.xml all
Buildfile: /import/linux/home/ndandek1/Nishigandha_Dandekar_assign5/genericCheckpointing/src/build.xml

prepare:

genericCheckpointing:
    [javac] /import/linux/home/ndandek1/Nishigandha_Dandekar_assign5/genericCheckpointing/src/build.xml:48: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds

compile_all:

all:

BUILD SUCCESSFUL
Total time: 0 seconds
bingsuns2%  ant -buildfile src/build.xml run -Darg0=serdser -Darg1=1 -Darg2=output.txt
Buildfile: /import/linux/home/ndandek1/Nishigandha_Dandekar_assign5/genericCheckpointing/src/build.xml

jar:

run:
     [java] 0 objects mismatched

BUILD SUCCESSFUL
Total time: 1 second
bingsuns2% 



TO COMPILE:
Please  compile by entering the following commands:
-> ant -buildfile src/build.xml all


TO RUN:
Please  execute by entering the following commands:
->  ant -buildfile src/build.xml run -Darg0=serdser -Darg1=1 -Darg2=output.txt


DATA STRUCTURE USED:
Nil

EXTRA CREDIT:
N/A

BIBLIOGRAPHY:

This serves as evidence that we are in no way intending Academic Dishonesty.
Nishigandha Girish Dandekar

-> https://www.oracle.com/java/index.html

-> http://www.tutorialspoint.com/java/

ACKNOWLEDGEMENT:

