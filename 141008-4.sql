select prodno, pname, price
from products
where regexp_like(pname, 'A4')
/
