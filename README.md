SpatialData
===========

Java Program implements a spatial data system
Please download the data folder called restaurant_data from moodle. The folder contains 9 
text files that are named as restaurants_group_i.txt where restaurants_group_i.txt file 
contains all the restaurants in i-th group and i = 0, 1, 2, 3, 4, 5, 6, 7, 8. Set R includes 54 
restaurants that are separated and grouped into these 9 files. Each line in a 
restaurants_group_i.txt file contains the information of a restaurant in following format: 
rid x_cor y_cor 
, where rid is the unique restaurant id, and x_cor is the x coordinate of the restaurant, and 
y_cor is the y coordinate of the restaurant. rid is integer in [0, 53], while x_cor and y_cor are 
real numbers in [0, 1000]. The restaurants_group_i.txt files are tab-delimited, which means 
in each line of the files, rid, x_cor, and y_cor are separated by a tab. 
(1). Write a program to calculate the MBRs of all 9 restaurant groups and output all the 
MBRs to a text file, named group_mbrs.txt, each line of which contains the MBR of a group 
in following format: i i
ll x i
ll y i
ur x i
ur y 
, where i means i-th group (i = 0, 1, 2, 3, 4, 5, 6, 7, 8), and i
ll x and i
ll y are the x and y 
coordinates of the lower left corner of i-th group’s MBR respectively. Similarly, i
ur x and i
ur y 
are the x and y coordinates of upper right corner of i-th group’s MBR respectively. 
All the 9 lines in group_mbrs.txt should be sorted by group id i, which means that the 1st line 
of group_mbrs.txt should be the 0-th group’s MBR in above format, and the 2nd line should 
be the 1-th group’s MBR in above format, and so on. 
Please put all your code for this question into a single file, called GetMBRs.cpp (if you are 
using C++), or GetMBRs.java (if you are using JAVA). 
(2) Assume two query points: ( , ) 1 1 1 q = x y and ( , ) 2 2 2 q = x y . You are at 1 q and your friend is 
at 2 q , and you and your friend want to find a place among all the 54 restaurants in R as a 
meeting point to meet as soon as possible. It is known that you and your friend walk in the 
same speed. Euclidean distance is used as distance metric. The best meeting point for you 
and your friend should be the restaurant r∈Rthat minimizesmax{ ( , ), ( , )} 1 2 dist r q dist r q . 
Suppose the number of accesses to restaurants_group_i.txt files is regarded as the cost of 
your program, please design a C++/JAVA program that produces correct result efficiently. 
(Marks will be deducted if the program accesses more files than necessary.) 
The output of program should be two integers separated by tab. The first integer is the chosen 
restaurant rid, and the second integer is number of accesses to restaurants_group_i.txt files. 
Test cases: (a) (720,120) q1 = (215,820) q2 = ; (b) (320,120) q1 = (450,680) q2 = 
Please put all your code of this question into a single file, called GetMeetingPoint.cpp (if you 
are using C++), or GetMeetingPoint.java (if you are using JAVA). 
 
(Hint: Use the MBRs in file group_mbrs.txt to derive some bound for pruning.)
