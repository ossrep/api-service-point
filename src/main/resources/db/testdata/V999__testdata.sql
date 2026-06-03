INSERT INTO service_point (service_point_id, esiid, street, city, state, zip, county, tdsp_duns, meter_read_cycle, status, premise_type, power_region, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (1, '1008901012345678901234', '123 MAIN ST', 'HOUSTON', 'TX', '77002', 'HARRIS', '957877905', '01', 'Active', 'Residential', 'ERCOT', 'FAN', 'FANNIN REIT', TRUE, 'Residential', 'Y', 'AMSM', 'N');

INSERT INTO service_point (service_point_id, esiid, street, city, state, zip, county, tdsp_duns, meter_read_cycle, status, premise_type, power_region, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (2, '1008901098765432109876', '456 OAK AVE', 'CYPRESS', 'TX', '77429', 'HARRIS', '957877905', '21', 'Active', 'Small Non-Residential', 'ERCOT', 'KL', 'KLUGE', TRUE, 'Small Non-Residential', 'Y', 'AMSR', 'N');

INSERT INTO service_point (service_point_id, esiid, street, city, state, zip, county, tdsp_duns, meter_read_cycle, status, premise_type, power_region, station_code, station_name, metered, polr_customer_class, settlement_ams_indicator, tdsp_ams_indicator, switch_hold_indicator)
VALUES (3, '1008901055555555555555', '789 ELM RD', 'BAYTOWN', 'TX', '77520', 'CHAMBERS', '957877905', '19', 'Inactive', 'Residential', 'ERCOT', '_CD', 'CEDAR BAYOU OLD', FALSE, 'Residential', 'N', NULL, 'N');
