# Project Issues - Prioritized

## 🔴 CRITICAL (Fix Immediately)

### 1. NPE in ActivityService.getActivities(Long id) - Fixed
**File**: `ActivityService.java:47`
**Issue**: Returns null at line 38, but calls `activity.getSupplier().getName()` at line 47 without null check
**Impact**: NullPointerException crashes endpoint
**Fix**: Throw exception or handle null properly

### 2. Unsafe In-Memory Search (DoS Risk) - Fixed
**File**: `ActivityService.java:52-54`
**Issue**: `findAll()` loads ALL activities, filters in-memory with `contains()`
**Impact**: Memory exhaustion, poor performance, DoS potential
**Fix**: Use DB query like SupplierRepository does

### 3. N+1 Query Problem - Fixed
**File**: `ActivityService.java:19-34`
**Issue**: Loads all activities, then accesses lazy `supplier.getName()` in loop
**Impact**: 1 + N queries instead of 1
**Fix**: Use JOIN FETCH or DTO projection

### 4. DB Schema Type Mismatch
**Files**: `V1.0.1__initial.sql`, `V1.0.2__add_supplier.sql`
**Issue**: Activity.id=LONG, Supplier.id=INT, but FK supplier_id=LONG
**Impact**: Potential FK constraint failures
**Fix**: Standardize to LONG

## 🟠 HIGH (Fix Soon)

### 5. Missing Input Validation - Fixed
**Files**: All controllers
**Issue**: No `@Valid`, `@NotNull`, `@NotBlank` on search params
**Impact**: Null/empty/malicious input accepted
**Fix**: Add validation annotations

### 6. Wrong Controller Annotations - Fixed
**Files**: All 3 controllers
**Issue**: Using `@Controller` instead of `@RestController`
**Impact**: Semantically incorrect for REST APIs
**Fix**: Change to `@RestController`

### 7. Generic Error Handling - Fixed
**File**: `ErrorHandler.java:13-15`
**Issue**: All exceptions → 500 with "Something went wrong"
**Impact**: No distinction between client/server errors
**Fix**: Add specific handlers for validation, not found, etc.

### 8. Missing DB Constraints - Fixed ✅
**Files**: Migration files V1.0.3, V1.0.4
**Issue**: No FK constraints, indexes, NOT NULL, unique constraints
**Impact**: Data integrity issues
**Fix**: Added FK constraints, NOT NULL constraints, and performance indexes

## 🟡 MEDIUM (Technical Debt)

### 9. Unused Imports
**Files**: `SupplierController.java:5`, `SupplierService.java:5,7,8`
**Fix**: Remove unused imports

### 10. Entity Exposed as DTO
**File**: `SupplierController.java:22`
**Issue**: Returns raw Supplier entity instead of DTO
**Impact**: Tight coupling, exposes internal structure
**Fix**: Create SupplierDto

### 11. Inconsistent Lombok Usage
**Files**: All services/controllers
**Issue**: Mix of `@AllArgsConstructor` and `@RequiredArgsConstructor`
**Fix**: Standardize on `@RequiredArgsConstructor`

### 12. Unsafe Object[] Return Type
**File**: `StatisticsRepository.java:11`
**Issue**: Returns `List<Object[]>` - untyped
**Fix**: Create DTO or use Tuple

### 13. Incorrect Lombok on DTOs
**File**: `ActivityDto.java:8`
**Issue**: `@Data` + `@Builder` combination, includes equals/hash
**Fix**: Use `@Value` + `@Builder` or just `@Builder` + `@Getter`

## 🟢 LOW (Nice to Have)

### 14. Empty/Weak Tests
**Files**: Test files
**Issue**: SupplierControllerTest empty, others only basic assertions
**Fix**: Add proper test coverage

### 15. Missing Documentation
**Files**: All endpoints
**Issue**: No JavaDoc, Swagger, or API versioning
**Fix**: Add OpenAPI annotations

### 16. Unnecessary Dependencies
**File**: `build.gradle:28`
**Issue**: Thymeleaf dependency not used
**Fix**: Remove from build.gradle

### 17. Empty H2 Credentials
**File**: `application.properties:3-4`
**Issue**: Blank username/password
**Fix**: Set proper credentials or document as intentional

### 18. Hard-coded Schema Names
**Files**: Entity classes
**Issue**: "getyourguide" schema hard-coded in `@Table`
**Fix**: Externalize to properties

---

## Priority Summary

| Priority | Count | Effort |
|----------|-------|--------|
| Critical | 4 | 4-6h |
| High | 4 | 3-4h |
| Medium | 6 | 2-3h |
| Low | 5 | 1-2h |

**Recommended Fix Order**: 1 → 2 → 3 → 5 → 6 → 7 → 4 → 8
