CREATE
    TABLE
        person(
            id VARCHAR(36) PRIMARY KEY,
            firstname VARCHAR(20) NOT NULL,
            lastname VARCHAR(20) NOT NULL,
            active_status VARCHAR(20) NOT NULL,
            country VARCHAR(20) NOT NULL,
            city VARCHAR(20) NOT NULL,
            created_date TIMESTAMP NOT NULL,
            last_modified_date TIMESTAMP NOT NULL,
            version INT
        );