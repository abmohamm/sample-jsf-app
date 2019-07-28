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
