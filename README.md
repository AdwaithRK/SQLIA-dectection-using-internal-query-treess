# SQLIA-dectection-using-internal-query-treess
Java coded program for converting Postgres logs into feature vectors then classifying it as malicious or non malicious using svm



I started working on working a project that would be able to detect SQLIAttacks by going thought database logs. I have throughthly 
checked the net and have found many paper concerning the topic. Here what I have done is build a crude way to convert query log(obtained-
from postgres database) to classifiable vectors. I have tested only for select statement(can be done for insert,modify..etc), and
have given a highest accuracy of 94.3% using 10 cross validation in svm, which is really not so much of a great accuracy to achive.
 
 All are welcome to improve on this, not so great code.
 
 I have used movielens data for the postgres database (PostgreSQL) 9.6.7
 
 The following command is used for downloading the movielens database of users
createdb movielens
curl https://raw.githubusercontent.com/ankane/movielens.sql/master/movielens.sql | psql -d movielens


postgresql.conf  file has been edited to to get the query-tree in the required format.(you may copy and replace it)


The floder website contains a rudementry website coded in html and php used for generating training data. The file create.php is used for 
creating username and password for all users in the movielens database.

Used a tool known SQLMAP for injecting malicious into the website for generating training set malicious query :
https://github.com/sqlmapproject/sqlmap3


weka.jar and weka-src.jar are need to included for using svm classifications

# Things I wanted to but couldn't do
1.Use histogram model available in Postgres Sql.
2.Add query lenght as 118th feature in the vector used for classification
3.Now the code has to be run separately for malicious and non-malicious queries but can be run parallely and combined.

