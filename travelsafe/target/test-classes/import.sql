insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional) values (1,'Sport','Sport',true)
insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional) values (2,'Starost','Age',true)
insert into type_of_risk(type_of_risk_id,name_en,name_srb,optional) values (3,'Region','Region',false)


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