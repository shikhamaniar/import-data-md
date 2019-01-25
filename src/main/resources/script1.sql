create	sequence	seq_person	start	with	1	increment	by	50	cycle; 
create	sequence	seq_employment	start	with	1	increment	by	50	cycle;

create	table	tbl_employment	
(		id	bigint		primary	key	default	nextval('seq_employment') ,
employer_name	varchar(256)	not	null,
designation	varchar(256)	not	null,
employment_date	date	not	null );
		
create	table	tbl_person	(
id	bigint	primary	key	default	nextval('seq_person'),
first_name	varchar(256)	not	null,
last_name	varchar(256)	not	null,
middle_name	varchar(256),
mobile	varchar(15)	not	null,
email	varchar(256)	not	null	unique,
gender	varchar(15)	not	null,		city	varchar(256),
state	varchar(256),		country	varchar(256),
employment_id	bigint	not	null	references	tbl_employment( id) );