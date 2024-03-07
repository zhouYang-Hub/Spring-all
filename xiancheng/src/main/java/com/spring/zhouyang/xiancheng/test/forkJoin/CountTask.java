package com.spring.zhouyang.xiancheng.test.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * CountTask:
 *
 * @author zhouYang
 * @date 2024/02/29
 */
public class CountTask extends RecursiveTask<Integer> {

    // 设置分隔阈值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        /**
         *
         ForkJoinPool forkJoinPool = new ForkJoinPool();
         // 生成一个计算任务，负责计算 1+2+3+4
         CountTask countTask = new CountTask(1, 4);
         Future<Integer> result = forkJoinPool.submit(countTask);

         try {

         System.out.println(result.get());

         } catch (InterruptedException e) {
         e.printStackTrace();
         } catch (Exception e) {
         e.printStackTrace();
         }
         System.out.println(result);

         */
        //1+2+3+4
        System.out.println("返回结果" + (1 + 2 + 3 + 4));
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start) + "ms");


    }


}
