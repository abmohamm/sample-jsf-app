select * from employees;

select first_name||' '||last_name as name from employees

select department_id,department_id*25 from employees;

select count(distinct department_id) from employees include null;

select distinct department_id from employees

select * from employees where salary<= 30000

select * from employees;

--Below query to will not work
select to_number(last_name) from employees

select * from employees where hire_date<'24-JUL-05'

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

