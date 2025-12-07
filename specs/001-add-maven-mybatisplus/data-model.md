# Data Model: Add Maven and MyBatis Plus Support

## Entities

### Order
- **id**: String (primary key)
- **status**: OrderStatus (enum: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)
- **price**: BigDecimal
- **Relationships**: 1 User places * Orders

**Validation Rules**:
- id: required, unique
- status: required, valid enum value
- price: required, positive

**State Transitions**:
- PENDING → CONFIRMED
- CONFIRMED → SHIPPED
- SHIPPED → DELIVERED
- Any status → CANCELLED

### User
- **id**: String (primary key)
- **name**: String
- **Relationships**: 1 User places * Orders

**Validation Rules**:
- id: required, unique
- name: required, non-empty

## MyBatis Plus Implementation Notes

### OrderEntity
- Maps to `order` table
- Uses `@TableId` for id field
- Uses `@TableField` for price field
- Status mapped via enum handler

### UserEntity
- Maps to `user` table
- Uses `@TableId` for id field
- Uses `@TableField` for name field

### Mappers
- OrderMapper extends BaseMapper<OrderEntity>
- UserMapper extends BaseMapper<UserEntity>
- Custom queries in XML files under `resources/mapper/`

### Services
- OrderService extends IService<OrderEntity>
- UserService extends IService<UserEntity>
- Implement business logic wrappers