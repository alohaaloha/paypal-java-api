# price
insert into price(price_id, amount, date_from, date_to, valute) values (1, 11, '2016-01-01', '2016-06-30', 'USD');
insert into price(price_id, amount, date_from, date_to, valute) values (2, 14, '2016-07-01', '2016-12-31', 'USD');
insert into price(price_id, amount, date_from, date_to, valute) values (3, 10, '2017-01-01', null, 'USD');


# type_of_risk
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (1, "duration_ti", "Duration", "Trajanje", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (2, "region_ti", "Region", "Region", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (3, "number_of_people_ti", "Number of people", "Broj osoba", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (4, "age_ti", "Age", "Starost", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (5, "sport_ti", "Sport", "Sport", true, true);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (6, "max_amount_ti", "Max amount of insurance", "Najveća vrednost osiguranja", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (7, "surface_area_hi", "Surface area [m^2]", "Površina [m^2]", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (8, "age_hi", "Age", "Starost", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (9, "estimated_value_hi", "Estimated value", "Procenjena vrednost", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (10, "insurance_desc_hi", "Insurance description", "Opis osiguranja", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (11, "car_package_ci", "Packages", "Paketi", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (12, "duration_ci", "Duration", "Trajanje", false, false);
insert into type_of_risk(type_of_risk_id, code, name_en, name_srb, optional, two_or_more) values (13, "duration_hi", "Duration", "Trajanje", false, false);


# item
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (1, "any", 1, '2017-01-01', null, "Any", "Bilo koja", 1);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (2, "serbia", 2, '2017-01-01', null, "Serbia", "Srbija", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (3, "england", 3, '2017-01-01', null, "England", "Engleska", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (4, "france", 3.5, '2017-01-01', null, "France", "Francuska", 2);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (5, "le_5", 1, '2017-01-01', null, "Five and less persons", "Do pet osoba (uključujući)", 3);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (6, "gt_5", 0.9, '2017-01-01', null, "More than five persons", "Više od pet osoba", 3);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (7, "football", 1.01, '2017-01-01', null, "Football", "Fudbal", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (8, "basketball", 1.01, '2017-01-01', null, "Basketball", "Kosarka", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (9, "skiing", 1.15, '2017-01-01', null, "Skiing", "Skijanje", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (10, "handball", 1.02, '2017-01-01', null, "Handball", "Rukomet", 5);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (11, "1000usd", 1.5, '2017-01-01', null, "1000 USD", "1000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (12, "5000usd", 1.75, '2017-01-01', null, "5000 USD", "5000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (13, "10000usd", 2, '2017-01-01', null, "10000 USD", "10000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (14, "30000usd", 2.5, '2017-01-01', null, "30000 USD", "30000 USD", 6);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (15, "le_50m2", 0.15, '2017-01-01', null, "Less than or equal to 50 m^2", "Manje ili jednako od 50 m^2", 7);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (16, "gt_50m2", 0.25, '2017-01-01', null, "More than 50 m^2", "Više od 50 m^2", 7);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (17, "le_10", 0.9, '2017-01-01', null, "Less than or equal to 10 years", "Manje ili jednako od 10 godina", 8);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (18, "gt_10", 0.7, '2017-01-01', null, "More than 10 years", "Više od 10 godina", 8);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (19, "all", 0.0001, '2017-01-01', null, "All", "Sve", 9);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (20, "fire", 1.2, '2017-01-01', null, "Fire", "Požar", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (21, "flood", 1.1, '2017-01-01', null, "Flood", "Poplava", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (22, "burglary", 1.12, '2017-01-01', null, "Burglary", "Provala", 10);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (23, "towing", 1.12, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (24, "accommodation", 1.1, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (25, "repair", 1.11, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (26, "transport", 1.01, '2017-01-01', null, "Burglary", "Provala", 11);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (27, "any", 1.25, '2017-01-01', null, "Any", "Bilo koja", 12);
insert into item(item_id, code, coef, date_from, date_to, name_en, name_srb, type_of_risk) values (28, "any", 1.5, '2017-01-01', null, "Any", "Bilo koja", 13);

#add items for age of persons!
