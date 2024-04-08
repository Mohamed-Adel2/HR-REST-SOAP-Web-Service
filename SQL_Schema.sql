-- Create database hr
CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    phone_number VARCHAR(20),
    hire_date DATE,
    job_title VARCHAR(100),
    salary DECIMAL(10, 2),
    department_id INT
);

CREATE TABLE Projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(255),
    start_date DATE,
    end_date DATE
);

CREATE TABLE Departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100),
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES Employees(employee_id)
);

ALTER TABLE Employees
ADD CONSTRAINT fk_department_id
FOREIGN KEY (department_id) REFERENCES Departments(department_id);

CREATE TABLE WorksOn (
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
    FOREIGN KEY (project_id) REFERENCES Projects(project_id),
    PRIMARY KEY (employee_id, project_id)
);

CREATE TABLE VacationTypes (
    vacation_type_id INT PRIMARY KEY,
    vacation_type_name VARCHAR(100) UNIQUE
);

CREATE TABLE VacationRequests (
    request_id INT PRIMARY KEY,
    employee_id INT,
    start_date DATE,
    end_date DATE,
    status ENUM('accepted', 'rejected', 'pending'),
    vacation_type INT,
    reason TEXT,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id),
	FOREIGN KEY (vacation_type) REFERENCES VacationTypes(vacation_type_id)
);