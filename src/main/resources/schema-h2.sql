drop table if exists wallet;
drop table if exists payment;
drop table if exists node;
drop table if exists bank_detail;
drop table if exists profile;
drop table if exists users_authorities ;
drop table if exists auth_detail  ;
drop table if exists authorities ;

CREATE TABLE bank_detail (
  id INT PRIMARY KEY AUTO_INCREMENT,
  account_number VARCHAR(25) NOT NULL,
  bank_name varchar(50),
  ifsc_code varchar(25),
  bank_address varchar(225),
  pan varchar(25),
  image varchar(25),
  image_data mediumblob,
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL
);


CREATE TABLE profile (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(25) NOT NULL,
  last_name VARCHAR(25),
  guardian varchar(25),
  gender varchar(5),
  dob  datetime,
  occupation varchar(20),
  city varchar(20),
  pin_code varchar(20),
  email varchar(25),
  phone varchar(10),
  aadhar varchar(20),
  address varchar(100),
  image varchar(25),
  image_data mediumblob,
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL
);

CREATE TABLE authorities (
  id        INT PRIMARY KEY AUTO_INCREMENT,
  authority_name  VARCHAR(100) not null,
  authority_description VARCHAR(225),
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL
);

CREATE TABLE auth_detail (
  id           INT PRIMARY KEY AUTO_INCREMENT,
  user_name    VARCHAR(100) not null,
  password     VARCHAR(255) not null,
  is_enabled   BOOLEAN default true,
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL
);

CREATE TABLE users_authorities (
  user_id    INT not null,
  auth_id    INT not null,
  FOREIGN KEY (auth_id) REFERENCES authorities (id),
  FOREIGN KEY (user_id) REFERENCES auth_detail (id)
  
);

CREATE TABLE node (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  position VARCHAR(5) NOT NULL,
  left_node  BIGINT ,
  right_node BIGINT,
  parent_node BIGINT,
  profile INT,
  auth_detail INT,
  bank_detail INT,
  child_count INT DEFAULT 0,
  accepted_tnc TINYINT DEFAULT 0,
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL,
  FOREIGN KEY (left_node) REFERENCES node (id),
  FOREIGN KEY (right_node) REFERENCES node (id),
  FOREIGN KEY (parent_node) REFERENCES node (id),
  FOREIGN KEY (profile) REFERENCES profile (id),
  FOREIGN KEY (auth_detail) REFERENCES auth_detail (id),
  FOREIGN KEY (bank_detail) REFERENCES bank_detail (id)
);

CREATE TABLE payment (
  id INT PRIMARY KEY AUTO_INCREMENT,
  mode VARCHAR(25) NOT NULL,
  date datetime,
  trans_no varchar(25),
  other varchar(100),
  image varchar(25),
  image_data mediumblob ,
  node BIGINT,
  amount float ,
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL,
  FOREIGN KEY (node) REFERENCES node (id)
);

CREATE TABLE wallet (
  id INT PRIMARY KEY AUTO_INCREMENT,
  type varchar(10) not null,
  node BIGINT,
  detail varchar(100) not null,
  amount float not null,
  trans_type varchar(10) not null,
  trans_date datetime not null,
  trans_status varchar(10),
  created_on  DATETIME NOT NULL,
  updated_on  DATETIME NOT NULL,
  created_by  VARCHAR(10) NOT NULL,
  updated_by  VARCHAR(10) NOT NULL,
  FOREIGN KEY (node) REFERENCES node (id)
);
