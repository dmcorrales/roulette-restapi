# Roulette App!

_Prueba técnica masivian._

## Comenzando 🚀
_Se han tomado en cuenta cada uno de los principos del **Clean Code**, a continuación encontrarás una guía
de las Paths o Rutas de la aplicación_

### 1. Endpoint de creación de nuevas ruletas que devuelva el id de la nueva ruleta creada

_[POST] host:port/roulette_

_BODY PARAMS (OPCIONAL)_

```
{"status":true}
```

_RESULTADO_

```
{
    "status": 201,
    "ok": true,
    "error": null,
    "message": "ddfb4526dfeb47f4b08f6a1802e7643c",
    "content": null
}
```


### 2. Endpoint de apertura de ruleta (el input es un id de ruleta) que permita las
posteriores peticiones de apuestas, este debe devolver simplemente un estado que
confirme que la operación fue exitosa o denegada

_[GET] host:port/roulette/opening/ddfb4526dfeb47f4b08f6a1802e7643c_

_RESULTADO_

```
{
    "status": 200,
    "ok": true,
    "error": null,
    "message": "Se ha realizado correctamente la apertura de la ruleta",
    "content": {
        "id": "ddfb4526dfeb47f4b08f6a1802e7643c",
        "status": true,
        "bet": []
    }
}
```

### 3. Endpoint de apuesta a un número (los números válidos para apostar son del 0 al 36)
o color (negro o rojo) de la ruleta una cantidad determinada de dinero (máximo
10.000 dólares) a una ruleta abierta.
(nota: este enpoint recibe además de los parámetros de la apuesta, un id de usuario
en los HEADERS asumiendo que el servicio que haga la petición ya realizo una
autenticación y validación de que el cliente tiene el crédito necesario para realizar la
apuesta)

_[GET] host:port/roulette/bet/ddfb4526dfeb47f4b08f6a1802e7643c_

_[HEADERS]_

```
user-id 6d610554cc0c47d2921212ef07a3b929
```

_[BODY]_

```
{"money":15, "number":22}
{"money":15, "color":"rojo"} {"money":15, "color":"negro"}
```

_RESULTADO_

```
{
    "status": 200,
    "ok": true,
    "error": null,
    "message": "Se ha realizado la apuesta",
    "content": {
        "id": "ddfb4526dfeb47f4b08f6a1802e7643c",
        "status": true,
        "bet": [
            {
                "value": "22",
                "resultValue": "20",
                "cash": 15.0,
                "user": {
                    "id": "6d610554cc0c47d2921212ef07a3b929",
                    "name": "Daniel Corrales",
                    "balance": 0.0
                }
            }
        ]
    }
}
```


### 4. Endpoint de cierre apuestas dado un id de ruleta, este endpoint debe devolver el
resultado de las apuestas hechas desde su apertura hasta el cierre.

_[GET] host:port/roulette/closing/ddfb4526dfeb47f4b08f6a1802e7643c_

_RESULTADO_

```
{
    "status": 200,
    "ok": true,
    "error": null,
    "message": "Se ha realizado correctamente el cierre de la ruleta",
    "content": {
        "id": "ddfb4526dfeb47f4b08f6a1802e7643c",
        "status": false,
        "bet": [
            {
                "value": "22",
                "resultValue": "20",
                "cash": 15.0,
                "user": {
                    "id": "6d610554cc0c47d2921212ef07a3b929",
                    "name": "Daniel Corrales",
                    "balance": 0.0
                }
            }
        ]
    }
}
```

### 5. Endpoint de listado de ruletas creadas con sus estados (abierta o cerrada)

_[GET] host:port/roulette/_

_RESULTADO_

```
[
    {
        "id": "ddfb4526dfeb47f4b08f6a1802e7643c",
        "status": false,
        "bet": [
            {
                "value": "22",
                "resultValue": "20",
                "cash": 15.0,
                "user": {
                    "id": "6d610554cc0c47d2921212ef07a3b929",
                    "name": "Daniel Corrales",
                    "balance": 0.0
                }
            }
        ]
    }
]
```

---
⌨️ con ❤️ por [Daniel Corrales](https://github.com/dmcorrales) 😊