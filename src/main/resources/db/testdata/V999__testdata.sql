INSERT INTO service_point (service_point_id, tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (1, 1, '1008901012345678901234', '123 MAIN ST', 'HOUSTON', 'TX', '77002', 'HARRIS', '01', 'Active', 'Residential', 'FAN', 'FANNIN REIT', TRUE, 'Residential', 'Y', 'AMSM', 'N');

INSERT INTO service_point (service_point_id, tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (2, 1, '1008901098765432109876', '456 OAK AVE', 'CYPRESS', 'TX', '77429', 'HARRIS', '21', 'Active', 'Small Non-Residential', 'KL', 'KLUGE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

INSERT INTO service_point (service_point_id, tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (3, 1, '1008901055555555555555', '789 ELM RD', 'BAYTOWN', 'TX', '77520', 'CHAMBERS', '19', 'Inactive', 'Residential', '_CD', 'CEDAR BAYOU OLD', FALSE, 'Residential', 'N', NULL, 'N');

-- CenterPoint (tdsp_id=1) - Houston metro
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(1, '1008901000000000001001', '101 WESTHEIMER RD', 'HOUSTON', 'TX', '77006', 'HARRIS', '02', 'Active', 'Residential', 'FAN', 'FANNIN REIT', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001002', '202 MONTROSE BLVD', 'HOUSTON', 'TX', '77006', 'HARRIS', '02', 'Active', 'Small Non-Residential', 'FAN', 'FANNIN REIT', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001003', '303 RICHMOND AVE', 'HOUSTON', 'TX', '77006', 'HARRIS', '03', 'Active', 'Residential', 'FAN', 'FANNIN REIT', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001004', '404 SHEPHERD DR', 'HOUSTON', 'TX', '77007', 'HARRIS', '03', 'Active', 'Residential', 'HGT', 'HEIGHTS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001005', '505 WASHINGTON AVE', 'HOUSTON', 'TX', '77007', 'HARRIS', '04', 'Active', 'Small Non-Residential', 'HGT', 'HEIGHTS', TRUE, 'Small Non-Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001006', '606 MEMORIAL DR', 'HOUSTON', 'TX', '77024', 'HARRIS', '04', 'Active', 'Residential', 'MEM', 'MEMORIAL', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001007', '707 VOSS RD', 'HOUSTON', 'TX', '77024', 'HARRIS', '05', 'Active', 'Residential', 'MEM', 'MEMORIAL', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001008', '808 GESSNER RD', 'HOUSTON', 'TX', '77024', 'HARRIS', '05', 'Active', 'Large Non-Residential', 'MEM', 'MEMORIAL', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001009', '909 DAIRY ASHFORD RD', 'HOUSTON', 'TX', '77079', 'HARRIS', '06', 'Active', 'Residential', 'DAI', 'DAIRY ASHFORD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001010', '1010 ELDRIDGE PKWY', 'HOUSTON', 'TX', '77077', 'HARRIS', '06', 'Active', 'Residential', 'DAI', 'DAIRY ASHFORD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001011', '1111 BELLAIRE BLVD', 'HOUSTON', 'TX', '77072', 'HARRIS', '07', 'Active', 'Small Non-Residential', 'BEL', 'BELLAIRE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001012', '1212 BISSONNET ST', 'HOUSTON', 'TX', '77005', 'HARRIS', '07', 'Active', 'Residential', 'BEL', 'BELLAIRE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001013', '1313 UNIVERSITY BLVD', 'HOUSTON', 'TX', '77005', 'HARRIS', '08', 'Active', 'Residential', 'BEL', 'BELLAIRE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001014', '1414 HOLCOMBE BLVD', 'HOUSTON', 'TX', '77030', 'HARRIS', '08', 'Active', 'Large Non-Residential', 'TMC', 'TEXAS MED CTR', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001015', '1515 BRAESWOOD BLVD', 'HOUSTON', 'TX', '77030', 'HARRIS', '09', 'Active', 'Residential', 'TMC', 'TEXAS MED CTR', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001016', '1616 SOUTH LOOP W', 'HOUSTON', 'TX', '77054', 'HARRIS', '09', 'Active', 'Small Non-Residential', 'SLP', 'SOUTH LOOP', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001017', '1717 ALMEDA RD', 'HOUSTON', 'TX', '77054', 'HARRIS', '10', 'Active', 'Residential', 'SLP', 'SOUTH LOOP', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001018', '1818 TELEPHONE RD', 'HOUSTON', 'TX', '77023', 'HARRIS', '10', 'De-Energized', 'Residential', 'SLP', 'SOUTH LOOP', TRUE, 'Residential', 'N', NULL, 'N'),
(1, '1008901000000000001019', '1919 NAVIGATION BLVD', 'HOUSTON', 'TX', '77003', 'HARRIS', '11', 'Active', 'Small Non-Residential', 'NAV', 'NAVIGATION', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001020', '2020 CANAL ST', 'HOUSTON', 'TX', '77003', 'HARRIS', '11', 'Active', 'Residential', 'NAV', 'NAVIGATION', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001021', '100 BARKER CYPRESS RD', 'CYPRESS', 'TX', '77433', 'HARRIS', '12', 'Active', 'Residential', 'CYP', 'CYPRESS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001022', '200 FRYRD', 'KATY', 'TX', '77449', 'HARRIS', '12', 'Active', 'Residential', 'KTY', 'KATY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001023', '300 MASON RD', 'KATY', 'TX', '77450', 'HARRIS', '13', 'Active', 'Residential', 'KTY', 'KATY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001024', '400 WESTGREEN BLVD', 'KATY', 'TX', '77450', 'HARRIS', '13', 'Active', 'Small Non-Residential', 'KTY', 'KATY', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001025', '500 SPRING CYPRESS RD', 'SPRING', 'TX', '77373', 'HARRIS', '14', 'Active', 'Residential', 'SPR', 'SPRING', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001026', '600 LOUETTA RD', 'SPRING', 'TX', '77379', 'HARRIS', '14', 'Active', 'Residential', 'SPR', 'SPRING', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001027', '700 KUYKENDAHL RD', 'SPRING', 'TX', '77379', 'HARRIS', '15', 'Active', 'Residential', 'SPR', 'SPRING', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001028', '800 FM 1960 RD W', 'HOUSTON', 'TX', '77069', 'HARRIS', '15', 'Active', 'Small Non-Residential', 'WIL', 'WILLOWBROOK', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001029', '900 JONES RD', 'HOUSTON', 'TX', '77070', 'HARRIS', '16', 'Active', 'Residential', 'WIL', 'WILLOWBROOK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001030', '1000 GRANT RD', 'HOUSTON', 'TX', '77070', 'HARRIS', '16', 'Inactive', 'Residential', 'WIL', 'WILLOWBROOK', FALSE, 'Residential', 'N', NULL, 'N'),
(1, '1008901000000000001031', '110 PEARLAND PKWY', 'PEARLAND', 'TX', '77581', 'BRAZORIA', '17', 'Active', 'Residential', 'PRL', 'PEARLAND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001032', '220 BROADWAY ST', 'PEARLAND', 'TX', '77581', 'BRAZORIA', '17', 'Active', 'Small Non-Residential', 'PRL', 'PEARLAND', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001033', '330 DIXIE FARM RD', 'PEARLAND', 'TX', '77584', 'BRAZORIA', '18', 'Active', 'Residential', 'PRL', 'PEARLAND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001034', '440 BAY AREA BLVD', 'WEBSTER', 'TX', '77598', 'HARRIS', '18', 'Active', 'Large Non-Residential', 'WEB', 'WEBSTER', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001035', '550 NASA PKWY', 'WEBSTER', 'TX', '77598', 'HARRIS', '19', 'Active', 'Residential', 'WEB', 'WEBSTER', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001036', '660 EL DORADO BLVD', 'HOUSTON', 'TX', '77062', 'HARRIS', '19', 'Active', 'Residential', 'WEB', 'WEBSTER', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001037', '770 GARTH RD', 'BAYTOWN', 'TX', '77521', 'HARRIS', '20', 'Active', 'Small Non-Residential', '_CD', 'CEDAR BAYOU OLD', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001038', '880 W BAKER RD', 'BAYTOWN', 'TX', '77521', 'HARRIS', '20', 'Active', 'Residential', '_CD', 'CEDAR BAYOU OLD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001039', '990 MARKET ST', 'BAYTOWN', 'TX', '77520', 'CHAMBERS', '21', 'De-Energized', 'Residential', '_CD', 'CEDAR BAYOU OLD', TRUE, 'Residential', 'N', NULL, 'Y'),
(1, '1008901000000000001040', '1100 TEXAS AVE', 'BAYTOWN', 'TX', '77520', 'CHAMBERS', '21', 'Active', 'Residential', '_CD', 'CEDAR BAYOU OLD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001041', '111 LEAGUE CITY PKWY', 'LEAGUE CITY', 'TX', '77573', 'GALVESTON', '01', 'Active', 'Residential', 'LGC', 'LEAGUE CITY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001042', '222 MARINA BAY DR', 'LEAGUE CITY', 'TX', '77573', 'GALVESTON', '02', 'Active', 'Residential', 'LGC', 'LEAGUE CITY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001043', '333 CALDER RD', 'LEAGUE CITY', 'TX', '77573', 'GALVESTON', '03', 'Active', 'Small Non-Residential', 'LGC', 'LEAGUE CITY', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001044', '444 FRIENDSWOOD DR', 'FRIENDSWOOD', 'TX', '77546', 'GALVESTON', '04', 'Active', 'Residential', 'FRD', 'FRIENDSWOOD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001045', '555 EDGEWATER DR', 'FRIENDSWOOD', 'TX', '77546', 'GALVESTON', '05', 'Active', 'Residential', 'FRD', 'FRIENDSWOOD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001046', '666 SUGAR CREEK BLVD', 'SUGAR LAND', 'TX', '77478', 'FORT BEND', '06', 'Active', 'Residential', 'SGL', 'SUGAR LAND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001047', '777 FIRST COLONY BLVD', 'SUGAR LAND', 'TX', '77479', 'FORT BEND', '07', 'Active', 'Residential', 'SGL', 'SUGAR LAND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001048', '888 WILLIAMS TRACE BLVD', 'SUGAR LAND', 'TX', '77478', 'FORT BEND', '08', 'Active', 'Large Non-Residential', 'SGL', 'SUGAR LAND', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(1, '1008901000000000001049', '999 DULLES AVE', 'SUGAR LAND', 'TX', '77478', 'FORT BEND', '09', 'Active', 'Residential', 'SGL', 'SUGAR LAND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(1, '1008901000000000001050', '1050 HWY 6', 'MISSOURI CITY', 'TX', '77459', 'FORT BEND', '10', 'Active', 'Small Non-Residential', 'MCI', 'MISSOURI CITY', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

-- Oncor (tdsp_id=2) - DFW metro
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(2, '1044372000000000002001', '100 MAIN ST', 'DALLAS', 'TX', '75201', 'DALLAS', '01', 'Active', 'Residential', 'DTC', 'DOWNTOWN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002002', '200 ELM ST', 'DALLAS', 'TX', '75201', 'DALLAS', '01', 'Active', 'Large Non-Residential', 'DTC', 'DOWNTOWN', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002003', '300 COMMERCE ST', 'DALLAS', 'TX', '75202', 'DALLAS', '02', 'Active', 'Small Non-Residential', 'DTC', 'DOWNTOWN', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002004', '400 SWISS AVE', 'DALLAS', 'TX', '75204', 'DALLAS', '02', 'Active', 'Residential', 'EAD', 'EAST DALLAS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002005', '500 GASTON AVE', 'DALLAS', 'TX', '75214', 'DALLAS', '03', 'Active', 'Residential', 'EAD', 'EAST DALLAS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002006', '600 GREENVILLE AVE', 'DALLAS', 'TX', '75206', 'DALLAS', '03', 'Active', 'Small Non-Residential', 'UPK', 'UPPER GREENVILLE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002007', '700 LOVERS LN', 'DALLAS', 'TX', '75209', 'DALLAS', '04', 'Active', 'Residential', 'UPK', 'UPPER GREENVILLE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002008', '800 PRESTON RD', 'DALLAS', 'TX', '75230', 'DALLAS', '04', 'Active', 'Residential', 'PRN', 'PRESTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002009', '900 FOREST LN', 'DALLAS', 'TX', '75243', 'DALLAS', '05', 'Active', 'Residential', 'PRN', 'PRESTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002010', '1000 COIT RD', 'PLANO', 'TX', '75075', 'COLLIN', '05', 'Active', 'Residential', 'PLN', 'PLANO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002011', '1100 INDEPENDENCE PKWY', 'PLANO', 'TX', '75075', 'COLLIN', '06', 'Active', 'Residential', 'PLN', 'PLANO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002012', '1200 PARK BLVD', 'PLANO', 'TX', '75074', 'COLLIN', '06', 'Active', 'Small Non-Residential', 'PLN', 'PLANO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002013', '1300 LEGACY DR', 'PLANO', 'TX', '75024', 'COLLIN', '07', 'Active', 'Large Non-Residential', 'PLN', 'PLANO', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002014', '1400 ALMA DR', 'PLANO', 'TX', '75025', 'COLLIN', '07', 'Active', 'Residential', 'PLN', 'PLANO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002015', '1500 MCKINNEY AVE', 'MCKINNEY', 'TX', '75069', 'COLLIN', '08', 'Active', 'Residential', 'MCK', 'MCKINNEY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002016', '1600 EL DORADO PKWY', 'MCKINNEY', 'TX', '75070', 'COLLIN', '08', 'Active', 'Residential', 'MCK', 'MCKINNEY', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002017', '1700 VIRGINIA PKWY', 'MCKINNEY', 'TX', '75071', 'COLLIN', '09', 'Active', 'Small Non-Residential', 'MCK', 'MCKINNEY', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002018', '1800 STONEBRIDGE DR', 'MCKINNEY', 'TX', '75072', 'COLLIN', '09', 'De-Energized', 'Residential', 'MCK', 'MCKINNEY', TRUE, 'Residential', 'N', NULL, 'N'),
(2, '1044372000000000002019', '1900 UNIVERSITY DR', 'FRISCO', 'TX', '75034', 'COLLIN', '10', 'Active', 'Residential', 'FRC', 'FRISCO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002020', '2000 MAIN ST', 'FRISCO', 'TX', '75034', 'COLLIN', '10', 'Active', 'Small Non-Residential', 'FRC', 'FRISCO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002021', '2100 TEEL PKWY', 'FRISCO', 'TX', '75033', 'DENTON', '11', 'Active', 'Residential', 'FRC', 'FRISCO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002022', '2200 DALLAS PKWY', 'FRISCO', 'TX', '75034', 'COLLIN', '11', 'Active', 'Large Non-Residential', 'FRC', 'FRISCO', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002023', '2300 HICKORY CREEK RD', 'DENTON', 'TX', '76210', 'DENTON', '12', 'Active', 'Residential', 'DEN', 'DENTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002024', '2400 TEASLEY LN', 'DENTON', 'TX', '76210', 'DENTON', '12', 'Active', 'Residential', 'DEN', 'DENTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002025', '2500 UNIVERSITY DR', 'DENTON', 'TX', '76201', 'DENTON', '13', 'Active', 'Small Non-Residential', 'DEN', 'DENTON', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002026', '100 W WEATHERFORD ST', 'FORT WORTH', 'TX', '76102', 'TARRANT', '14', 'Active', 'Residential', 'FTW', 'FORT WORTH', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002027', '200 MAIN ST', 'FORT WORTH', 'TX', '76102', 'TARRANT', '14', 'Active', 'Large Non-Residential', 'FTW', 'FORT WORTH', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002028', '300 CAMP BOWIE BLVD', 'FORT WORTH', 'TX', '76107', 'TARRANT', '15', 'Active', 'Small Non-Residential', 'FTW', 'FORT WORTH', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002029', '400 MAGNOLIA AVE', 'FORT WORTH', 'TX', '76104', 'TARRANT', '15', 'Active', 'Residential', 'FTW', 'FORT WORTH', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002030', '500 W 7TH ST', 'FORT WORTH', 'TX', '76102', 'TARRANT', '16', 'De-Energized', 'Residential', 'FTW', 'FORT WORTH', TRUE, 'Residential', 'N', NULL, 'N'),
(2, '1044372000000000002031', '600 COLLEYVILLE BLVD', 'COLLEYVILLE', 'TX', '76034', 'TARRANT', '16', 'Active', 'Residential', 'CNE', 'COLLEYVILLE NE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002032', '700 GRAPEVINE HWY', 'GRAPEVINE', 'TX', '76051', 'TARRANT', '17', 'Active', 'Small Non-Residential', 'CNE', 'COLLEYVILLE NE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002033', '800 BEDFORD RD', 'BEDFORD', 'TX', '76022', 'TARRANT', '17', 'Active', 'Residential', 'CNE', 'COLLEYVILLE NE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002034', '900 PRECINCT LINE RD', 'HURST', 'TX', '76053', 'TARRANT', '18', 'Active', 'Residential', 'HEB', 'HEB', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002035', '1000 W PIPELINE RD', 'HURST', 'TX', '76053', 'TARRANT', '18', 'Active', 'Residential', 'HEB', 'HEB', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002036', '1100 EULESS BLVD', 'EULESS', 'TX', '76039', 'TARRANT', '19', 'Active', 'Small Non-Residential', 'HEB', 'HEB', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002037', '1200 PIONEER PKWY', 'ARLINGTON', 'TX', '76013', 'TARRANT', '19', 'Active', 'Residential', 'ARL', 'ARLINGTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002038', '1300 COOPER ST', 'ARLINGTON', 'TX', '76011', 'TARRANT', '20', 'Active', 'Residential', 'ARL', 'ARLINGTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002039', '1400 COLLINS ST', 'ARLINGTON', 'TX', '76011', 'TARRANT', '20', 'Active', 'Large Non-Residential', 'ARL', 'ARLINGTON', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002040', '1500 NOLEN DR', 'ARLINGTON', 'TX', '76012', 'TARRANT', '21', 'Inactive', 'Residential', 'ARL', 'ARLINGTON', FALSE, 'Residential', 'N', NULL, 'N'),
(2, '1044372000000000002041', '1600 GREEN OAKS BLVD', 'ARLINGTON', 'TX', '76013', 'TARRANT', '21', 'Active', 'Residential', 'ARL', 'ARLINGTON', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002042', '1700 WAXAHACHIE ST', 'WAXAHACHIE', 'TX', '75165', 'ELLIS', '01', 'Active', 'Residential', 'WAX', 'WAXAHACHIE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002043', '1800 FERRIS AVE', 'WAXAHACHIE', 'TX', '75165', 'ELLIS', '02', 'Active', 'Small Non-Residential', 'WAX', 'WAXAHACHIE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(2, '1044372000000000002044', '1900 OVILLA RD', 'MIDLOTHIAN', 'TX', '76065', 'ELLIS', '03', 'Active', 'Residential', 'WAX', 'WAXAHACHIE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002045', '2000 MANSFIELD HWY', 'MANSFIELD', 'TX', '76063', 'TARRANT', '04', 'Active', 'Residential', 'MAN', 'MANSFIELD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002046', '2100 DEBBIE LN', 'MANSFIELD', 'TX', '76063', 'TARRANT', '05', 'Active', 'Residential', 'MAN', 'MANSFIELD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002047', '2200 BROAD ST', 'MANSFIELD', 'TX', '76063', 'TARRANT', '06', 'De-Energized', 'Small Non-Residential', 'MAN', 'MANSFIELD', TRUE, 'Small Non-Residential', 'N', NULL, 'Y'),
(2, '1044372000000000002048', '2300 MATLOCK RD', 'MANSFIELD', 'TX', '76063', 'TARRANT', '07', 'Active', 'Residential', 'MAN', 'MANSFIELD', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002049', '2400 GARDEN RIDGE BLVD', 'FLOWER MOUND', 'TX', '75028', 'DENTON', '08', 'Active', 'Residential', 'FLM', 'FLOWER MOUND', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(2, '1044372000000000002050', '2500 LONG PRAIRIE RD', 'FLOWER MOUND', 'TX', '75022', 'DENTON', '09', 'Active', 'Small Non-Residential', 'FLM', 'FLOWER MOUND', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

-- AEP Central (tdsp_id=3) - South/West Texas
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(3, '1007454000000000003001', '100 HOUSTON ST', 'SAN ANTONIO', 'TX', '78205', 'BEXAR', '01', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003002', '200 COMMERCE ST', 'SAN ANTONIO', 'TX', '78205', 'BEXAR', '02', 'Active', 'Large Non-Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003003', '300 MARKET ST', 'SAN ANTONIO', 'TX', '78205', 'BEXAR', '03', 'Active', 'Small Non-Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003004', '400 BROADWAY ST', 'SAN ANTONIO', 'TX', '78209', 'BEXAR', '04', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003005', '500 HILDEBRAND AVE', 'SAN ANTONIO', 'TX', '78212', 'BEXAR', '05', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003006', '600 FREDERICKSBURG RD', 'SAN ANTONIO', 'TX', '78201', 'BEXAR', '06', 'De-Energized', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'N', NULL, 'N'),
(3, '1007454000000000003007', '700 BANDERA RD', 'SAN ANTONIO', 'TX', '78228', 'BEXAR', '07', 'Active', 'Small Non-Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003008', '800 ZARZAMORA ST', 'SAN ANTONIO', 'TX', '78207', 'BEXAR', '08', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003009', '900 PRESA ST', 'SAN ANTONIO', 'TX', '78210', 'BEXAR', '09', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003010', '1000 NOGALITOS ST', 'SAN ANTONIO', 'TX', '78204', 'BEXAR', '10', 'Active', 'Residential', 'SAT', 'SAN ANTONIO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003011', '100 N CHAPARRAL ST', 'CORPUS CHRISTI', 'TX', '78401', 'NUECES', '11', 'Active', 'Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003012', '200 SHORELINE BLVD', 'CORPUS CHRISTI', 'TX', '78401', 'NUECES', '12', 'Active', 'Large Non-Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003013', '300 OCEAN DR', 'CORPUS CHRISTI', 'TX', '78404', 'NUECES', '13', 'Active', 'Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003014', '400 STAPLES ST', 'CORPUS CHRISTI', 'TX', '78401', 'NUECES', '14', 'Active', 'Small Non-Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003015', '500 AIRLINE RD', 'CORPUS CHRISTI', 'TX', '78412', 'NUECES', '15', 'Active', 'Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003016', '600 S PADRE ISLAND DR', 'CORPUS CHRISTI', 'TX', '78416', 'NUECES', '16', 'Inactive', 'Residential', 'CCR', 'CORPUS CHRISTI', FALSE, 'Residential', 'N', NULL, 'N'),
(3, '1007454000000000003017', '700 LEOPARD ST', 'CORPUS CHRISTI', 'TX', '78408', 'NUECES', '17', 'Active', 'Small Non-Residential', 'CCR', 'CORPUS CHRISTI', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003018', '100 E CANO ST', 'EDINBURG', 'TX', '78539', 'HIDALGO', '01', 'Active', 'Residential', 'EDI', 'EDINBURG', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(3, '1007454000000000003019', '200 S CLOSNER BLVD', 'EDINBURG', 'TX', '78539', 'HIDALGO', '02', 'Active', 'Small Non-Residential', 'EDI', 'EDINBURG', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(3, '1007454000000000003020', '300 E UNIVERSITY DR', 'EDINBURG', 'TX', '78539', 'HIDALGO', '03', 'Active', 'Residential', 'EDI', 'EDINBURG', TRUE, 'Residential', 'Y', 'AMSM', 'N');

-- AEP North (tdsp_id=4) - North/West Texas
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(4, '1007453000000000004001', '100 PINE ST', 'ABILENE', 'TX', '79601', 'TAYLOR', '01', 'Active', 'Residential', 'ABL', 'ABILENE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004002', '200 GRAPE ST', 'ABILENE', 'TX', '79601', 'TAYLOR', '02', 'Active', 'Small Non-Residential', 'ABL', 'ABILENE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(4, '1007453000000000004003', '300 BUTTERNUT ST', 'ABILENE', 'TX', '79602', 'TAYLOR', '03', 'Active', 'Residential', 'ABL', 'ABILENE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004004', '400 TREADAWAY BLVD', 'ABILENE', 'TX', '79602', 'TAYLOR', '04', 'De-Energized', 'Residential', 'ABL', 'ABILENE', TRUE, 'Residential', 'N', NULL, 'N'),
(4, '1007453000000000004005', '500 S 14TH ST', 'ABILENE', 'TX', '79602', 'TAYLOR', '05', 'Active', 'Residential', 'ABL', 'ABILENE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004006', '100 E WALKER ST', 'BRECKENRIDGE', 'TX', '76424', 'STEPHENS', '06', 'Active', 'Residential', 'BRK', 'BRECKENRIDGE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004007', '200 W ELM ST', 'BRECKENRIDGE', 'TX', '76424', 'STEPHENS', '07', 'Active', 'Small Non-Residential', 'BRK', 'BRECKENRIDGE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(4, '1007453000000000004008', '300 MOCKINGBIRD LN', 'SAN ANGELO', 'TX', '76901', 'TOM GREEN', '08', 'Active', 'Residential', 'SAN', 'SAN ANGELO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004009', '400 BEAUREGARD AVE', 'SAN ANGELO', 'TX', '76903', 'TOM GREEN', '09', 'Active', 'Residential', 'SAN', 'SAN ANGELO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004010', '500 KNICKERBOCKER RD', 'SAN ANGELO', 'TX', '76904', 'TOM GREEN', '10', 'Active', 'Large Non-Residential', 'SAN', 'SAN ANGELO', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(4, '1007453000000000004011', '600 SHERWOOD WAY', 'SAN ANGELO', 'TX', '76901', 'TOM GREEN', '11', 'Inactive', 'Residential', 'SAN', 'SAN ANGELO', FALSE, 'Residential', 'N', NULL, 'N'),
(4, '1007453000000000004012', '700 BRYANT BLVD', 'SAN ANGELO', 'TX', '76903', 'TOM GREEN', '12', 'Active', 'Small Non-Residential', 'SAN', 'SAN ANGELO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(4, '1007453000000000004013', '800 LOOP 306', 'SAN ANGELO', 'TX', '76904', 'TOM GREEN', '13', 'Active', 'Residential', 'SAN', 'SAN ANGELO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004014', '900 N CHADBOURNE ST', 'SAN ANGELO', 'TX', '76903', 'TOM GREEN', '14', 'Active', 'Residential', 'SAN', 'SAN ANGELO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(4, '1007453000000000004015', '100 E INDUSTRIAL BLVD', 'BROWNWOOD', 'TX', '76801', 'BROWN', '15', 'Active', 'Residential', 'BRW', 'BROWNWOOD', TRUE, 'Residential', 'Y', 'AMSM', 'N');

-- TNMP (tdsp_id=5)
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(5, '1042140000000000005001', '100 W SAN ANTONIO ST', 'NEW BRAUNFELS', 'TX', '78130', 'COMAL', '01', 'Active', 'Residential', 'NBR', 'NEW BRAUNFELS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005002', '200 LANDA ST', 'NEW BRAUNFELS', 'TX', '78130', 'COMAL', '02', 'Active', 'Small Non-Residential', 'NBR', 'NEW BRAUNFELS', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(5, '1042140000000000005003', '300 S SEGUIN AVE', 'NEW BRAUNFELS', 'TX', '78130', 'COMAL', '03', 'Active', 'Residential', 'NBR', 'NEW BRAUNFELS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005004', '400 COMMON ST', 'NEW BRAUNFELS', 'TX', '78130', 'COMAL', '04', 'De-Energized', 'Residential', 'NBR', 'NEW BRAUNFELS', TRUE, 'Residential', 'N', NULL, 'N'),
(5, '1042140000000000005005', '500 LOOP 337', 'NEW BRAUNFELS', 'TX', '78130', 'COMAL', '05', 'Active', 'Residential', 'NBR', 'NEW BRAUNFELS', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005006', '100 MAIN PLZ', 'WACO', 'TX', '76701', 'MCLENNAN', '06', 'Active', 'Small Non-Residential', 'WAC', 'WACO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(5, '1042140000000000005007', '200 FRANKLIN AVE', 'WACO', 'TX', '76701', 'MCLENNAN', '07', 'Active', 'Residential', 'WAC', 'WACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005008', '300 COLUMBUS AVE', 'WACO', 'TX', '76701', 'MCLENNAN', '08', 'Active', 'Residential', 'WAC', 'WACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005009', '400 LAKE AIR DR', 'WACO', 'TX', '76710', 'MCLENNAN', '09', 'Active', 'Residential', 'WAC', 'WACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(5, '1042140000000000005010', '500 VALLEY MILLS DR', 'WACO', 'TX', '76710', 'MCLENNAN', '10', 'Inactive', 'Small Non-Residential', 'WAC', 'WACO', FALSE, 'Small Non-Residential', 'N', NULL, 'N');

-- Lubbock (tdsp_id=6)
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(6, '1060361000000000006001', '100 BROADWAY ST', 'LUBBOCK', 'TX', '79401', 'LUBBOCK', '01', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006002', '200 UNIVERSITY AVE', 'LUBBOCK', 'TX', '79401', 'LUBBOCK', '02', 'Active', 'Small Non-Residential', 'LBK', 'LUBBOCK', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(6, '1060361000000000006003', '300 SLIDE RD', 'LUBBOCK', 'TX', '79407', 'LUBBOCK', '03', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006004', '400 QUAKER AVE', 'LUBBOCK', 'TX', '79413', 'LUBBOCK', '04', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006005', '500 INDIANA AVE', 'LUBBOCK', 'TX', '79415', 'LUBBOCK', '05', 'De-Energized', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'N', NULL, 'N'),
(6, '1060361000000000006006', '600 MILWAUKEE AVE', 'LUBBOCK', 'TX', '79416', 'LUBBOCK', '06', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006007', '700 82ND ST', 'LUBBOCK', 'TX', '79424', 'LUBBOCK', '07', 'Active', 'Large Non-Residential', 'LBK', 'LUBBOCK', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(6, '1060361000000000006008', '800 LOOP 289', 'LUBBOCK', 'TX', '79423', 'LUBBOCK', '08', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006009', '900 FRANKFORD AVE', 'LUBBOCK', 'TX', '79416', 'LUBBOCK', '09', 'Active', 'Residential', 'LBK', 'LUBBOCK', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(6, '1060361000000000006010', '1000 MARSHA SHARP FWY', 'LUBBOCK', 'TX', '79401', 'LUBBOCK', '10', 'Active', 'Small Non-Residential', 'LBK', 'LUBBOCK', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

-- Nueces (tdsp_id=7)
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(7, '1039940000000000007001', '100 ROBSTOWN AVE', 'ROBSTOWN', 'TX', '78380', 'NUECES', '01', 'Active', 'Residential', 'ROB', 'ROBSTOWN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(7, '1039940000000000007002', '200 MAIN ST', 'ROBSTOWN', 'TX', '78380', 'NUECES', '02', 'Active', 'Small Non-Residential', 'ROB', 'ROBSTOWN', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(7, '1039940000000000007003', '300 CAMPBELL ST', 'ROBSTOWN', 'TX', '78380', 'NUECES', '03', 'Active', 'Residential', 'ROB', 'ROBSTOWN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(7, '1039940000000000007004', '400 S HWY 77', 'ROBSTOWN', 'TX', '78380', 'NUECES', '04', 'Inactive', 'Residential', 'ROB', 'ROBSTOWN', FALSE, 'Residential', 'N', NULL, 'N'),
(7, '1039940000000000007005', '500 NUECES ST', 'ROBSTOWN', 'TX', '78380', 'NUECES', '05', 'Active', 'Residential', 'ROB', 'ROBSTOWN', TRUE, 'Residential', 'Y', 'AMSM', 'N');

-- Entergy (tdsp_id=8) - Southeast Texas
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(8, '1058590000000000008001', '100 PEARL ST', 'BEAUMONT', 'TX', '77701', 'JEFFERSON', '01', 'Active', 'Residential', 'BMT', 'BEAUMONT', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008002', '200 CALDER AVE', 'BEAUMONT', 'TX', '77701', 'JEFFERSON', '02', 'Active', 'Small Non-Residential', 'BMT', 'BEAUMONT', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(8, '1058590000000000008003', '300 COLLEGE ST', 'BEAUMONT', 'TX', '77701', 'JEFFERSON', '03', 'Active', 'Residential', 'BMT', 'BEAUMONT', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008004', '400 DOWLEN RD', 'BEAUMONT', 'TX', '77706', 'JEFFERSON', '04', 'Active', 'Residential', 'BMT', 'BEAUMONT', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008005', '500 PHELAN BLVD', 'BEAUMONT', 'TX', '77707', 'JEFFERSON', '05', 'Active', 'Large Non-Residential', 'BMT', 'BEAUMONT', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(8, '1058590000000000008006', '600 WASHINGTON BLVD', 'BEAUMONT', 'TX', '77705', 'JEFFERSON', '06', 'De-Energized', 'Residential', 'BMT', 'BEAUMONT', TRUE, 'Residential', 'N', NULL, 'N'),
(8, '1058590000000000008007', '100 PORT ARTHUR AVE', 'PORT ARTHUR', 'TX', '77640', 'JEFFERSON', '07', 'Active', 'Residential', 'PAR', 'PORT ARTHUR', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008008', '200 GULFWAY DR', 'PORT ARTHUR', 'TX', '77642', 'JEFFERSON', '08', 'Active', 'Small Non-Residential', 'PAR', 'PORT ARTHUR', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(8, '1058590000000000008009', '300 MEMORIAL BLVD', 'PORT ARTHUR', 'TX', '77640', 'JEFFERSON', '09', 'Active', 'Residential', 'PAR', 'PORT ARTHUR', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008010', '400 TWIN CITY HWY', 'PORT ARTHUR', 'TX', '77642', 'JEFFERSON', '10', 'Active', 'Residential', 'PAR', 'PORT ARTHUR', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008011', '100 GREEN AVE', 'ORANGE', 'TX', '77630', 'ORANGE', '11', 'Active', 'Residential', 'ORG', 'ORANGE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008012', '200 W PARK AVE', 'ORANGE', 'TX', '77630', 'ORANGE', '12', 'Active', 'Small Non-Residential', 'ORG', 'ORANGE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(8, '1058590000000000008013', '300 MACARTHUR DR', 'ORANGE', 'TX', '77630', 'ORANGE', '13', 'Active', 'Residential', 'ORG', 'ORANGE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(8, '1058590000000000008014', '400 BORDER ST', 'ORANGE', 'TX', '77630', 'ORANGE', '14', 'Inactive', 'Residential', 'ORG', 'ORANGE', FALSE, 'Residential', 'N', NULL, 'N'),
(8, '1058590000000000008015', '100 LUMBERTON RD', 'LUMBERTON', 'TX', '77657', 'HARDIN', '15', 'Active', 'Residential', 'LBR', 'LUMBERTON', TRUE, 'Residential', 'Y', 'AMSM', 'N');

-- Sharyland (tdsp_id=9)
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(9, '1046648000000000009001', '100 N MAIN ST', 'MISSION', 'TX', '78572', 'HIDALGO', '01', 'Active', 'Residential', 'MSN', 'MISSION', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(9, '1046648000000000009002', '200 CONWAY AVE', 'MISSION', 'TX', '78572', 'HIDALGO', '02', 'Active', 'Small Non-Residential', 'MSN', 'MISSION', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(9, '1046648000000000009003', '300 BRYAN RD', 'MISSION', 'TX', '78572', 'HIDALGO', '03', 'Active', 'Residential', 'MSN', 'MISSION', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(9, '1046648000000000009004', '400 SHARY RD', 'MISSION', 'TX', '78572', 'HIDALGO', '04', 'De-Energized', 'Residential', 'MSN', 'MISSION', TRUE, 'Residential', 'N', NULL, 'N'),
(9, '1046648000000000009005', '500 EXPRESSWAY 83', 'MISSION', 'TX', '78572', 'HIDALGO', '05', 'Active', 'Large Non-Residential', 'MSN', 'MISSION', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(9, '1046648000000000009006', '100 E STENGER ST', 'SAN BENITO', 'TX', '78586', 'CAMERON', '06', 'Active', 'Residential', 'SBN', 'SAN BENITO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(9, '1046648000000000009007', '200 SAM HOUSTON BLVD', 'SAN BENITO', 'TX', '78586', 'CAMERON', '07', 'Active', 'Residential', 'SBN', 'SAN BENITO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(9, '1046648000000000009008', '300 BUSINESS 77', 'SAN BENITO', 'TX', '78586', 'CAMERON', '08', 'Active', 'Small Non-Residential', 'SBN', 'SAN BENITO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(9, '1046648000000000009009', '400 W ROBERTSON ST', 'SAN BENITO', 'TX', '78586', 'CAMERON', '09', 'Active', 'Residential', 'SBN', 'SAN BENITO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(9, '1046648000000000009010', '500 N WILLIAMS RD', 'SAN BENITO', 'TX', '78586', 'CAMERON', '10', 'Inactive', 'Residential', 'SBN', 'SAN BENITO', FALSE, 'Residential', 'N', NULL, 'N');

-- SWEPCO (tdsp_id=10) - Northeast Texas
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(10, '1040506000000000010001', '100 E METHVIN ST', 'LONGVIEW', 'TX', '75601', 'GREGG', '01', 'Active', 'Residential', 'LGV', 'LONGVIEW', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(10, '1040506000000000010002', '200 W COTTON ST', 'LONGVIEW', 'TX', '75601', 'GREGG', '02', 'Active', 'Small Non-Residential', 'LGV', 'LONGVIEW', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(10, '1040506000000000010003', '300 JUDSON RD', 'LONGVIEW', 'TX', '75601', 'GREGG', '03', 'Active', 'Residential', 'LGV', 'LONGVIEW', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(10, '1040506000000000010004', '400 EASTMAN RD', 'LONGVIEW', 'TX', '75602', 'GREGG', '04', 'Active', 'Large Non-Residential', 'LGV', 'LONGVIEW', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(10, '1040506000000000010005', '500 LOOP 281', 'LONGVIEW', 'TX', '75605', 'GREGG', '05', 'De-Energized', 'Residential', 'LGV', 'LONGVIEW', TRUE, 'Residential', 'N', NULL, 'N'),
(10, '1040506000000000010006', '100 E AUSTIN ST', 'MARSHALL', 'TX', '75670', 'HARRISON', '06', 'Active', 'Residential', 'MAR', 'MARSHALL', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(10, '1040506000000000010007', '200 N WASHINGTON AVE', 'MARSHALL', 'TX', '75670', 'HARRISON', '07', 'Active', 'Small Non-Residential', 'MAR', 'MARSHALL', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(10, '1040506000000000010008', '300 E GRAND AVE', 'MARSHALL', 'TX', '75670', 'HARRISON', '08', 'Active', 'Residential', 'MAR', 'MARSHALL', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(10, '1040506000000000010009', '100 TEXAS BLVD', 'TEXARKANA', 'TX', '75501', 'BOWIE', '09', 'Active', 'Residential', 'TXK', 'TEXARKANA', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(10, '1040506000000000010010', '200 STATE LINE AVE', 'TEXARKANA', 'TX', '75501', 'BOWIE', '10', 'Active', 'Small Non-Residential', 'TXK', 'TEXARKANA', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

-- AEP Texas SP (tdsp_id=11)
INSERT INTO service_point (tdsp_id, esiid, street, city, state, zip, county, meter_read_cycle, status, premise_type, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator) VALUES
(11, '1007455000000000011001', '100 S BICENTENNIAL BLVD', 'MCALLEN', 'TX', '78501', 'HIDALGO', '01', 'Active', 'Residential', 'MCA', 'MCALLEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011002', '200 N 10TH ST', 'MCALLEN', 'TX', '78501', 'HIDALGO', '02', 'Active', 'Small Non-Residential', 'MCA', 'MCALLEN', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011003', '300 PECAN BLVD', 'MCALLEN', 'TX', '78501', 'HIDALGO', '03', 'Active', 'Residential', 'MCA', 'MCALLEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011004', '400 NOLANA AVE', 'MCALLEN', 'TX', '78504', 'HIDALGO', '04', 'Active', 'Residential', 'MCA', 'MCALLEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011005', '500 EXPRESSWAY 83', 'MCALLEN', 'TX', '78503', 'HIDALGO', '05', 'Active', 'Large Non-Residential', 'MCA', 'MCALLEN', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011006', '100 E TYLER AVE', 'HARLINGEN', 'TX', '78550', 'CAMERON', '06', 'Active', 'Residential', 'HRL', 'HARLINGEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011007', '200 S 77 SUNSHINE STRIP', 'HARLINGEN', 'TX', '78550', 'CAMERON', '07', 'Active', 'Small Non-Residential', 'HRL', 'HARLINGEN', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011008', '300 W HARRISON AVE', 'HARLINGEN', 'TX', '78550', 'CAMERON', '08', 'De-Energized', 'Residential', 'HRL', 'HARLINGEN', TRUE, 'Residential', 'N', NULL, 'N'),
(11, '1007455000000000011009', '400 N ED CAREY DR', 'HARLINGEN', 'TX', '78550', 'CAMERON', '09', 'Active', 'Residential', 'HRL', 'HARLINGEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011010', '500 E VAN BUREN AVE', 'HARLINGEN', 'TX', '78550', 'CAMERON', '10', 'Active', 'Residential', 'HRL', 'HARLINGEN', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011011', '100 E ELIZABETH ST', 'BROWNSVILLE', 'TX', '78520', 'CAMERON', '11', 'Active', 'Residential', 'BRO', 'BROWNSVILLE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011012', '200 E LEVEE ST', 'BROWNSVILLE', 'TX', '78520', 'CAMERON', '12', 'Active', 'Small Non-Residential', 'BRO', 'BROWNSVILLE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011013', '300 BOCA CHICA BLVD', 'BROWNSVILLE', 'TX', '78521', 'CAMERON', '13', 'Active', 'Residential', 'BRO', 'BROWNSVILLE', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011014', '400 CENTRAL BLVD', 'BROWNSVILLE', 'TX', '78520', 'CAMERON', '14', 'Active', 'Large Non-Residential', 'BRO', 'BROWNSVILLE', TRUE, 'Large Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011015', '500 E 14TH ST', 'BROWNSVILLE', 'TX', '78520', 'CAMERON', '15', 'Inactive', 'Residential', 'BRO', 'BROWNSVILLE', FALSE, 'Residential', 'N', NULL, 'N'),
(11, '1007455000000000011016', '100 W PIKE BLVD', 'WESLACO', 'TX', '78596', 'HIDALGO', '16', 'Active', 'Residential', 'WES', 'WESLACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011017', '200 S TEXAS BLVD', 'WESLACO', 'TX', '78596', 'HIDALGO', '17', 'Active', 'Residential', 'WES', 'WESLACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011018', '300 E BUSINESS 83', 'WESLACO', 'TX', '78596', 'HIDALGO', '18', 'Active', 'Small Non-Residential', 'WES', 'WESLACO', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N'),
(11, '1007455000000000011019', '400 S BRIDGE AVE', 'WESLACO', 'TX', '78596', 'HIDALGO', '19', 'Active', 'Residential', 'WES', 'WESLACO', TRUE, 'Residential', 'Y', 'AMSM', 'N'),
(11, '1007455000000000011020', '500 N AIRPORT DR', 'WESLACO', 'TX', '78596', 'HIDALGO', '20', 'Active', 'Residential', 'WES', 'WESLACO', TRUE, 'Residential', 'Y', 'AMSM', 'N');
