CREATE DATABASE recipe_dev;
CREATE DATABASE recipe_prod;

#Create database service accounts
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'user';
CREATE USER 'prod_user'@'localhost' IDENTIFIED BY 'user';
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'user';
CREATE USER 'prod_user'@'%' IDENTIFIED BY 'user';

#Database grants
GRANT SELECT ON recipe_dev.* to 'dev_user'@'localhost';
GRANT INSERT ON recipe_dev.* to 'dev_user'@'localhost';
GRANT DELETE ON recipe_dev.* to 'dev_user'@'localhost';
GRANT UPDATE ON recipe_dev.* to 'dev_user'@'localhost';
GRANT SELECT ON recipe_prod.* to 'prod_user'@'localhost';
GRANT INSERT ON recipe_prod.* to 'prod_user'@'localhost';
GRANT DELETE ON recipe_prod.* to 'prod_user'@'localhost';
GRANT UPDATE ON recipe_prod.* to 'prod_user'@'localhost';
GRANT SELECT ON recipe_dev.* to 'dev_user'@'%';
GRANT INSERT ON recipe_dev.* to 'dev_user'@'%';
GRANT DELETE ON recipe_dev.* to 'dev_user'@'%';
GRANT UPDATE ON recipe_dev.* to 'dev_user'@'%';
GRANT SELECT ON recipe_prod.* to 'prod_user'@'%';
GRANT INSERT ON recipe_prod.* to 'prod_user'@'%';
GRANT DELETE ON recipe_prod.* to 'prod_user'@'%';
GRANT UPDATE ON recipe_prod.* to 'prod_user'@'%';
