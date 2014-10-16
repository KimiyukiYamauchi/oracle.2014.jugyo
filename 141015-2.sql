alter session set nls_date_format = 'RR-MM-DD'
/
alter session set nls_date_language = 'JAPANESE'
/
select ename, hiredate from employees where deptno = 10
/
