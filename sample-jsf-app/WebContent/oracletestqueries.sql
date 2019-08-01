select * from employees;

select * from employees where employee_id in (101,102,103)

select * from employees where not salary>=10000 and salary<=20000

select * from employees where lower(last_name) like 'a%'

select * from employees where department_id is null

select initcap('abid mohammad') from dual

select sysdate-5 from dual;

select first_name||' '||last_name,rpad(substr(phone_number,0,3),10,'*') from employees where length(first_name)=5

select first_name||' '||last_name as name from employees

select department_id,department_id*25 from employees;

select count(distinct department_id) from employees include null;

select distinct department_id from employees

select * from employees where salary<= 30000

select * from employees;

--Below query to will not work
select to_number(last_name) from employees

select * from employees where hire_date<'24-JUL-05'

select round(to_date('24-Aug-17'),'year') from dual;

select next_day('31-JUL-2019','Monday') from dual

select * from employees where hire_date<to_date('24-07-05','dd-mm-yy')

select * from tab

selet SQRT(81) from dual

select * from employees;

select * from employees where first_name like '_%a'

select distinct manager_id from employees

desc employees

select * from employees;

--single row functions
select lower(first_name) from employees;

select * from employees where lower(first_name) = 'ellen'

select initcap(first_name) from employees;

desc dual;

select round(months_between(sysdate,hire_date),0) from employees;

select to_char(hire_date,'Day DD MM,YYYY') as hire_date from employees;

select to_timestamp(sysdate+2) from dual;

select next_day(sysdate,'tuesday') from dual

select * from dual;

select current_timestamp from dual;

select * from EMP_DETAILS_VIEW

select round(10.266,2) from dual;

select trunc(10.266,2) from dual;

select sysdate from dual;

desc user_constraints

select constraint_name,constraint_type from user_constraints where table_name='employees'

desc user_cons_columns 

select max(salary) from employees;

select max(salary) from employees where salary<(select max(salary) from employees)

select distinct(salary) from employees order by salary desc limit 0,1;

SELECT e.first_name||' '||e.last_name as full_name,ROW_NUMBER() OVER (ORDER BY salary DESC) row_num FROM Employees e

SELECT * FROM ( SELECT e.*,ROW_NUMBER() OVER (ORDER BY salary DESC) row_num FROM Employees e ) WHERE row_num = 2; 

select* from employees order by salary desc;
  
SELECT salary FROM Employees  ORDER BY salary DESC

SELECT salary FROM (SELECT salary FROM Employees  ORDER BY salary DESC) WHERE ROWNUM=2;

select * from employees;

select * from NEWTABLE;

merge into NEWTABLE n using EMPLOYEES e on (e.employee_id=n.employee_id)
when matched then
update set n.first_name=e.first_name,n.last_name=e.last_name,n.email=e.email,n.phone_number=e.phone_number,n.hire_date=e.hire_date,
n.job_id=e.job_id,n.salary=e.salary,n.commission_pct=e.commission_pct,n.manager_id=e.manager_id,n.department_id=e.department_id,
n.creat_upd_date=e.creat_upd_date
when not matched then
insert values(e.employee_id,e.first_name,e.last_name,e.email,e.phone_number,e.hire_date,e.job_id,e.salary,e.commission_pct,
e.manager_id,e.department_id,current_timestamp)

select * from employees;

--Inner Join
select e.employee_id,d.department_id,e.first_name||' '||e.last_name as full_name from employees e,departments d where e.department_id = d.department_id;

--Left Outer Join
select e.employee_id,d.department_id,e.first_name||' '||e.last_name as full_name from employees e,departments d where e.department_id = d.department_id(+);

--Self Join
select m.first_name as manager,w.first_name as worker from employees m, employees w where w.manager_id = w.manager_id; 

--Join by using clause
select first_name||' '||last_name as full_name,department_name from employees join departments using(department_id)

--Join by ON clause
select first_name||' '||last_name as full_name,department_name from employees join departments on employees.department_id = departments.department_id;

select to_char(sysdate,'DD-MONTH-YYYY DAY') as today_date from dual;

select sum(salary),department_id,job_id from employees group by department_id,job_id having sum(salary) >= 50000 order by 2,3;

select * from employees

update employees set salary = salary+10000 where department_id = all(select department_id from employees where department_id = 101)

select to_char(sysdate,'HH:MM:SS') from dual

select employee_id,first_name||' '||last_name as Name from employees where lower(to_char(hire_date,'day')) = 'wednesday'

select first_name||' '||last_name as full_name,salary,department_id from employees where (department_id,manager_id) in 
(select department_id,manager_id from employees where salary >= 10000);

create sequence sample_sequence start with 1 increment by 1 maxvalue 1000 cycle cache 10;

desc user_sequences;

select max_value,min_value from user_sequences where sequence_name = 'SAMPLE_SEQUENCE';

select SAMPLE_SEQUENCE.CURRVAL,SAMPLE_SEQUENCE.NEXTVAL from dual;

create index emp_fname_idx on employees(first_name);

create index emp_lname_idx on employees(last_name);

