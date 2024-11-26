CREATE
    TABLE
        orders(
            id VARCHAR(36) PRIMARY KEY,
            status VARCHAR(20) NOT NULL,
            price DECIMAL(
                10,
                2
            ) NOT NULL
        );