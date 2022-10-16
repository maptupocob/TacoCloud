create table if not exists Taco_Order (
    id identity,
    delivery_name varchar(50) not null,
    delivery_address varchar(50) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Taco (
    id identity,
    name varchar(50) not null,
    taco_order bigint not null,
    taco_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists Ingredient_Ref (
    ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null
);


create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(20) not null,
    type varchar(20) not null
);

alter table Taco
    add foreign key (taco_order) references Taco_Order(id);

alter table Ingredient_Ref
    add foreign key (taco) references Taco(id);

alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
