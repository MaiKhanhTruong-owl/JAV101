
use master
go

-- Tạo database
CREATE DATABASE Lab08;
GO

USE Lab08;
GO

-- Bảng Departments
CREATE TABLE Departments (
    id CHAR(3) NOT NULL PRIMARY KEY,
    Name NVARCHAR(50) NOT NULL,
    Description NVARCHAR(255) NULL
);
GO

-- Bảng Employees
CREATE TABLE Employees (
    Id VARCHAR(20) NOT NULL PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Photo NVARCHAR(50) NOT NULL,
    Gender BIT NOT NULL,
    Birthday DATE NOT NULL,
    Salary FLOAT NOT NULL,
    DepartmentId CHAR(3) NOT NULL,

    FOREIGN KEY (DepartmentId) REFERENCES Departments(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
GO

CREATE TABLE Users (
    username NVARCHAR(50) NOT NULL PRIMARY KEY,
    password NVARCHAR(50) NULL,
    fullname NVARCHAR(50) NULL,
    role INT NULL,
    email NVARCHAR(50) NULL,
    address NVARCHAR(255) NULL,
    sdt NVARCHAR(50) NOT NULL
);
GO

-- Thêm dữ liệu cho bảng Departments
INSERT INTO Departments (id, Name, Description)
VALUES 
    ('001', 'Sales', 'Sales Department'),
    ('002', 'HR', 'Human Resources Department'),
    ('003', 'IT', 'Information Technology Department');
GO

-- Thêm dữ liệu cho bảng Employees
INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId)
VALUES
    ('E001', 'pass123', 'John Doe', 'john.jpg', 1, '1990-05-15', 50000, '001'),
    ('E002', 'pass234', 'Jane Smith', 'jane.jpg', 0, '1988-07-23', 60000, '002'),
    ('E003', 'pass345', 'Michael Johnson', 'michael.jpg', 1, '1985-02-12', 55000, '003');
GO

INSERT INTO Users (username, password, fullname, role, email, address, sdt)
VALUES 
    ('admin', '123456', N'Administrator', 1, 'admin@example.com', N'Hà Nội', '0909000000'),

    ('john', 'pass123', N'John Nguyen', 0, 'john@example.com', N'Hồ Chí Minh', '0911222333'),

    ('maria', 'maria2024', N'Maria Tran', 0, 'maria@example.com', N'Đà Nẵng', '0988111222'),

    ('khoa', 'khoa999', N'Nguyễn Văn Khoa', 0, 'khoa@example.com', N'Hải Phòng', '0977555666');
GO

SELECT * FROM Departments;

SELECT * FROM Employees;

SELECT * FROM Users;
