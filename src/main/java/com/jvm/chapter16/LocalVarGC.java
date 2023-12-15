package com.jvm.chapter16;

public class LocalVarGC {

    //YGC后，内存空间由 20077K->11934K
    //Full GC后，年轻代内存空间由: 11934K->0K   老年代空间由：8K->11771K   即将新生代中对象放到了老年代中
    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];//10MB
        System.gc();
    }

    //对象引用被置空，对象被回收
    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    //YGC的时候没有被释放掉
    //FullGC的时候 对象从新生代被放到老年代中
    //但是buffer并没有被回收， 局部变量表中只有我们的this，没有我们的buffer，因为slot位置为1的位置，被buffer占用的，但是没有被回收
    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    //相比上一个方法，buffer在slot中被占用，所以没有被回收，这个方法中slot中重写了value的值，
    //所以buffer中的值被回收
    public void localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    //经历两次GC，第一次将对象放到老年代中，第二次将老年代中的对象清除掉
    public void localvarGC5() {
        localvarGC1();
        System.gc();
    }

    public static void main(String[] args) {
        LocalVarGC local = new LocalVarGC();
        local.localvarGC5();
    }

}
