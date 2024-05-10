_POST /products\
Отвечает за создание нового продукта\
Требует @RequestBody ProductDto, price по умолчанию 0.0, inStock по умолчанию false\
name - varchar(255), description - varchar(4096), price(numeric(8,2)) check >= 0, inStock - boolean\
Пример тела запроса:\
{\
    "name":"Apple",\
    "description":"Apple description",\
    "price":25.5,\
    "inStock":true\
}\
Пример ответа:\
{\
    "id":1\
    "name":"Apple",\
    "description":"Apple description",\
    "price":25.5,\
    "inStock":true\
}



GET /products\
Возвращает список всех продуктов\
Пример ответа:\
[\
    {\
        "id":1\
        "name":"Apple",\
        "description":"Apple description",\
        "price":25.5,\
        "inStock":true\
    }\
]



PUT /products\
Отвечает за изменение продукта, заменит собой продукт с таким же id, если его нет, то создаст новый\
Требует @RequestBody ProductDto, см. GET /products\
Пример тела запроса:\
{\
    "id":1\
    "name":"Apple",\
    "description":"Apple description",\
    "price":25.5,\
    "inStock":true\
}\
Пример ответа:\
{\
    "id":1\
    "name":"Apple",\
    "description":"Apple description",\
    "price":25.5,\
    "inStock":true\
}



DELETE /products/{id}\
Удалит продукт с указанным {id}.\
Возвращает код 200 ОК.



GET /products/search\
Отвечает за поиск по продуктам. Возвращает список по указанным параметрам\
searchType - строка, указывающая тип поиска варианты указаны в SearchType enum,\
name - имя, которое используется в поиске по имени,\
price - цена, которая используется в поисках по цене,\
inStock - boolean, который использует в поике по inStock,\
sortType - строка, указывающая тип сортировки варианты указаны в SortType enum

Типы поиска:\
NONE - вернёт пустой список\
NAME - по части имени\
PRICE - по цене\
PRICEHIGHER - выше указанной цены\
PRICELOWER - ниже указанной цены\
INSTOCK - в наличии ли\
Типы сортировок:\
NONE - вернёт в том виде, в каком данные получены из бд\
NAME - сортировка по имени\
PRICE - сортировка по цене\

Пример запроса:
localhost:15001/products/search?searchType=NAME&&name=f&&price=1&&inStock=екгу&&sortType=NAME