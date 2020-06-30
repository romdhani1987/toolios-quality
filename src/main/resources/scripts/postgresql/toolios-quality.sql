CREATE SEQUENCE public.address_id_seq;
CREATE TABLE public.address (
                id BIGINT NOT NULL DEFAULT nextval('public.address_id_seq'),
                street VARCHAR(250) ,
                code VARCHAR(250) ,
                city VARCHAR(250) ,
                country VARCHAR(250) ,
                CONSTRAINT address_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.company_id_seq;
CREATE TABLE public.company(
                id BIGINT NOT NULL DEFAULT nextval('public.company_id_seq'),
                name VARCHAR(250) NOT NULL,
				serial BIGINT ,
				SIREN BIGINT ,
				SIRET BIGINT,
				address_id BIGINT,
				serialized_properties TEXT,
                CONSTRAINT company_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.computer_id_seq;
CREATE TABLE public.computer(
                id BIGINT NOT NULL DEFAULT nextval('public.computer_id_seq'),
                computer_name VARCHAR(250) NOT NULL,
				serial_number VARCHAR(250),
	            processor VARCHAR(250),
	            ram VARCHAR(250),
	            service_tag VARCHAR(250),
	            os VARCHAR(250),
	            age BIGINT,
	            shifting BOOLEAN,
		        purchase_date TIMESTAMP,
				serialized_properties TEXT,
	            user_id BIGINT,
                CONSTRAINT computer_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.license_id_seq;
CREATE TABLE public.license(
                id BIGINT NOT NULL DEFAULT nextval('public.license_id_seq'),
                license_name VARCHAR(250) NOT NULL,
				license_type VARCHAR(250),
				serial_number VARCHAR(250),
				expiration_date TIMESTAMP,
		        purchase_date TIMESTAMP,
				serialized_properties TEXT,
				computer_id BIGINT,
                CONSTRAINT license_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.screen_id_seq;
CREATE TABLE public.screen(
                id BIGINT NOT NULL DEFAULT nextval('public.screen_id_seq'),
                screen_name VARCHAR(250) NOT NULL,
				serial_number VARCHAR(250),
				service_tag VARCHAR(250),
				age BIGINT,
		        purchase_date TIMESTAMP,
				serialized_properties TEXT,
				computer_id BIGINT,
                CONSTRAINT screen_pk PRIMARY KEY (id)
);


CREATE SEQUENCE public.user_function_id_seq;
CREATE TABLE public.user_function(
                id BIGINT NOT NULL DEFAULT nextval('public.user_function_id_seq'),
                name VARCHAR(250) ,
				serialized_properties TEXT,
                CONSTRAINT user_function_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.group_id_seq;
CREATE TABLE public.user_group (
                id BIGINT NOT NULL DEFAULT nextval('public.group_id_seq'),
                name VARCHAR(250) ,
			    company_id BIGINT NOT NULL,
                serialized_properties TEXT,
                CONSTRAINT group_pk PRIMARY KEY (id)
);



CREATE SEQUENCE public.user_account_id_seq;
CREATE TABLE public.user_account (
                id BIGINT NOT NULL DEFAULT nextval('public.user_account_id_seq'),
                login VARCHAR(50) NOT NULL,
				f_name VARCHAR(50),
				l_name VARCHAR(50),
				email VARCHAR(50) ,
				phone_number VARCHAR(50),
                password_hash VARCHAR(50),
				creation_mode VARCHAR(50),
                serialized_properties TEXT,
				address_id BIGINT ,
				function_id BIGINT ,
				roles_id BIGINT ,
				group_id BIGINT,
				CONSTRAINT user_account_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.provider_account_id_seq;
CREATE TABLE public.provider_account (
                id BIGINT NOT NULL DEFAULT nextval('public.provider_account_id_seq'),
                login VARCHAR(50) NOT NULL,
				f_name VARCHAR(50),
				l_name VARCHAR(50),
				email VARCHAR(50) ,
				phone_number VARCHAR(50),
                creation_mode VARCHAR(50),
                serialized_properties TEXT,
				address_id BIGINT ,
				CONSTRAINT provider_account_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.user_roles_id_seq;
CREATE TABLE public.user_roles(
                id BIGINT NOT NULL DEFAULT nextval('public.user_roles_id_seq'),
                name VARCHAR(250) NOT NULL,
				serialized_properties TEXT,
                CONSTRAINT user_roles_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.user_request_id_seq;
CREATE TABLE public.user_request(
                id BIGINT NOT NULL DEFAULT nextval('public.user_request_id_seq'),
                name VARCHAR(250) ,
				creation_timestamp TIMESTAMP,
				archive_timestamp TIMESTAMP,
				serialized_properties TEXT,
                CONSTRAINT user_request_id_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_account_request_map (
                user_account_id BIGINT NOT NULL,
                user_request_id BIGINT NOT NULL,
                CONSTRAINT user_account_request_map_pk PRIMARY KEY (user_account_id, user_request_id)
);

CREATE SEQUENCE public.unconformity_id_seq;
CREATE TABLE public.unconformity(
                id BIGINT NOT NULL DEFAULT nextval('public.unconformity_id_seq'),
                name VARCHAR(250),
				description VARCHAR(250) ,
				serialized_properties TEXT,
                CONSTRAINT unconformity_id_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.subject_id_seq;
CREATE TABLE public.subject(
                id BIGINT NOT NULL DEFAULT nextval('public.subject_id_seq'),
                title VARCHAR(250) ,
				description VARCHAR(250),
				serialized_properties TEXT,
                CONSTRAINT subject_id_pk PRIMARY KEY (id)
);

CREATE TABLE public.unconformity_subject_map (
                unconformity_id BIGINT NOT NULL,
                subject_id BIGINT NOT NULL,
                CONSTRAINT unconformity_subject_map_pk PRIMARY KEY (unconformity_id, subject_id)
);

CREATE SEQUENCE public.product_id_seq;
CREATE TABLE public.product (
                id BIGINT NOT NULL DEFAULT nextval('public.product_id_seq'),
                title VARCHAR(250) NOT NULL,
                description VARCHAR(1000),
                serialized_properties TEXT,
                price_ht NUMERIC,
                price_ttc NUMERIC,
                provider_account_id BIGINT ,
                article_category_id BIGINT ,
                CONSTRAINT product_pk PRIMARY KEY (id)
);

CREATE TABLE public.unconformity_product_map (
                unconformity_id BIGINT NOT NULL,
                product_id BIGINT NOT NULL,
                CONSTRAINT unconformity_product_map_pk PRIMARY KEY (unconformity_id, product_id)
);

CREATE SEQUENCE public.project_id_seq;
CREATE TABLE public.project (
                id BIGINT NOT NULL DEFAULT nextval('public.project_id_seq'),
                title VARCHAR(250) NOT NULL,
                description VARCHAR(1000),
                creation_timestamp TIMESTAMP ,
                lock_expiration_timestamp TIMESTAMP,
				supervisor_id BIGINT ,
				serialized_properties TEXT,
                CONSTRAINT project_pk PRIMARY KEY (id)
);

CREATE TABLE public.unconformity_project_map (
                unconformity_id BIGINT NOT NULL,
                project_id BIGINT NOT NULL,
                CONSTRAINT unconformity_project_map_pk PRIMARY KEY (unconformity_id, project_id)
);

CREATE TABLE public.user_account_project_map (
                project_id BIGINT NOT NULL,
                user_account_id BIGINT NOT NULL,
                CONSTRAINT user_account_project_map_pk PRIMARY KEY (project_id, user_account_id)
);


CREATE SEQUENCE public.user_order_id_seq;
CREATE TABLE public.user_order (
                id BIGINT NOT NULL DEFAULT nextval('public.user_order_id_seq'),
                name VARCHAR(250),
                description VARCHAR(1000),
                creation_timestamp TIMESTAMP ,
           		serialized_properties TEXT,
                CONSTRAINT user_order_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_account_order_map (
                user_order_id BIGINT NOT NULL,
                user_account_id BIGINT NOT NULL,
                CONSTRAINT user_account_order_map_pk PRIMARY KEY (user_order_id, user_account_id)
);

CREATE TABLE public.user_order_product_map (
                user_order_id BIGINT NOT NULL,
                product_id BIGINT NOT NULL,
                CONSTRAINT user_order_product_map_pk PRIMARY KEY (user_order_id, product_id)
);

CREATE SEQUENCE public.purchase_id_seq;
CREATE TABLE public.purchase (
                id BIGINT NOT NULL DEFAULT nextval('public.purchase_id_seq'),
                title VARCHAR(250) ,
                description VARCHAR(1000),
                creation_timestamp TIMESTAMP NOT NULL,
                serialized_properties TEXT,
                CONSTRAINT purchase_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_request_purchase_map (
                purchase_id BIGINT NOT NULL,
                user_request_id BIGINT NOT NULL,
                CONSTRAINT user_request_purchase_map_pk PRIMARY KEY (purchase_id, user_request_id)
);


CREATE TABLE public.purchase_product_map (
                purchase_id BIGINT NOT NULL,
                product_id BIGINT NOT NULL,
                CONSTRAINT purchase_product_map_pk PRIMARY KEY (purchase_id, product_id)
);

CREATE SEQUENCE public.user_action_id_seq;
CREATE TABLE public.user_action (
                id BIGINT NOT NULL DEFAULT nextval('public.user_action_id_seq'),
                title VARCHAR(250) ,
                description VARCHAR(1000),
                creation_timestamp TIMESTAMP ,
				user_action_type_id BIGINT,
                serialized_properties TEXT,
                CONSTRAINT user_action_pk PRIMARY KEY (id)
);

CREATE SEQUENCE public.user_action_type_id_seq;
CREATE TABLE public.user_action_type (
                id BIGINT NOT NULL DEFAULT nextval('public.user_action_type_id_seq'),
                title VARCHAR(250) NOT NULL,
                CONSTRAINT user_action_type_pk PRIMARY KEY (id)
);


CREATE TABLE public.user_action_request_map (
                user_request_id BIGINT NOT NULL,
                action_id BIGINT NOT NULL,
                CONSTRAINT user_action_request_map_pk PRIMARY KEY (user_request_id, action_id)
);

CREATE SEQUENCE public.user_action_purchase_id_seq;
CREATE TABLE public.user_action_purchase (
                id BIGINT NOT NULL DEFAULT nextval('public.user_action_purchase_id_seq'),
                title VARCHAR(250) ,
                description VARCHAR(1000),
                creation_timestamp TIMESTAMP ,
				serialized_properties TEXT,
                CONSTRAINT user_action_purchase_pk PRIMARY KEY (id)
);

CREATE TABLE public.user_action_purchase_map (
                user_action_id BIGINT NOT NULL,
                user_action_purchase_id BIGINT NOT NULL,
                CONSTRAINT user_action_purchase_map_pk PRIMARY KEY (user_action_id, user_action_purchase_id)
);

CREATE TABLE public.user_action_purchase_order_map (
               user_action_purchase_id BIGINT NOT NULL,
               order_id BIGINT NOT NULL,
               CONSTRAINT user_action_purchase_order_map_pk PRIMARY KEY (user_action_purchase_id, order_id)
);
-- company --

ALTER TABLE public.company ADD CONSTRAINT company_address_fk
FOREIGN KEY (address_id)
REFERENCES public.address (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- user_group --

ALTER TABLE public.user_group ADD CONSTRAINT user_group_company_fk
FOREIGN KEY (company_id)
REFERENCES public.company (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- user_account --

ALTER TABLE public.user_account ADD CONSTRAINT user_account_address_fk
FOREIGN KEY (address_id)
REFERENCES public.address (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT user_account_function_fk
FOREIGN KEY (function_id)
REFERENCES public.user_function(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT user_account_roles_fk
FOREIGN KEY (roles_id)
REFERENCES public.user_roles (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account ADD CONSTRAINT user_account_group_fk
FOREIGN KEY (group_id)
REFERENCES public.user_group (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- provider_account --


ALTER TABLE public.provider_account ADD CONSTRAINT provider_account_address_fk
FOREIGN KEY (address_id)
REFERENCES public.address (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- user_account_request_map --

ALTER TABLE public.user_account_request_map ADD CONSTRAINT user_account_request_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_request_map ADD CONSTRAINT request_user_account_fk
FOREIGN KEY (user_request_id)
REFERENCES public.user_request (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- unconformity_subject_map --

ALTER TABLE public.unconformity_subject_map ADD CONSTRAINT unconformity_subject_map_fk
FOREIGN KEY (unconformity_id)
REFERENCES public.unconformity(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.unconformity_subject_map ADD CONSTRAINT subject_unconformity_map_fk
FOREIGN KEY (subject_id)
REFERENCES public.subject (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- product --

ALTER TABLE public.product ADD CONSTRAINT product_provider_account_fk
FOREIGN KEY (provider_account_id)
REFERENCES public.unconformity(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.product ADD CONSTRAINT product_article_category_fk
FOREIGN KEY (article_category_id)
REFERENCES public.provider_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- unconformity_product_map --

ALTER TABLE public.unconformity_product_map ADD CONSTRAINT unconformity_product_map_unconformity_fk
FOREIGN KEY (unconformity_id)
REFERENCES public.unconformity(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.unconformity_product_map ADD CONSTRAINT unconformity_product_map_product_fk
FOREIGN KEY (product_id)
REFERENCES public.product (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- project --

ALTER TABLE public.project ADD CONSTRAINT projet_supervisor_fk
FOREIGN KEY (supervisor_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- unconformity_product_map --

ALTER TABLE public.unconformity_project_map ADD CONSTRAINT unconformity_project_map_unconformity_fk
FOREIGN KEY (unconformity_id)
REFERENCES public.unconformity(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.unconformity_project_map ADD CONSTRAINT unconformity_project_map_project_fk
FOREIGN KEY (project_id)
REFERENCES public.project (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_account_project_map --

ALTER TABLE public.user_account_project_map ADD CONSTRAINT user_account_project_map_user_account_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_project_map ADD CONSTRAINT user_account_project_map_project_fk
FOREIGN KEY (project_id)
REFERENCES public.project (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_account_order_map --

ALTER TABLE public.user_account_order_map ADD CONSTRAINT user_account_order_map_order_fk
FOREIGN KEY (user_order_id)
REFERENCES public.user_order(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_account_order_map ADD CONSTRAINT user_account_order_map_user_account_fk
FOREIGN KEY (user_account_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_order_product_map --

ALTER TABLE public.user_order_product_map ADD CONSTRAINT user_order_product_map_order_fk
FOREIGN KEY (user_order_id)
REFERENCES public.user_order(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_order_product_map ADD CONSTRAINT user_order_product_map_product_fk
FOREIGN KEY (product_id)
REFERENCES public.product (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_request_purchase_map --

ALTER TABLE public.user_request_purchase_map ADD CONSTRAINT user_request_purchase_map_purchase_fk
FOREIGN KEY (purchase_id)
REFERENCES public.purchase(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_request_purchase_map ADD CONSTRAINT user_request_purchase_map_user_request_fk
FOREIGN KEY (user_request_id)
REFERENCES public.user_request (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- purchase_product_map --

ALTER TABLE public.purchase_product_map ADD CONSTRAINT purchase_product_map_purchase_fk
FOREIGN KEY (purchase_id)
REFERENCES public.purchase(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.purchase_product_map ADD CONSTRAINT purchase_product_map_product_fk
FOREIGN KEY (product_id)
REFERENCES public.product (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- user_action --


ALTER TABLE public.user_action ADD CONSTRAINT user_action_type_fk
FOREIGN KEY (user_action_type_id)
REFERENCES public.user_action_type (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_action_request_map --

ALTER TABLE public.user_action_request_map ADD CONSTRAINT user_action_request_map_user_request_fk
FOREIGN KEY (user_request_id)
REFERENCES public.user_request(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_action_request_map ADD CONSTRAINT user_action_request_map_action_fk
FOREIGN KEY (action_id)
REFERENCES public.user_action (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


-- user_action_purchase_map --

ALTER TABLE public.user_action_purchase_map ADD CONSTRAINT user_action_purchase_map_user_action_fk
FOREIGN KEY (user_action_id)
REFERENCES public.user_action(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_action_purchase_map ADD CONSTRAINT user_action_purchase_map_user_action_purchase_fk
FOREIGN KEY (user_action_purchase_id)
REFERENCES public.user_action_purchase (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

-- user_action_purchase_order_map --

ALTER TABLE public.user_action_purchase_order_map ADD CONSTRAINT user_action_purchase_order_map_user_action_purchase_fk
FOREIGN KEY (user_action_purchase_id)
REFERENCES public.user_action_purchase(id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.user_action_purchase_order_map ADD CONSTRAINT user_action_purchase_order_map_order_fk
FOREIGN KEY (order_id)
REFERENCES public.user_order (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;


ALTER TABLE public.computer ADD CONSTRAINT computer_user_fk
FOREIGN KEY (user_id)
REFERENCES public.user_account (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.license ADD CONSTRAINT license_computer_fk
FOREIGN KEY (computer_id)
REFERENCES public.computer (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.screen ADD CONSTRAINT screen_computer_fk
FOREIGN KEY (computer_id)
REFERENCES public.computer (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;
