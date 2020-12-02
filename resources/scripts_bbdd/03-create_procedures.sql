DROP PROCEDURE IF EXISTS regenerar_aplicacion_operacion;

DELIMITER //

CREATE PROCEDURE regenerar_aplicacion_operacion(IN in_idaplicacion VARCHAR(100), IN in_operacion VARCHAR(100))
BEGIN

   DECLARE numPeticiones  INTEGER;
   DECLARE reg INTEGER;

   -- sacamos el total de peticiones
   SELECT SUM(numero_peticiones) INTO numPeticiones FROM aplicacion_operacion WHERE idaplicacion = in_idaplicacion AND operacion = in_operacion;

   -- buscamos el maximo para modificarlo
   SELECT MIN(id) INTO reg FROM aplicacion_operacion WHERE idaplicacion = in_idaplicacion AND operacion = in_operacion;
   
   -- actualizamos el registro con el nuevo contador
   UPDATE aplicacion_operacion SET numero_peticiones = numPeticiones WHERE id = reg;
   
   -- eliminamos los registros con 1
   DELETE FROM aplicacion_operacion where idaplicacion = in_idaplicacion AND operacion = in_operacion AND id != reg;

END //
DELIMITER ;