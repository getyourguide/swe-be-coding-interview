-- V1.0.4: Add performance indexes

-- FK index (join optimization)
CREATE INDEX idx_activity_supplier_id ON getyourguide.activity(supplier_id);

-- Search optimization indexes (individual per column)
CREATE INDEX idx_activity_title ON getyourguide.activity(title);
CREATE INDEX idx_supplier_name ON getyourguide.supplier(name);
CREATE INDEX idx_supplier_address ON getyourguide.supplier(address);
CREATE INDEX idx_supplier_city ON getyourguide.supplier(city);
CREATE INDEX idx_supplier_country ON getyourguide.supplier(country);
CREATE INDEX idx_supplier_zip ON getyourguide.supplier(zip);
