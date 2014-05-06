create table if not exists user (
    id int auto_increment primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    nickname varchar(255) not null,
    role varchar(255) not null,
    active int,
    activecode varchar(255)
);

create table if not exists product (
    id int auto_increment primary key,
    name varchar(255) not null,
    category varchar(255) not null,
    markedprice double,
    estoreprice double,
    pnum int,
    imgurl varchar(255) not null,
    description varchar(255) not null
);

create table if not exists orders(
    id varchar(255) primary key,
    totalprice double,
    receiverinfo varchar(255),
    paystate int,
    createtime timestamp not null,
    user_id int,
    constraint user_id_fk foreign key(user_id) references user(id)
);

create table if not exists orderitem(
   order_id varchar(255),
   product_id int,
   buynum int,
   constraint order_id_fk foreign key(order_id) references orders(id),
   constraint prodect_id_fk foreign key(product_id) references product(id)
);
