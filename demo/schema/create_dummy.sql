create table dummy (n INT);

insert into dummy values (1);

insert into dummy select * FROM dummy;