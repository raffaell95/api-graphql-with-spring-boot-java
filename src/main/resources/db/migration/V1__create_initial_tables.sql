create table customer (
    id    bigint auto_increment primary key,
    email varchar(255) null,
    name  varchar(255) null
);

create table product(
    id    bigint auto_increment primary key,
    name  varchar(255) null,
    amount double       not null
);

create table purchase
(
    id         bigint auto_increment primary key,
    date       datetime     null,
    quantity int          null,
    status     varchar(255) null,
    customer_id bigint       null,
    product_id bigint       null,
    constraint FK_PURCHASE_PRODUCT foreign key (product_id) references product (id),
    constraint FK_PURCHASE_CUSTOMER foreign key (customer_id) references customer (id)
);