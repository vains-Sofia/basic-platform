package com.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@AutoConfigureMockMvc
class RedisLockControllerConcurrencyTest {

    @Autowired
    private MockMvc mockMvc;

    private static final int THREAD_COUNT = 20;

    @Test
    void testConcurrentAccessToTest01() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(executor.submit(() -> {
                latch.countDown(); // 等待所有线程准备好
                latch.await();     // 一起发起请求
                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/redis/lock/test01"))
                        .andReturn();
                return result.getResponse().getContentAsString();
            }));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        int success = 0;
        int failed = 0;

        for (Future<String> future : futures) {
            String response = future.get();
            if (response.contains("已售罄")) {
                failed++;
            } else {
                success++;
            }
        }

        System.out.println("成功请求数: " + success);
        System.out.println("失败请求数: " + failed);

        // 断言
        Assertions.assertEquals(10, success, "成功应该是10个");
        Assertions.assertEquals(10, failed, "失败应该是10个");
    }

    @Test
    void testConcurrentAccessToTest02() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(executor.submit(() -> {
                latch.countDown(); // 等待所有线程准备好
                latch.await();     // 一起发起请求
                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/redis/lock/test02"))
                        .andReturn();
                return result.getResponse().getContentAsString();
            }));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        int success = 0;
        int failed = 0;

        for (Future<String> future : futures) {
            String response = future.get();
            if (response.contains("超过限额")) {
                failed++;
            } else {
                success++;
            }
        }

        System.out.println("成功请求数: " + success);
        System.out.println("失败请求数: " + failed);

        Assertions.assertEquals(10, success, "成功应该是10个");
        Assertions.assertEquals(10, failed, "失败应该是10个");
    }

}
