# LRUCache

Выполнил: студент группы М34371, Хлытин Григорий.

## Test

Создаем экземпляр `LRUCacheImpl`, устанавливаем `maxSize` = 4:

```haskell
LRUCache<Integer, String> lruCache = new LRUCacheImpl<>(4);
```

Заполняем данными:

```haskell
lruCache.put(5, "5");
lruCache.put(4, "4");
lruCache.put(6, "6");
lruCache.put(3, "3");
lruCache.put(7, "7");
```

Общий тест:

```haskell
-----
size 4
===== lruCache =====
{4, 4} {6, 6} {3, 3} {7, 7} 
-----
get 6
===== lruCache =====
{4, 4} {3, 3} {7, 7} {6, 6} 
-----
get 3
===== lruCache =====
{4, 4} {7, 7} {6, 6} {3, 3} 
-----
put 2
===== lruCache =====
{7, 7} {6, 6} {3, 3} {2, 2} 
-----
remove 10
===== lruCache =====
{7, 7} {6, 6} {3, 3} {2, 2} 
-----
remove 7
===== lruCache =====
{6, 6} {3, 3} {2, 2} 
-----
remove 2
===== lruCache =====
{6, 6} {3, 3} 
-----
size 2
===== lruCache =====
{6, 6} {3, 3} 
-----
remove 3
===== lruCache =====
{6, 6} 
-----
remove 6
===== lruCache =====

-----
size 0
===== lruCache =====

-----
put 10
===== lruCache =====
{10, 10} 
-----
clear 
===== lruCache =====

```