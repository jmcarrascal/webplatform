INSERT INTO idioma (id, codigo, descripcion) VALUES (1, 'ES', 'Español');
INSERT INTO idioma (id, codigo, descripcion) VALUES (2, 'PT', 'Português');

INSERT INTO pais (id_pais, c_pais, d_pais) VALUES (1, 'ARG', 'Argentina');
INSERT INTO pais (id_pais, c_pais, d_pais) VALUES (2, 'BR', 'Brasil');
INSERT INTO pais (id_pais, c_pais, d_pais) VALUES (3, 'PR', 'Paraguay');
INSERT INTO pais (id_pais, c_pais, d_pais) VALUES (4, 'UR', 'Uruguay');

INSERT INTO nacionalidad (id, id_pais, descripcion) VALUES (1, 1, 'Argentina');
INSERT INTO nacionalidad (id, id_pais, descripcion) VALUES (2, 2, 'Brasil');
INSERT INTO nacionalidad (id, id_pais, descripcion) VALUES (3, 3, 'Paraguay');
INSERT INTO nacionalidad (id, id_pais, descripcion) VALUES (4, 4, 'Uruguay');
INSERT INTO nacionalidad (id, id_pais, descripcion) VALUES (5, null, 'Otro');

INSERT INTO configuracion_servidor_pais (id_servidor_pais, id_pais, url) VALUES (1, 1, 'http://localhost:8080/');
INSERT INTO configuracion_servidor_pais (id_servidor_pais, id_pais, url) VALUES (2, 2, 'http://localhost:8180/');
INSERT INTO configuracion_servidor_pais (id_servidor_pais, id_pais, url) VALUES (3, 3, 'http://localhost:8280/');
INSERT INTO configuracion_servidor_pais (id_servidor_pais, id_pais, url) VALUES (4, 4, 'http://localhost:8380/');

INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('version', '1.2', 'Versión del Sistema');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('appserver', 'JBOSS', 'Servidor de aplicaciones utilizado (JBOSS ou OC4J)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('jmsQueueFactory', 'ConnectionFactory', 'Fábrica de conexiones para o JMS (JBOSS = ConnectionFactory; OC4J = jms/QueueConnectionFactory)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('jmsQueue', 'queue/filaLogMercosul', 'Cola de mensajes persistentes configurada en el servidor (JBOSS = queue/filaLogMercosul; OC4J = jms/filaLogMercosul)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('oissUrl', 'http://localhost:8080/oiss/services/LogServices', 'Log Web Service de OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('oissTimer', '1000000', 'Tiempo en milisegundos para tentativa de reenvío del log a OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('oissUrlAuth', 'http://localhost:8080/oiss/services/AutorizacaoServices', 'Web Service de autenticación en OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('tamMaxImagens', '700', 'Tamaño máximo de las imágenes adjuntas en un proceso.');

INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('paisId', '1', 'ID del País en la tabla de dominio');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystorePassword', 'CIV10oiQeHf3+xVFggTzXCjRMKX+Kyv7xbY2bEMSrmo5t3coaqlBIcYdvAVcwHfh', 'Contraseña utilizada para acceder al keystore');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('privateKeyPassword', 'CIV10oiQeHf3+xVFggTzXCjRMKX+Kyv7xbY2bEMSrmo5t3coaqlBIcYdvAVcwHfh', 'Contraseña utilizada para acceder a la clave privada');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystoreSSLPassword', 'CIV10oiQeHf3+xVFggTzXCjRMKX+Kyv7xbY2bEMSrmo5t3coaqlBIcYdvAVcwHfh', 'Contraseña para autenticación del Keystore SSL');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystoreAuthPassword', 'CIV10oiQeHf3+xVFggTzXCjRMKX+Kyv7xbY2bEMSrmo5t3coaqlBIcYdvAVcwHfh', 'Contraseña para autenticación del Keystore de certificados autorizados');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('privateKeyAlias', 'uruguay', 'Alias utilizado para identificar la clave privada del país');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystoreAuthPath', '/usr/local/jboss-4.2.3.GA/CDOISS/keystore/keystore.jks', 'Ruta del Keystore de certificados autorizados');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystoreSSLPath', '/usr/local/jboss-4.2.3.GA/CDOISS/keystore/keystore.jks', 'Ruta del Keystore SSL para configuración de HTTPS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('keystorePath', '/usr/local/jboss-4.2.3.GA/CDOISS/keystore/keystore.jks', 'Ruta real para el archivo de keystore');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('trustedCertificatesPath', '/usr/local/jboss-4.2.3.GA/CDOISS/trusted', 'Directorio de certificados confiables');

INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.archivosalidaxml', 'C:/Proyectos/Java/SIACI/Archivo/Salida/', 'URI Archivo salida XML');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.archivohistoricoxml', 'C:/Proyectos/Java/SIACI/Archivo/Historico/', 'URI Archivo historico XML');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.empadronamientowsSinIP', 'siaci/soap/alta', 'URL Web service Empadronamiento (Sin IP)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.actualizacionwsSinIP', 'siaci/soap/actualizacion', 'URL Web service Actualizacion (Sin IP)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.comunicacionwsSinIP', 'pagosMercosurWS/ComunicacionWSImpl', 'URL Web service Comunicacion (Sin IP)');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.empadronamientows', 'http://localhost:8080/siaci/soap/alta', 'URL Web service Empadronamiento');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.seguimientows', 'http://localhost:8080/pagosMercosurWS/SeguimientoWSImpl', 'URL Web service Seguimiento');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.busquedaws', 'http://localhost:8080/pagosMercosurWS/BusquedaWSImpl', 'URL Web service Busqueda');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.codigoUtilws', 'http://localhost:8080/pagosMercosurWS/CodigoUtilWSImpl', 'URL Web service Codigo Util');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.actualizacionws', 'http://localhost:8080/pagosMercosurWS/ActualizacionWSImpl', 'URL Web service Actualizacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.xmlws', 'http://localhost:8080/pagosMercosurWS/XmlWSImpl', 'URL Web service XML');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.autorizacionws', 'http://localhost:8080/pagosMercosurWS/AutorizacionWSImpl', 'URL Web service Autorizacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.configuracionSistemaws', 'http://localhost:8080/pagosMercosurWS/ConfiguracionSistemaWSImpl', 'URL Web service Configuracion sistema');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.actualizacionwsOISS', 'http://localhost:9080/oissMercosurWS/ActualizacionOISSWSImpl', 'URL Web service Actualizacion OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.empadronamientowsOISS', 'http://localhost:9080/oissMercosurWS/EmpadronamientoOISSWSImpl', 'URL Web service Empadronamiento OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.liquidacionws', 'http://localhost:8080/pagosMercosurWS/LiquidacionWSImpl', 'URL Web service Liquidacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.comunws', 'http://localhost:8080/pagosMercosurWS/ComunWSImpl', 'URL Web service Comun');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.comunicacionws', 'http://localhost:8080/pagosMercosurWS/ComunicacionWSImpl', 'URL Web service Comunicacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.logoissws', 'http://localhost:9080/oissMercosurWS/LogOISSWSImpl', 'URL Web service Log OISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.rendicionws', 'http://localhost:8080/pagosMercosurWS/RendicionWSImpl', 'URL Web service Rendicion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('url.validacionoissws', 'http://localhost:9080/oissMercosurWS/ValidacionWSImpl', 'URL Web service ValidacionOISS');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.esquemarendicion', 'C:/Proyectos/Java/SIACI/Archivos/Esquemas/EsquemaRENLIQInterno.xsd', 'URI Esquema Rendicion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.esquemaliquidacion', 'C:/Proyectos/Java/SIACI/Archivos/Esquemas/ESQUEMALIQBEN.xsd', 'URI Esquema Liquidacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.esquemaempadronamientoalta', 'C:/Proyectos/Java/SIACI/Archivos/Esquemas/EsquemaEmpadronamientoAlta.xsd', 'URI Esquema Empadronamiento Alta');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('uri.esquemaempadronamientoactualizacion', 'C:/Proyectos/Java/SIACI/Archivos/Esquemas/EsquemaEmpadronamientoActualizacion.xsd', 'URI Esquema Empadronamiento Actualizacion');
INSERT INTO configuraciones_sistema (propiedad, contenido_propiedad, descripcion) VALUES ('TareaPeriodo', '900000', 'Periodo para ejecucion de procesos batch');

INSERT INTO tipo_motivo (id_tipo_motivo, c_tipo_motivo, d_tipo_motivo) VALUES (1, 'ID', 'No es posible identificar a la persona');
INSERT INTO tipo_motivo (id_tipo_motivo, c_tipo_motivo, d_tipo_motivo) VALUES (2, 'BV', 'La persona no tiene beneficios vigentes');
INSERT INTO tipo_motivo (id_tipo_motivo, c_tipo_motivo, d_tipo_motivo) VALUES (0, 'OT', 'Otro');

INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (1, 'DNI', 'Documento Unico', true, true, 1);
INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (2, 'CUIL', 'Clave Unica Identificacion Laboral', false, false, 1);
INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (3, 'CP', 'Cedula Paraguay', false, false, 3);
INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (4, 'CI', 'Cedula Uruguay', false, false, 4);
INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (5, 'CPF', 'Catastro Persona Fisica', false, true, 2);
INSERT INTO tipo_documento (id_tipo_documento, c_tipo_documento, d_tipo_documento, gestor_obligatorio, pagador_obligatorio, id_pais) VALUES (6, 'DOC', 'Documento', false, false, 2);

INSERT INTO datos_pais (id_datos_pais, id_pais, id_moneda_transferencia, valida_nro_doc, valida_beneficio, beneficio_pagador, datos_maternos_oblig) VALUES (1, 1, NULL, false, false, false, false);
INSERT INTO datos_pais (id_datos_pais, id_pais, id_moneda_transferencia, valida_nro_doc, valida_beneficio, beneficio_pagador, datos_maternos_oblig) VALUES (3, 3, NULL, false, false, false, false);
INSERT INTO datos_pais (id_datos_pais, id_pais, id_moneda_transferencia, valida_nro_doc, valida_beneficio, beneficio_pagador, datos_maternos_oblig) VALUES (2, 2, NULL, true, true, true, true);
INSERT INTO datos_pais (id_datos_pais, id_pais, id_moneda_transferencia, valida_nro_doc, valida_beneficio, beneficio_pagador, datos_maternos_oblig) VALUES (4, 4, NULL, false, false, false, false);

INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (1, 'E'   , 'Edicion');
INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (2, 'PEPA', 'Pendiente Envío País Alta');
INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (3, 'RE'  , 'Recibido');
INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (4, 'PEOM', 'Pendiente Envío OISS Modificación');
INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (5, 'PEPM', 'Pendiente Envío País Modificación');
INSERT INTO emp_estado_seguimiento_domicilio (id_estado_domicilio, c_estado_domicilio, d_estado_domicilio) VALUES (6, 'REM' , 'Recibido Modificación');

UPDATE emp_estado_seguimiento_domicilio SET id_estado_siguiente = 2 WHERE id_estado_domicilio = 1;
UPDATE emp_estado_seguimiento_domicilio SET id_estado_siguiente = 3 WHERE id_estado_domicilio = 2;
UPDATE emp_estado_seguimiento_domicilio SET id_estado_siguiente = 5 WHERE id_estado_domicilio = 4;
UPDATE emp_estado_seguimiento_domicilio SET id_estado_siguiente = 6 WHERE id_estado_domicilio = 5;

INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (1, 'E', 'Edicion');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (2, 'PEPA', 'Pendiente Envío País Alta');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (3, 'PA', 'Pendiente Aprobacion');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (4, 'PEAO', 'Pendiente Envío Aprobacion OISS');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (5, 'PEAP', 'Pendiente Envío Aprobacion País');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (6, 'RE', 'Recibido');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (7, 'PEOM', 'Pendiente Envío OISS Modificación');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (8, 'PEPM', 'Pendiente Envío País Modificación');
INSERT INTO emp_estado_seguimiento_empadronamiento (id_estado_emp, c_estado_emp, d_estado_emp) VALUES (9, 'REM', 'Recibido Modificación');

UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 2 WHERE id_estado_emp = 1;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 3 WHERE id_estado_emp = 2;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 4 WHERE id_estado_emp = 3;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 5 WHERE id_estado_emp = 4;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 6 WHERE id_estado_emp = 5;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 8 WHERE id_estado_emp = 7;
UPDATE emp_estado_seguimiento_empadronamiento SET id_estado_emp_siguiente = 9 WHERE id_estado_emp = 8;

INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (1, 'PEOA', 'Pendiente Envío OISS Alta');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (2, 'PEPA', 'Pendiente Envío País Alta');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (3, 'PA', 'Pendiente Aprobacion Local');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (4, 'PEAO', 'Pendiente Envío Aprobación OISS');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (5, 'PEAP', 'Pendiente Envío Aprobación País');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (6, 'RE', 'Recibido');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (7, 'PEOM', 'Pendiente Envío OISS Modificación');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (8, 'PEPM', 'Pendiente Envío País Modificación');
INSERT INTO emp_estado_seguimiento_vinculo (id_estado_vinculo, c_estado_vinculo, d_estado_vinculo) VALUES (9, 'REM', 'Recibido Modificación');

UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 2 WHERE id_estado_vinculo = 1;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 3 WHERE id_estado_vinculo = 2;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 4 WHERE id_estado_vinculo = 3;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 5 WHERE id_estado_vinculo = 4;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 6 WHERE id_estado_vinculo = 5;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 8 WHERE id_estado_vinculo = 7;
UPDATE emp_estado_seguimiento_vinculo SET id_estado_vinculo_siguiente = 9 WHERE id_estado_vinculo = 8;

-- TODO verificar si el estado Incompleto se usa. Eliminar si no es usado.
INSERT INTO estado_beneficiario (id_estado_beneficiario, c_estado_beneficiario, d_estado_beneficiario) VALUES (1, 'R', 'Rechazado');
INSERT INTO estado_beneficiario (id_estado_beneficiario, c_estado_beneficiario, d_estado_beneficiario) VALUES (2, 'P', 'Provisorio');
INSERT INTO estado_beneficiario (id_estado_beneficiario, c_estado_beneficiario, d_estado_beneficiario) VALUES (3, 'C', 'Confirmado');
INSERT INTO estado_beneficiario (id_estado_beneficiario, c_estado_beneficiario, d_estado_beneficiario) VALUES (4, 'I', 'Incompleto');
INSERT INTO estado_beneficiario (id_estado_beneficiario, c_estado_beneficiario, d_estado_beneficiario) VALUES (5, 'B', 'Baja');

INSERT INTO estado_beneficio (id_estado_beneficio, c_estado_beneficio, d_estado_beneficio) VALUES (1, 'R', 'Rechazado');
INSERT INTO estado_beneficio (id_estado_beneficio, c_estado_beneficio, d_estado_beneficio) VALUES (2, 'P', 'Provisorio');
INSERT INTO estado_beneficio (id_estado_beneficio, c_estado_beneficio, d_estado_beneficio) VALUES (3, 'C', 'Confirmado');
INSERT INTO estado_beneficio (id_estado_beneficio, c_estado_beneficio, d_estado_beneficio) VALUES (4, 'I', 'Incompleto');
INSERT INTO estado_beneficio (id_estado_beneficio, c_estado_beneficio, d_estado_beneficio) VALUES (5, 'B', 'Baja');

INSERT INTO estado_civil (id_estado_civil, c_estado_civil, d_estado_civil) VALUES (1, 'S', 'Soltero');
INSERT INTO estado_civil (id_estado_civil, c_estado_civil, d_estado_civil) VALUES (2, 'C', 'Casado');
INSERT INTO estado_civil (id_estado_civil, c_estado_civil, d_estado_civil) VALUES (3, 'V', 'Viudo');
INSERT INTO estado_civil (id_estado_civil, c_estado_civil, d_estado_civil) VALUES (4, 'D', 'Divorciado');
INSERT INTO estado_civil (id_estado_civil, c_estado_civil, d_estado_civil) VALUES (5, 'P', 'Conviviente Previsional');

INSERT INTO sexo (codigo, descripcion) VALUES ('M', 'Masculino');
INSERT INTO sexo (codigo, descripcion) VALUES ('F', 'Femenino');

INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (1, 'PENDIENTE ENVIO', 'Liq. Pendiente Envío');
INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (2, 'ENVIADA', 'Liq. Enviada');
INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (3, 'RECIBIDA', 'Liq. Recibida');
INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (4, 'RECHAZADA', 'Liq. Rechazada');
INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (5, 'CERRADA', 'Liq. Cerrada');
INSERT INTO liq_estado_despacho (id_estado_despacho, c_estado_despacho, d_estado_despacho) VALUES (6, 'PENDIENTE RECEPCION', 'Liq. Pendiente Recepcion');

INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (1, 'ENVIADA', 'Rendición Enviada');
INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (2, 'OK', 'Rendición OK');
INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (3, 'FIRMA INVALIDA', 'Firma Inválida');
INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (4, 'INSCONSISTENTE', 'Inconsistencia en Archivo');
INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (5, 'ERROR FORMATO', 'Error de formato');
INSERT INTO liq_estado_rendicion (id_estado_rendicion, c_estado_rendicion, d_estado_rendicion) VALUES (6, 'PENDIENTE ENVIO', 'Rendicion Pendiente Envío');

INSERT INTO liq_moneda (id_moneda, id_pais, c_moneda, d_moneda, moneda_activa) VALUES (1, 1, 'PesoArg', 'Peso Argentino', false);
INSERT INTO liq_moneda (id_moneda, id_pais, c_moneda, d_moneda, moneda_activa) VALUES (2, 2, 'Real', 'Real', false);
INSERT INTO liq_moneda (id_moneda, id_pais, c_moneda, d_moneda, moneda_activa) VALUES (3, 3, 'PesoPara', 'Peso Paraguayo', false);
INSERT INTO liq_moneda (id_moneda, id_pais, c_moneda, d_moneda, moneda_activa) VALUES (4, 4, 'PesoUru', 'Peso Uruguayo', false);
--INSERT INTO liq_moneda (id_moneda, id_pais, c_moneda, d_moneda, moneda_activa) VALUES (5, 5, 'Dolar', 'Dolar', false);

INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (1, 1, 2);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (2, 1, 3);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (3, 1, 4);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (4, 2, 1);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (5, 2, 3);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (6, 2, 4);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (7, 3, 1);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (8, 3, 2);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (9, 3, 4);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (10, 4, 1);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (11, 4, 2);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (12, 4, 3);
--INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (13, 1, 5);
--INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (14, 2, 5);
--INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (15, 5, 2);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (74, 2, 2);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (80, 4, 4);
INSERT INTO liq_moneda_cambio (id_moneda_cambio, id_moneda_origen, id_moneda_destino) VALUES (83, 3, 3);

INSERT INTO prestacion (id_prestacion, c_prestacion, d_prestacion) VALUES (1, 'J', 'Jubilación');
INSERT INTO prestacion (id_prestacion, c_prestacion, d_prestacion) VALUES (2, 'P', 'Pensión');
INSERT INTO prestacion (id_prestacion, c_prestacion, d_prestacion) VALUES (3, 'E', 'Ayuda Enfermedad');

INSERT INTO rol (id_rol, c_rol, d_rol) VALUES (1, 'USER_G', 'Gestor');
INSERT INTO rol (id_rol, c_rol, d_rol) VALUES (2, 'USER_P', 'Pagador');

INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (1, 'AF', 'Fecha Fallecimiento');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (2, 'AD', 'Domicilio');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (3, 'AV', 'Vínculo Previsional');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (4, 'AEB', 'Estado Beneficiario');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (5, 'AB', 'Beneficiario');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (6, 'AP', 'Identificación Persona');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (7, 'A', 'Alta');
INSERT INTO tipo_actualizacion (id_tipo_actualizacion, c_tipo_actualizacion, d_tipo_actualizacion) VALUES (8, 'M', 'Modificación');


INSERT INTO tipo_envio (id_tipo_envio, c_tipo_envio, d_tipo_envio) VALUES (1, 'Envío Inicial', 'Envío Inicial');
INSERT INTO tipo_envio (id_tipo_envio, c_tipo_envio, d_tipo_envio) VALUES (2, 'Envío Auxiliar', 'Envío Auxiliar');

INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (1, 'Empadronamiento', 'Empadronamiento');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (2, 'Transferencia Bancaria', 'Transferencia Bancaria');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (3, 'Archivo de Liquidación', 'Archivo de Liquidación');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (4, 'Archivo de Rendición', 'Archivo de Rendición');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (5, 'Reclamos', 'Reclamos');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (6, 'Error empadronamiento', 'Error empadronamiento');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (7, 'Error archivo de Liquidación', 'Error archivo de Liquidación');
INSERT INTO tipo_informacion (id_tipo_informacion, c_tipo_informacion, d_tipo_informacion) VALUES (8, 'Error archivo de Rendición', 'Error archivo de Rendición');

INSERT INTO tipo_prestacion (id_tipo_prestacion, c_tipo_prestacion, d_tipo_prestacion) VALUES (1, 'J', 'JUBILACIÓN');
INSERT INTO tipo_prestacion (id_tipo_prestacion, c_tipo_prestacion, d_tipo_prestacion) VALUES (2, 'P', 'PENSIÓN');

INSERT INTO usuario (id, nombre_usuario, apellido, nombre, correo_electronico, id_pais_origen, id_rol, password) VALUES (NEXTVAL('sq_usuario_id'), 'admin', '', 'Administrador', 'admin@admin', (select cast(contenido_propiedad as integer) from configuraciones_sistema where propiedad = 'paisId'), (select id_rol from rol where c_rol = 'USER_G'), 'admin');
INSERT INTO usuario (id, nombre_usuario, apellido, nombre, correo_electronico, id_pais_origen, id_rol, password) VALUES (NEXTVAL('sq_usuario_id'), 'pagador', '', 'Pagador', 'pagador@pagador', (select cast(contenido_propiedad as integer) from configuraciones_sistema where propiedad = 'paisId'), (select id_rol from rol where c_rol = 'USER_P'), 'pagador');
