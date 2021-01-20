# Eeutil-firma
Instalación y evolutivo de la versión distribuible de Eeutil-Firma.

Se parte de la versión distribuible que se ofrece en el Área de descargas de la Suite Inside del Centro de Transferencia Tecnológica:

https://administracionelectronica.gob.es/ctt/inside/descargas -> Versión distribuible (Código fuente) -> Distribuible Eeutil-firma v4.2.0 (noviembre 2018)

Estos son los servicios de Inside que hacen uso de los servicios de eeutil-firma:

## FirmarFichero
Servicio de Eeutil-Firma que se utiliza en Inside al crear un nuevo Documento y seleccionar Firma en Servidor.

Definición de los campos según guía de la AGE:

* **idaplicacion:** Identificador de la aplicación consumidora.
* **password:** password de la aplicación consumidora.
* **contenido:** Contenido en base64 que se desea firmar.
* **formatoFirma:** (Opcional)Formato de la firma a realizar. Las cadenas admitidas son:
*-Adobe PDF, PAdES: Con este formato solo se pueden firmar y confirmar documentos PDF. No se permiten contrafirmas.
-XAdES Detached.
-XAdES Enveloped. Sólo ficheros XML
-CAdES.
-XAdES Manifest. Enviar a firmar el Hash (SHA512) en base64 del fichero.*

* **modoFirma:** (Opcional)Cadenas admitidas:
*-implicit: El documento se queda implícito en la firma generada. Sólo se tendrá en cuenta para firmas XadES Detached y CAdES, ya que la firma Adobe PDF , PAdES es siempre implícita.
-explicit: Se firma el hash del documento, y el documento no irá implícito en la firma. Válido para firmas CAdES.*

Las firmas XAdES explícitas están deprecadas en el MiniApplet y AutoFirma, y serán eliminadas en próximas versiones de estas herramientas. Este tipo de firmas no son conformes con ningún tipo de normativa ni estándar, y deben ser sustituidas por firmas con estructuras MANIFEST ya que en un futuro próximo se extinguirá el uso del tipo “explícito” en las firmas XAdES.

* **algoritmoFirma:** (Opcional)
-SHA1withRSA (obsoleto)
-SHA256withRSA
-SHA384withRSA
-SHA512withRSA (recomendado)

No es recomendable usar los algoritmos *MD5withRSA y MD2withRSA* por estar obsoletos y ser vulnerables. Por la misma razón, es igualmente conveniente evitar el algoritmo *SHA1withRSA*.

* **cofirmarSiFirmado:** En caso de que lo que se envíe ya sea una firma, este parámetro sirve para decidir si se quiere confirmar (en cuyo caso deberá tomar valor true (1)) o contrafirmar(false (0)).

## FirmarFicheroWithPropertie
Servicio de Eeutil-Firma que se utiliza en Inside al crear un nuevo Expediente y seleccionar Firma en Servidor cuando creamos el documento dentro del expediente.

Definición de los campos según guía de la AGE:

* **idaplicacion:** Identificador de la aplicación consumidora.
* **password:** password de la aplicación consumidora.
* **contenido:** Contenido en base64 que se desea firmar.
* **formatoFirma:** (Opcional)Formato de la firma a realizar. Las cadenas admitidas son:
*-Adobe PDF, PAdES:** Con este formato solo se pueden firmar y confirmar documentos PDF. No se permiten contrafirmas.
-XAdES Detached.
-XAdES Enveloped. Sólo ficheros XML
-CAdES.
-XAdES Manifest. Enviar a firmar el Hash (SHA512) en base64 del fichero.*

* **modoFirma:** (Opcional)Cadenas admitidas:
*-implicit
-explicit*

* **algoritmoFirma:** (Opcional)
*-SHA1withRSA (obsoleto)
-SHA256withRSA
-SHA384withRSA
-SHA512withRSA (recomendado)*

* **cofirmarSiFirmado:** En caso de que lo que se envíe ya sea una firma, este parámetro sirve para decidir si se quiere confirmar (en cuyo caso deberá tomar valor true (1)) o contrafirmar(false (0)).
* **nodeToSign:** Parámetro para indicar el atributo id de un nodo de un xml a firmar.(Básicamente para la firma del expediente ENI.)
