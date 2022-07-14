package com.lc.structure.zother;

public class BitMap {
    /**
     * 字节数组，用于保存数据
     */
    private byte[] bytes;

    /**
     * 最大容量，能存的最大数
     */
    private int capacity;

    public BitMap(int capacity) {
        if (capacity < 0) {
            throw new NegativeArraySizeException("capacity < 0: " + capacity);
        }
        this.capacity = capacity;
        /*
         * 根据最大容量确定字节数组的大小
         * 1.位图就是用位（bit）数表示这个整数的值；例如：整数100就标记在100位（bit）上
         * 2.因为一个字节是8位（bit），所以整数n就在bytes[n / 8]的第（n % 8）位上
         * 3.进而，字节数组的容量就是[(capacity >>> 3) + 1]
         */
        bytes = new byte[(capacity >>> 3) + 1];
    }

    public void put(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException("n < 0: " + n);
        }
        // n/8得到byte[]的index
        int index = n >>> 3;
        // n%8( 等价于n&(8-1) )得到在byte[index]上的位置position
        int position = n & 0x07;
        /*
         * 将1左移position后和bytes[index]做【或】运算，就把n插入到了BitMap中
         * 例如假设原来BitMap中存有(4,7)，现在要插入5
         *   4,7: bytes[0] -> 1 0 0 1 0 0 0 0（原来的4,7）
         *     5: bytes[0] -> 0 0 1 0 0 0 0 0（1左移5%8=5位）
         * 4,5,7: bytes[0] -> 1 0 1 1 0 0 0 0
         * 要是插入9呢？
         *   4,7: bytes[0] -> 1 0 0 1 0 0 0 0（原来的4,7）
         *        bytes[1] -> 0 0 0 0 0 0 0 0（bytes[1]中标记8-15，而BitMap中只有(4,7)，故bytes[1]的位全是0）
         *     9: bytes[1] -> 0 0 0 0 0 0 1 0（1左移9%8=1位）
         * 4,7,9: bytes[0] -> 1 0 0 1 0 0 0 0
         *        bytes[1] -> 0 0 0 0 0 0 1 0
         */
        bytes[index] |= 1 << position;
    }

    public void clear(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException("n < 0: " + n);
        }
        // n/8得到byte[]的index
        int index = n >>> 3;
        // n%8得到在byte[index]上的位置position
        int position = n & 0x07;
        /*
         * 将1左移position后【取反】再和bytes[index]做【与】运算，就把n清除了
         * 例如，假设原来BitMap中存有(4,7)，现在要清除4
         *   4: bytes[0] -> 0 0 0 1 0 0 0 0（1左移4%8=4位）
         *  ~4: bytes[0] -> 1 1 1 0 1 1 1 1（取反）
         * 4,7: bytes[0] -> 1 0 0 1 0 0 0 0（原来的4,7）
         *   7: bytes[0] -> 1 0 0 0 0 0 0 0
         */
        bytes[index] &= ~(1 << position);

    }

    public boolean contain(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException("n < 0: " + n);
        }
        // n/8得到byte[]的index
        int index = n >>> 3;
        // n%8得到在byte[index]上的位置position
        int position = n & 0x07;
        /*
         * 将1左移position后和bytes[index]做【与】运算，判断是否为0即可
         * 例如，假设原来BitMap中存有(4,7)，现在要判断4是否存在
         *   4: bytes[0] -> 0 0 0 1 0 0 0 0（1左移4%8=4位）
         * 4,7: bytes[0] -> 1 0 0 1 0 0 0 0（原来的4,7）
         *   4: bytes[0] -> 0 0 0 1 0 0 0 0（true）
         * 要判断3呢？
         *   3: bytes[0] -> 0 0 0 0 1 0 0 0（1左移3%8=3位）
         * 4,7: bytes[0] -> 1 0 0 1 0 0 0 0（原来的4,7）
         *   0: bytes[0] -> 0 0 0 0 0 0 0 0（false）
         */
        return (bytes[index] & (1 << position)) != 0;
    }
}
