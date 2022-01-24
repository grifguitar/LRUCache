import org.junit.Assert;

public class Test extends Assert {

    @org.junit.Test
    public void commonTest() {
        LRUCache<Integer, String> lruCache = new LRUCacheImpl<>(4);

        lruCache.put(5, "5");
        lruCache.put(4, "4");
        lruCache.put(6, "6");
        lruCache.put(3, "3");
        lruCache.put(7, "7");

        print("size", "4");
        assertEquals(4, lruCache.size());

        assertEquals(
                "{4, 4} {6, 6} {3, 3} {7, 7} ",
                print(lruCache)
        );

        print("get", "6");
        assertEquals("6", lruCache.get(6));

        assertEquals(
                "{4, 4} {3, 3} {7, 7} {6, 6} ",
                print(lruCache)
        );

        print("get", "3");
        assertEquals("3", lruCache.get(3));

        assertEquals(
                "{4, 4} {7, 7} {6, 6} {3, 3} ",
                print(lruCache)
        );

        print("put", "2");
        lruCache.put(2, "2");

        assertEquals(
                "{7, 7} {6, 6} {3, 3} {2, 2} ",
                print(lruCache)
        );

        print("remove", "10");
        lruCache.remove(10);

        assertEquals(
                "{7, 7} {6, 6} {3, 3} {2, 2} ",
                print(lruCache)
        );

        print("remove", "7");
        lruCache.remove(7);

        assertEquals(
                "{6, 6} {3, 3} {2, 2} ",
                print(lruCache)
        );

        print("remove", "2");
        lruCache.remove(2);

        assertEquals(
                "{6, 6} {3, 3} ",
                print(lruCache)
        );

        print("size", "2");
        assertEquals(2, lruCache.size());

        assertEquals(
                "{6, 6} {3, 3} ",
                print(lruCache)
        );

        print("remove", "3");
        lruCache.remove(3);

        assertEquals(
                "{6, 6} ",
                print(lruCache)
        );

        print("remove", "6");
        lruCache.remove(6);

        assertEquals(
                "",
                print(lruCache)
        );

        print("size", "0");
        assertEquals(0, lruCache.size());

        assertEquals(
                "",
                print(lruCache)
        );

        print("put", "10");
        lruCache.put(10, "10");

        assertEquals(
                "{10, 10} ",
                print(lruCache)
        );

        print("clear", "");
        lruCache.clear();

        assertEquals(
                "",
                print(lruCache)
        );

    }

    private static <K, V> String print(LRUCache<K, V> lruCache) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("===== lruCache =====");
        for (Pair<K, V> elem : lruCache) {
            String s = elem + " ";
            stringBuilder.append(s);
            System.out.print(s);
        }
        System.out.println();
        return stringBuilder.toString();
    }

    private static void print(String name, String value) {
        System.out.println("-----");
        System.out.println(name + " " + value);
    }

}
