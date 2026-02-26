-- V1.0.3: Fix type mismatch and add database constraints

-- Step 1: Delete orphaned activity (id=40882, supplier_id=5 doesn't exist)
DELETE FROM getyourguide.activity WHERE id = 40882;

-- Step 2: Fix type mismatch (INT → BIGINT)
ALTER TABLE getyourguide.supplier ALTER COLUMN id BIGINT;

-- Step 3: Add FK constraint with RESTRICT (prevent supplier deletion if activities exist)
ALTER TABLE getyourguide.activity
  ADD CONSTRAINT fk_activity_supplier
  FOREIGN KEY (supplier_id) REFERENCES getyourguide.supplier(id)
  ON DELETE RESTRICT;

-- Step 4: Add NOT NULL constraints
ALTER TABLE getyourguide.activity ALTER COLUMN title SET NOT NULL;
ALTER TABLE getyourguide.activity ALTER COLUMN price SET NOT NULL;
ALTER TABLE getyourguide.activity ALTER COLUMN currency SET NOT NULL;
ALTER TABLE getyourguide.activity ALTER COLUMN rating SET NOT NULL;

ALTER TABLE getyourguide.supplier ALTER COLUMN name SET NOT NULL;
ALTER TABLE getyourguide.supplier ALTER COLUMN city SET NOT NULL;
ALTER TABLE getyourguide.supplier ALTER COLUMN country SET NOT NULL;
