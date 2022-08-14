
CREATE TABLE "client" (
  "id" uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "credentials" (
  "email" varchar PRIMARY KEY,
  "password" varchar NOT NULL,
  "client_id" uuid
);



CREATE TABLE "tokens" (
  "code" uuid DEFAULT uuid_generate_v4 () NOT NULL PRIMARY KEY,
  "expiration" TIMESTAMP NOT NULL,
  "num_calls" int DEFAULT 0,
  "status" token_status,
  "client_id" uuid
);

ALTER TABLE "credentials" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("id") on DELETE CASCADE;

ALTER TABLE "tokens" ADD FOREIGN KEY ("client_id") REFERENCES "client" ("id") on DELETE CASCADE;






create or replace procedure newClient(
	_id uuid,
	_name varchar,
	_email varchar,
	_password varchar
) 
language plpgsql
as $$
begin 
	if( (select count(*) from credentials where email=_email) = 0)
	then
		insert into client (id,name) values (_id, _name);

		insert into credentials (email, "password", client_id) values (_email, _password, _id);
	
		commit;
	else
		raise exception using message = 'email already in use';
	end if;
end;$$





