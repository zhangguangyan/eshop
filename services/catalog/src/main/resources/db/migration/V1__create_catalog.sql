CREATE TABLE IF NOT EXISTS catalog(name VARCHAR(200));
CREATE TABLE IF NOT EXISTS catalog_brand (
  brand_id serial PRIMARY KEY,
  brand VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS catalog_type (
  type_id serial PRIMARY KEY,
  type VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS catalog_item (
  item_id serial PRIMARY KEY,
  description VARCHAR(2000),
  name VARCHAR(50) NOT NULL,
  picture_filename VARCHAR(2000),
  price NUMERIC (18, 2) NOT NULL,
  available_stock INT NOT NULL,
  max_stock_threshold INT DEFAULT 0  NOT NULL,
  on_reorder BOOL NOT NULL,
  restock_threshold INT DEFAULT 0  NOT NULL,
  catalog_brand_id INT REFERENCES catalog_brand(brand_id),
  catalog_type_id INT REFERENCES catalog_type(type_id)
);

CREATE INDEX ix_catalog_item_brand_id ON catalog_item (catalog_brand_id);

CREATE INDEX ix_catalog_item_type_id ON catalog_item (catalog_type_id);

-- CREATE TABLE INTEGRATION_EVENT_LOG (
-- 	EVENT_ID UNIQUEIDENTIFIER NOT NULL CONSTRAINT PK_INTEGRATIONEVENTLOG PRIMARY KEY,
-- 	CONTENT NVARCHAR(MAX) NOT NULL,
-- 	CREATION_TIME DATETIME2 NOT NULL,
-- 	EVENT_TYPE_NAME NVARCHAR(MAX) NOT NULL,
-- 	STATE INT NOT NULL,
-- 	TIMESSENT INT NOT NULL
-- );
--
