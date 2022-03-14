package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceExample {
    private String status = "Hi I am active";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReferenceExample [status=" + status + "]";
    }

    public void strongReference() {
        ReferenceExample ex = new ReferenceExample();
        System.out.println(ex);
    }

    public void softReference() {
        SoftReference<ReferenceExample> ex = new SoftReference<ReferenceExample>(getRefrence());
        System.out.println("Soft refrence :: " + ex.get());
    }

    public void weakReference() {
        int counter = 0;
        WeakReference<ReferenceExample> ex = new WeakReference<ReferenceExample>(getRefrence());
        while (ex.get() != null) {
            counter++;
            System.gc();
            System.out.println("Weak reference deleted  after:: " + counter + ex.get());
        }
    }

    public void phantomReference() throws InterruptedException {
        final ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<ReferenceExample> ex = new PhantomReference<ReferenceExample>(getRefrence(), queue);
        System.gc();
        queue.remove();
        System.out.println("Phantom reference deleted  after");
    }

    private ReferenceExample getRefrence() {
        return new ReferenceExample();
    }

    public static void main(String[] args) {
        ReferenceExample ex = new ReferenceExample();
        ex.strongReference();
        ex.softReference();
        ex.weakReference();
        try {
            ex.phantomReference();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}