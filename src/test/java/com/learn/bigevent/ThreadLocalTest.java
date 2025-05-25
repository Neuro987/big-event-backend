package com.learn.bigevent;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        // Create a ThreadLocal variable
        ThreadLocal tl = new ThreadLocal();

        new Thread(() -> {
            // Set a value in the ThreadLocal variable
            tl.set(" Thread 1");
            System.out.println(Thread.currentThread().getName() + tl.get());
        }, "A").start();

        new Thread(() -> {
            // Set a value in the ThreadLocal variable
            tl.set(" Thread 2");
            System.out.println(Thread.currentThread().getName() + tl.get());
        }, "B").start();

    }
}
