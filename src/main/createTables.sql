CREATE TABLE Users(
    Username VARCHAR(20) PRIMARY KEY,
    Password VARBINARY(144)
);

CREATE TABLE Friends (
    Username1 VARCHAR(20) FOREIGN KEY REFERENCES Users(Username),
    Username2 VARCHAR(20) FOREIGN KEY REFERENCES Users(Username)
);