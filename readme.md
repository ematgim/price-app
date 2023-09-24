# Price app

Aquí esta en proyecto se utiliza una base de datos Mysql. El projecto esta hecho en Arquitectura Hexagonal.

Contiene 3 capas:

- Dominio: Contiene los modelos de negocio y toda la logica referente al negocio, el repositorio
- Aplicación: Contiene los casos de uso
- Infraestructura: Contiene las implementaciones de los repositorios y los controladores de Spring.


## Modulos
La aplicación esta formada por dos módulos.
### Rate
Contiene toda la lógica de negocio de el contexto de tarifas

### Shared
Contiene todo lo que se va a compartir entre los diferentes contextos. Aquí encontraremos las implementaciones de los conectores con spring y BD, Las clases abstractas para crear los VOs y aggregados. Creación de criterias con los modelos de dominio y sus conversores a criteria de spring etc. También tenemos todo lo relacionado con springboot y su configuración.

# Uso

## Ejecutar test
Para ejecutar los test se levantara la BD mysql el contenedor de test y se ejecutaran los test a través de gradle

     make test

## Ejecutar build
Para ejecutar la build se ejecuta el comando

    make build


## Levantar la maquina en local sin compilar

	make run



# ENDPOINTS
## Crear tarifa

Este endpoint nos permite crear un nuevo rate.
> POST /api/rate/create

Body:

    {
    "endDate":  "2024-12-09T18:25:58+01:00[Europe/Paris]",
    "startDate":  "2021-12-09T18:25:58.6037597+01:00[Europe/Paris]",
    "priceListId":1,
    "productId":1,
    "priority":2,
    "price":20.00,
    "currency":"EUR"
    }


## Buscar tarifa

> GET /api/rate/search?productId=1&date=2024-12-09T18:25:58+01:00[Europe/Paris]

Response

	{
	"productId":  1,
	"endDate":  "2024-12-09T18:25:58",
	"price":  10.0,
	"rateId":  5,
	"startDate":  "2021-12-09T18:25:59"
	}
 

