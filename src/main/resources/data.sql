USE fillandgo;
CREATE TABLE IF NOT EXISTS user (
                                    id VARCHAR(36) PRIMARY KEY,
                                    user_name VARCHAR(255) NOT NULL,
                                    email VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    role VARCHAR(50) NOT NULL,
                                    created_at VARCHAR(36) NOT NULL,
                                    created_by VARCHAR(20) NOT NULL,
                                    updated_at VARCHAR(36) NOT NULL,
                                    updated_by VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS contract (
                                        id VARCHAR(255) PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        surname VARCHAR(255) NOT NULL,
                                        patronymic VARCHAR(255) NOT NULL ,
                                        mobile_number VARCHAR(255) NOT NULL UNIQUE,
                                        created_at VARCHAR(255) NOT NULL,
                                        created_by VARCHAR(255) NOT NULL,
                                        updated_at VARCHAR(255) NOT NULL,
                                        updated_by VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS contract_files (
                                        id VARCHAR(255) PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        user_id VARCHAR(255) NOT NULL ,
                                        created_at VARCHAR(255) NOT NULL,
                                        created_by VARCHAR(255) NOT NULL,
                                        updated_at VARCHAR(255) NOT NULL,
                                        updated_by VARCHAR(255) NOT NULL
);
