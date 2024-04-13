-- Create database hr
CREATE TABLE Departments (
    department_id INT PRIMARY KEY auto_increment,
    department_name VARCHAR(100),
    manager_id INT unique
);

CREATE TABLE Employees (
    employee_id INT PRIMARY KEY auto_increment,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    hire_date DATE NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

ALTER TABLE Departments
ADD CONSTRAINT fk_manager_id FOREIGN KEY (manager_id) REFERENCES Employees(employee_id);

CREATE TABLE Projects (
    project_id INT PRIMARY KEY auto_increment,
    project_name VARCHAR(255),
    department_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id)
);

CREATE TABLE VacationTypes (
    vacation_id INT PRIMARY KEY auto_increment,
    vacation_name VARCHAR(100) UNIQUE
);

CREATE TABLE VacationRequests (
    request_id INT PRIMARY KEY auto_increment,
    employee_id INT,
    start_date DATE,
    end_date DATE,
    status ENUM('accepted', 'rejected', 'pending'),
    vacation_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
	FOREIGN KEY (vacation_id) REFERENCES VacationTypes(vacation_id)
);