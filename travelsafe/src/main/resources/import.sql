insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional,two_or_more) values (1,'Sport','Sport',true, TRUE )
insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional,two_or_more) values (2,'Age','Starost',true, FALSE )
insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional,two_or_more) values (3,'Region','Region',false,FALSE )


insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (1,2,'Football','Fudbal',1)
insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (2,5,'Skiing','Skijanje',1)
insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (3,1,'Baseball','Bejzbol',1)
insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (4,5,'Basketball','Kosarka',1)

insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (5,5,'Do 18','To 18',2)
insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (6,4,'Od 18 do 60','From 18 to 60',2)
insert into item(ITEM_ID, COEF, name_en,name_srb,type_of_risk) values (7,6,'Od 60','From 60',2)

insert into item(COEF, name_en,name_srb,type_of_risk) values (6,'Serbia','Srbija',3)
insert into item(COEF, name_en,name_srb,type_of_risk) values (6,'England','Engleska',3)
insert into item(COEF, name_en,name_srb,type_of_risk) values (6,'France','Francuska',3)
insert into item(COEF, name_en,name_srb,type_of_risk) values (6,'Canada','Kanada',3)
insert into item(COEF, name_en,name_srb,type_of_risk) values (6,'Netherlands','Holandija',3)

insert into price(PRICE_ID, AMOUNT, DATE_FROM, DATE_TO, VALUTE) values (1, 10, '2016-01-01', '2016-06-30', 'USD')
insert into price(PRICE_ID, AMOUNT, DATE_FROM, DATE_TO, VALUTE) values (2, 12, '2016-07-01', '2016-12-31', 'USD')
insert into price(PRICE_ID, AMOUNT, DATE_FROM, DATE_TO, VALUTE) values (3, 15, '2017-01-01', '2017-02-28', 'USD')
/*
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (1, 2, 'less_than_equal_five_duration', '2017-01-02', '2017-02-28')
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (2, 1.5, 'more_than_five_duration', '2017-01-02', '2017-02-28')

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (3, 1, 'Serbia', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (4, 1, 'England', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (5, 2.5, 'Germany', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (6, 1, 'Spain', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (7, 2.5, 'Netherlands', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (8, 3, 'France', '2017-01-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (9, 1, 'Russia', '2017-01-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (10, 0.9, 'more_than_five_people', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (11, 0.2, 'more_than_10000_max_amount', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (12, 0.2, 'surface_area_hi', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (13, 0.2, 'age_hi', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (14, 0.01, 'estimated_value_hi', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (14, 10, 'flood_hi', '2017-02-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (14, 15, 'fire_hi', '2017-02-01', null)

insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (15, 5, 'towing_service_ci', '2017-02-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (16, 12, 'alternative_transport_ci', '2017-02-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (17, 15, 'hotel_accommodation_ci', '2017-02-01', null)
insert into item(ITEM_ID, COEF, NAME, DATE_FROM, DATE_TO) values (18, 10, 'repair_ci', '2017-02-01', null)


insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (1, 'Srbija',' Serbia')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (2, 'Engleska','England')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (3, 'Nemačka','Germany')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (4, 'Italija','Italy')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (5, 'Španija','Spain')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (6, 'Holandija','Netherlands')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (7, 'Francuska','France')
insert into region(REGION_ID, SER_TRANSLATION, EN_TRANSLATION) values (8, 'Rusija','Russia')
*/
