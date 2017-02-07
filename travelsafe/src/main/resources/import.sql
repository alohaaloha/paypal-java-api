insert into price(price_id, amount, date_from, date_to, valute) values (1, 11, '2016-01-01', '2016-06-30', 'USD');
insert into price(price_id, amount, date_from, date_to, valute) values (2, 14, '2016-07-01', '2016-12-31', 'USD');
insert into price(price_id, amount, date_from, date_to, valute) values (3, 10, '2017-01-01', null, 'USD');


insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (1, "duration_ti", "Duration", "Trajanje", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (2, "region_ti", "Region", "Region", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (3, "number_of_people_ti", "Number of people", "Broj osoba", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (4, "age_ti", "Age", "Starost", false, false); #check this
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (5, "sport_ti", "Sport", "Sport", true, true); #check this
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (6, "max_amount_ti", "Max amount of insurance", "Najveća vrednost osiguranja", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (7, "surface_area_hi", "Surface area [m^2]", "Površina [m^2]", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (8, "age_hi", "Age", "Starost", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (9, "estimated_value_hi", "Estimated value", "Procenjena vrednost", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (10, "insurance_desc_hi", "Insurance description", "Opis osiguranja", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (11, "car_package_ci", "Packages", "Paketi", false, false);


insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (1, "any", 1, '2017-01-01', null, "Any", "Bilo koja", 1);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (2, "serbia", 2, '2017-01-01', null, "Serbia", "Srbija", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (3, "england", 3, '2017-01-01', null, "England", "Engleska", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (4, "france", 3.5, '2017-01-01', null, "France", "Francuska", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (5, "le_5", 2, '2017-01-01', null, "Five and less people", "Do pet osoba (uključujući)", 3);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (6, "gt_5", 1.5, '2017-01-01', null, "More than five people", "Više od pet osoba", 3);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (7, "1000usd", 1.5, '2017-01-01', null, "1000 USD", "1000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (8, "10000usd", 2, '2017-01-01', null, "10000 USD", "10000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (9, "30000usd", 2.5, '2017-01-01', null, "30000 USD", "30000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (10, "le_50m2", 1.5, '2017-01-01', null, "Less than or equal to 50 m^2", "Manje ili jednako od 50m^2", 7);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (11, "gt_50m2", 2, '2017-01-01', null, "More than 50 m^2", "Više od 50m^2", 7);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (12, "le_10", 2, '2017-01-01', null, "Less than or equal to 10 years", "Manje ili jednako od 10 godina", 8);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (13, "gt_10", 1.5, '2017-01-01', null, "More than 10 years", "Više od 10 godina", 8);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (14, "all", 0.1, '2017-01-01', null, "All", "Sve", 9);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (15, "fire", 1.2, '2017-01-01', null, "Fire", "Požar", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (16, "flood", 1.1, '2017-01-01', null, "Flood", "Poplava", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (17, "burglary", 1.12, '2017-01-01', null, "Burglary", "Provala", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (18, "towing", 1.12, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (19, "accommodation", 1.1, '2017-01-01', null, "Burglary", "Provala", 11); #
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (20, "repair", 1.11, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (21, "transport", 1.01, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (22, "football", 1.01, '2017-01-01', null, "Football", "Fudbal", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (23, "basketball", 1.01, '2017-01-01', null, "Basketball", "Kosarka", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (24, "skii", 1.15, '2017-01-01', null, "Skiing", "Skijanje", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (25, "handball", 1.02, '2017-01-01', null, "Handball", "Rukomet", 5);

#add items for age and sport of persons!
