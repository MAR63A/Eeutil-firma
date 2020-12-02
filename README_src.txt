*************************************************************************************************
* eeutil-firma-src.zip
* EEUtil-firma se distribuye bajo la la licencia EUPL1.1.
*************************************************************************************************
Los componentes incluidos en eeutil-firma-src.zip se enumeran a continuaci�n:

	- fuentes: Contiene el c�digo fuente de los m�dulos:
		- eeutil-afirmaws-stub
		- eeutil-comunes
		- eeutil-firma
		- eeutil-inside-stub
		- eeutil-model
		- eeutil-mvn-base
		- eeutil-pdf-conversion
		- eeutil-pdf-conversion-igae
		- eeutil-services
		- eeutil-util
		- load-tables

	- lib: Librer�as que usa la aplicaci�n EEUtil-firma
	- resources
		
		- config
			- certificados: contiene un keystore de prueba
				- sello_dtic_age_pruebas.p12
			- database.properties
			- formatosFirmas.properties
			- log4j.properties
			- textos.errores.firma.properties

		- external-libs: librer�as externas y necesarias de instalar manualmente
			- afirma
			- igae
			- jodconverter
		
		- scripts_bbdd: Scripts de BBDD
			- 01-create_tables.sql
			- 02-inserts.sql
			- 03-create_procedures.sql
			- 04-insert-prueba-test-user.sql
			- 05-insert-admin-eeutil-misc.sql
	
		
	- licenses: Contiene las licencias bajo las que se distribuyen las librer�as de las que depende EEUtil-firma.

	- LICENSE_EUPL_1.1.pdf: Licencia EUPL1.1, bajo la que se distribuye Inside.
	



