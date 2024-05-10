CREATE TABLE IF NOT EXISTS product (
  id            BIGSERIAL PRIMARY KEY,
  name          VARCHAR(255) NOT NULL,
  description   VARCHAR(4096) NOT NULL,
  price         NUMERIC(8,2) NOT NULL,
  in_stock     BOOLEAN NOT NULL,
  CONSTRAINT chk_price_positive CHECK (price>= 0)
  );
