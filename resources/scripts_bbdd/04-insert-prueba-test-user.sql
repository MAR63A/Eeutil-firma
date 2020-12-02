DELETE FROM inside_aplicaciones;
INSERT INTO inside_aplicaciones (idaplicacion, password, descripcion, activa, tramitar, sello, firma) VALUES ('prueba','098f6bcd4621d373cade4e832627b4f6','prueba', 'S','S','S','S');

DELETE FROM inside_aplicaciones_propiedad;
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','algoritmoFirmaDefecto','SHA1withRSA');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','aliasCertificado','dtic age pruebas');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','formatoFirmaDefecto','Adobe PDF');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','ip.openoffice	','');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','modoFirmaDefecto','implicit');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','passwordCertificado','<noaplica>');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','passwordKS','4jdhs786');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','port.openoffice','');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','rutaKS','${local_home_app}/certificados/sello_dtic_age_pruebas.p12');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','rutaLogo','${local_home_app}/img/escudo.jpg');
INSERT INTO inside_aplicaciones_propiedad (idaplicacion, propiedad, valor) VALUES ('prueba','tipoKS','PKCS12');