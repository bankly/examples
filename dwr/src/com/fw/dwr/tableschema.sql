CREATE TABLE fw_order (
  ORDER_ID int(10) unsigned NOT NULL auto_increment,
  ORDER_NAME varchar(45) NOT NULL,
  STATUS_ID int(10) unsigned NOT NULL,
  UPDATE_DT date NOT NULL,
  PRIMARY KEY  (ORDER_ID)
);

CREATE TABLE fw_order_detail (
  DETAIL_ID int(10) unsigned NOT NULL auto_increment,
  ORDER_ID int(10) unsigned NOT NULL,
  DETAIL_NAME varchar(45) NOT NULL,
  UPDATE_DT date NOT NULL,
  PRIMARY KEY  (DETAIL_ID)
);

CREATE TABLE  fw_order_status (
  STATUS_ID int(10) unsigned NOT NULL auto_increment,
  STATUS_NAME varchar(45) NOT NULL,
  PRIMARY KEY  (STATUS_ID)
);
