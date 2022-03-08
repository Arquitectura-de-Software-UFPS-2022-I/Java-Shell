CREATE TABLE IF NOT EXISTS file (
  id int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  file BLOB NOT NULL,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS user (
  id int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  email varchar(30) NOT NULL,
  username varchar(10) NOT NULL,
  password varchar(250) NOT NULL,
  signature_id int(10),
  modified_date datetime,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE user ADD CONSTRAINT user_signature_id_fk FOREIGN KEY (signature_id) REFERENCES file (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS signature_request (
  id int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  subject varchar(250) NOT NULL,
  document_id int(10),
  user_id int(10) NOT NULL,  
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE signature_request ADD CONSTRAINT signature_request_document_id_fk FOREIGN KEY (document_id) REFERENCES file (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE signature_request ADD CONSTRAINT signature_request_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS signature_request_user (
  id int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  request_id int(10) NOT NULL, 
  user_id int(10) NOT NULL, 
  signature_id int(10),
  pos_x int(10), 
  pos_y int(10), 
  signed TINYINT(1) DEFAULT 0,
  signature_date datetime,
  created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE signature_request_user ADD CONSTRAINT signature_request_user_request_id_fk FOREIGN KEY (request_id) REFERENCES signature_request (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE signature_request_user ADD CONSTRAINT signature_request_user_signature_id_fk FOREIGN KEY (signature_id) REFERENCES file (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE signature_request_user ADD CONSTRAINT signature_request_user_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE;
