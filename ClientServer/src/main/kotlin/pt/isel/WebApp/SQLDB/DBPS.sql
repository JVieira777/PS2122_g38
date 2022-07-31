CREATE  TABLE New_User (
    ID uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
	Username VARCHAR(50) NOT NULL Unique,
	Email_Address VARCHAR(50) NOT NULL Unique,
    Password VARCHAR(50) NOT NULL,
	rate float,
	Profile_Picture VARCHAR(100),
	Terminated BOOLEAN
);



CREATE TABLE Seller (
    ID uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
	Name VARCHAR(50) NOT NULL Unique,
	Contry VARCHAR(50),
    description VARCHAR(50),
	rate float,
	Wallet VARCHAR(50) NOT NULL,
	Terminated BOOLEAN,
	UID uuid REFERENCES New_User(ID) on DELETE CASCADE
);



CREATE TABLE Moderator (
    ID uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
	Name VARCHAR(50) NOT NULL Unique,
    description VARCHAR(50),
	Terminated BOOLEAN,
	UID uuid REFERENCES New_User(ID) on DELETE CASCADE
);

	
CREATE  TABLE Product (
    ID uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
	description VARCHAR(50),
	price int,
	rate float,
	SID uuid REFERENCES Seller(ID) on DELETE CASCADE
);


CREATE   TABLE Image (
    ID uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
    Path VARCHAR(100) NOT NULL,
    PID uuid REFERENCES Product(ID) on DELETE CASCADE NOT NULL
);

CREATE TABLE Exchange (
    ID   SERIAL PRIMARY KEY,
	client_id uuid REFERENCES New_User(ID)on DELETE CASCADE,
	seller_id uuid REFERENCES Seller(ID)on DELETE CASCADE,
	PID uuid REFERENCES Product(ID)on DELETE CASCADE,
    Value Int,
	Quantity Int,
	completed BOOLEAN,
	End_Date date
);

CREATE TABLE RefundForm (
    ID   SERIAL PRIMARY KEY,
	client_id uuid REFERENCES New_User(ID)on DELETE CASCADE,
	exchange_id SERIAL REFERENCES Exchange(ID)on DELETE CASCADE,
	description VARCHAR(100),
);





CREATE EXTENSION IF NOT EXISTS "uuid-ossp";



