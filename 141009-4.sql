select prodno, pname, price
from products
where pname like '%100|%%' escape '|'
/
