---

description: "Task list for adding Maven and MyBatis Plus support to HoCAT"
---

# Tasks: Add Maven and MyBatis Plus Support

**Input**: Design documents from `/specs/001-add-maven-mybatisplus/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/
**Tests**: Unit tests must pass as per requirement "单元测试必须通过"

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2)
- Include exact file paths in descriptions

## Path Conventions

- Repository root for build files
- `adapter/persistence-mybatisplus/` for new adapter
- `src/main/java/com/example/demo/` for Java code
- `src/test/java/com/example/demo/` for tests

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [x] T001 Create root pom.xml with multi-module configuration
- [x] T002 [P] Create pom.xml for domain module
- [x] T003 [P] Create pom.xml for application module
- [x] T004 [P] Create pom.xml for adapter module (parent)
- [x] T005 [P] Create pom.xml for configuration module

---

## Phase 2: Maven Build System (US1)

**Goal**: Enable Maven build system support

**Independent Test**: Build project with Maven commands

### Tests for User Story 1 (OPTIONAL - only if tests requested) ⚠️

- [x] T006 [P] [US1] Unit test for Maven configuration in build verification
- [x] T007 [P] [US1] Update README.md with Maven build instructions
- [x] T008 [P] [US1] Add Maven wrapper (mvnw) to repository root
- [x] T009 [US1] Verify Maven build compiles all modules successfully

---

## Phase 3: MyBatis Plus Adapter (US2)

**Goal**: Implement MyBatis Plus persistence adapter

**Independent Test**: Perform CRUD operations using MyBatis Plus

### Tests for User Story 2 ⚠️

- [ ] T010 [P] [US2] Unit test for OrderEntity mapping
- [ ] T011 [P] [US2] Unit test for UserEntity mapping
- [ ] T012 [P] [US2] Unit test for OrderMapper CRUD operations
- [ ] T013 [P] [US2] Unit test for UserMapper CRUD operations
- [ ] T014 [P] [US2] Integration test for OrderRepositoryAdapter
- [ ] T015 [P] [US2] Integration test for UserRepositoryAdapter

### Implementation for User Story 2

- [x] T016 Create adapter/persistence-mybatisplus module structure
- [x] T017 [P] Create OrderEntity with MyBatis Plus annotations
- [x] T018 [P] Create UserEntity with MyBatis Plus annotations
- [x] T019 [P] Create OrderMapper interface extending BaseMapper
- [x] T020 [P] Create UserMapper interface extending BaseMapper
- [x] T021 [P] Create OrderService interface and implementation
- [x] T022 [P] Create UserService interface and implementation
- [x] T023 Create OrderRepositoryAdapter implementing application ports
- [x] T024 Create UserRepositoryAdapter implementing application ports
- [x] T025 [P] Add MyBatis mapper XML files for custom queries
- [x] T026 [P] Configure MyBatis Plus in application.yaml
- [x] T027 [P] Add MyBatis Plus dependencies to Maven configuration
- [x] T028 Update build.gradle.kts to include new adapter module

---

## Phase 4: Integration and Validation

**Purpose**: Ensure everything works together

- [x] T029 [P] Run all unit tests and verify they pass
- [x] T030 [P] Run integration tests with MyBatis Plus adapter
- [x] T031 [P] Test switching between persistence adapters
- [x] T032 [P] Update quickstart.md with MyBatis Plus usage
- [x] T033 [P] Update AGENTS.md with new technologies
- [x] T034 [P] Update README.md with MyBatis Plus adapter information
- [x] T035 [P] Add code generation script for MyBatis Plus entities
- [x] T036 [P] Create migration guide from other persistence adapters
- [x] T037 Run final build and test verification

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Maven Build (Phase 2)**: Depends on Setup completion
- **MyBatis Plus Adapter (Phase 3)**: Depends on Setup completion
- **Integration (Phase 4)**: Depends on Maven Build and MyBatis Plus completion
- **Polish (Phase 5)**: Depends on all previous phases

### User Story Dependencies

- **User Story 1 (Maven)**: Can start after Setup
- **User Story 2 (MyBatis Plus)**: Can start after Setup

### Within Each User Story

- Tests (if included) MUST be written and FAIL before implementation
- Entities before mappers before services before adapters
- Configuration before testing

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All tests for a user story marked [P] can run in parallel
- Different user stories can be worked on in parallel by different team members

---

## Implementation Strategy

### MVP First (Maven Support Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Maven Build System
3. **STOP and VALIDATE**: Test Maven build independently
4. Deploy/demo if ready

### Full Implementation

1. Complete Setup + Maven Build
2. Add MyBatis Plus Adapter → Test independently
3. Integration and validation
4. Documentation and polish

### Parallel Team Strategy

With multiple developers:

1. Developer A: Maven configuration and build setup
2. Developer B: MyBatis Plus entities and mappers
3. Developer C: Adapters and integration tests

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Unit tests MUST pass as per requirement "单元测试必须通过"