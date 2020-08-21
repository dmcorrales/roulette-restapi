# Roulete App!

_Este proyecto se ha realizado con el propósito de hacer la prueba técnica para Masivian._

## Comenzando 🚀
_Se han tomado en cuenta cada uno de los principos del **Clean Code**, a continuación encontrarás una guía
de las Paths o Rutas de la aplicación_

### 1. Endpoint de creación de nuevas ruletas que devuelva el id de la nueva ruleta creada

_[POST] <host>:<port>/roulette_

```{
    "status": 201,
    "ok": true,
    "error": null,
    "message": "ddfb4526dfeb47f4b08f6a1802e7643c",
    "content": null
}```


### 2. Endpoint de apertura de ruleta (el input es un id de ruleta) que permita las
posteriores peticiones de apuestas, este debe devolver simplemente un estado que
confirme que la operación fue exitosa o denegada

### 3. Endpoint de apuesta a un número (los números válidos para apostar son del 0 al 36)
o color (negro o rojo) de la ruleta una cantidad determinada de dinero (máximo
10.000 dólares) a una ruleta abierta.
(nota: este enpoint recibe además de los parámetros de la apuesta, un id de usuario
en los HEADERS asumiendo que el servicio que haga la petición ya realizo una
autenticación y validación de que el cliente tiene el crédito necesario para realizar la
apuesta)

### 4. Endpoint de cierre apuestas dado un id de ruleta, este endpoint debe devolver el
resultado de las apuestas hechas desde su apertura hasta el cierre.


### 5. Endpoint de listado de ruletas creadas con sus estados (abierta o cerrada)
