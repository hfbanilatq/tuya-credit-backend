USE tuya_db;

/* INSERT QUERY NO: 1*/
INSERT INTO USER_ROLE(ROLE_NAME) VALUES ('ROLE_ADMIN');

/* INSERT QUERY NO: 2*/
INSERT INTO USER_ROLE(ROLE_NAME) VALUES ('ROLE_USER');

/* INSERT QUERY NO: 3 */
INSERT INTO DOCUMENT_TYPE(ID, DOCUMENT_TYPE)
VALUES (1, 'Cédula de Ciudadanía');

/* INSERT QUERY NO: 4 */
INSERT INTO DOCUMENT_TYPE(ID, DOCUMENT_TYPE)
VALUES (2, 'Cédula de Extranjería');

/* INSERT QUERY NO: 5 */
INSERT INTO DOCUMENT_TYPE(ID, DOCUMENT_TYPE)
VALUES (3, 'Tarjeta de Identidad');

/* INSERT QUERY NO: 6 */
INSERT INTO DOCUMENT_TYPE(ID, DOCUMENT_TYPE)
VALUES (4, 'Registro Civil');

/* INSERT QUERY NO: 7 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Éxito', '22800', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-privada.png');

/* INSERT QUERY NO: 8 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Éxito Pro Mastercard', '20300', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-exito-pro_0.png');

/* INSERT QUERY NO: 9 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Éxito Gold Mastercard', '19600', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-exito-gold_0.png');

/* INSERT QUERY NO: 10 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Carulla Gold Mastercard', '19600', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-exito-gold_0.png');

/* INSERT QUERY NO: 11 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Carulla Mastercard Black', '15700', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-carulla-black_0.png');

/* INSERT QUERY NO: 12 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Viva Mastercard', '10000', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/tarjeta-viva-mastercard-v2_0.png');

/* INSERT QUERY NO: 13 */
INSERT INTO credit_card(type, fee_value, max_fee, monthly_interest, effective_annual_interest, image_url)
VALUES('Tarjeta Club del Comerciante Mastercard', '10000', '48', '2.18', '29.53609', 'https://www.tuya.com.co/sites/default/files/imagenes/tarjetas/img-card-club-comerciante.png');


INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'OSTER-PLU:3064695', 'https://exitocol.vtexassets.com/arquivos/ids/11230968-800-auto?v=637745155729070000&width=800&height=auto&aspect=true',
 'Freidora De Aire 4Lt OSTER. 2138251', '603000', 'Exito', '49.73466', '54.73466');
 
INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'MABE-PLU:3095062', 'https://exitocol.vtexassets.com/arquivos/ids/12717328-800-auto?v=637873776674170000&width=800&height=auto&aspect=true',
  'Nevera MABE No Frost 300 L RMA300FJCG', '2070900', 'Exito', '23.001', '31.001');

INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'SAMSUNG-PLU:1724620', '',
 'Lavadora SAMSUNG Carga Superior 19 kg (42 lb) WA19T6260B', '603000', 'Exito', '49.73466', '54.73466');



INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'SAMSUNG-PLU:1724620', 'https://exitocol.vtexassets.com/arquivos/ids/12345673-800-auto?v=637849616588230000&width=800&height=auto&aspect=true',
 'Lavadora SAMSUNG Carga Superior 19 kg (42 lb) WA19T6260B', '3095900', 'Exito', '28.00', '40.00');



INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'ABBA-PLU:1568089', 'https://exitocol.vtexassets.com/arquivos/ids/11749813-800-auto?v=637798514048500000&width=800&height=auto&aspect=true',
 'Nevera ABBA Convencional 187 Litros NVARS2351P', '1338900', 'Exito', '22.00', '30.00');



INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'HOLSTEIN-PLU:3159185', 'https://exitocol.vtexassets.com/arquivos/ids/12946604-800-auto?v=637886496379930000&width=800&height=auto&aspect=true',
 'Freidora De Aire Digital 7.6Lt HOLSTEIN HH09227001SS', '799900', 'Exito', '32.00', '48.00');



INSERT INTO product(reference, image_url, description, price, warehouse, discount, discount_with_credit_card)
VALUES ( 'WHIRLPOOL-PLU:3125719', 'https://carulla.vtexassets.com/arquivos/ids/7663922-800-auto?v=637873777834300000&width=800&height=auto&aspect=true',
 'Lavadora WHIRLPOOL Carga Superior 15 kg (33 lb) WWG15BNHLA', '2189900', 'Carulla', '19.00', '19.00');

