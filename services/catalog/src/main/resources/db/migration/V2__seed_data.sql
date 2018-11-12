--catalog brand
insert into catalog_brand(brand) values('Azure');
insert into catalog_brand(brand) values('.NET');
insert into catalog_brand(brand) values('Visual Studio');
insert into catalog_brand(brand) values('SQL Server');
insert into catalog_brand(brand) values('Other');

--catalog Type
insert into catalog_type(type) values('Mug');
insert into catalog_type(type) values('T-Shirt');
insert into catalog_type(type) values('Sheet');
insert into catalog_type(type) values('USB Memory Stick');
insert into catalog_type(type) values('CatalogTypeTestOne');
insert into catalog_type(type) values('CatalogTypeTestTwo');

-- catalog items
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('.NET Bot Black Hoodie, and more', '.NET Bot Black Hoodie', 19.5, '1.png', 100, false, (select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('.NET Black & White Mug','.NET Black & White Mug',8.50,'2.png',89,true,(select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='Mug'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Prism White T-Shirt','Prism White T-Shirt',12,'3.png',56,false,(select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('.NET Foundation T-shirt','.NET Foundation T-shirt',12,'4.png',120,false, (select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Roslyn Red Sheet','Roslyn Red Sheet',8.5,'5.png',55,false, (select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='Sheet'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('.NET Blue Hoodie','.NET Blue Hoodie',12,'6.png',17,false, (select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Roslyn Red T-Shirt','Roslyn Red T-Shirt',12,'7.png',8,false, (select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Kudu Purple Hoodie','Kudu Purple Hoodie',8.5,'8.png',34,false, (select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='T-Shirt'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Cup<T> White Mug','Cup<T> White Mug',12,'9.png',76,false, (select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='Mug'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('.NET Foundation Sheet','.NET Foundation Sheet',12,'10.png',11,false, (select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='Sheet'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Cup<T> Sheet','Cup<T> Sheet',8.5,'11.png',3,false, (select brand_id from catalog_brand where brand ='.NET'), (select type_id from catalog_type where type ='Sheet'));
insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
values('Prism White TShirt','Prism White TShirt',12,'12.png',0,false,(select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='T-Shirt'));
-- insert into catalog_item(description, name, price, picture_filename, available_stock, on_reorder, catalog_brand_id, catalog_type_id)
-- values ('De los Palotes','pepito', 12, '12.png', 0, false, (select brand_id from catalog_brand where brand ='Other'), (select type_id from catalog_type where type ='Mug'));
