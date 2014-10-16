alter session set nls_date_format = 'DD-MON-RR'
/
alter session set nls_date_language = 'AMERICAN'
/
select ename, hiredate from employees where deptno = 10
/
