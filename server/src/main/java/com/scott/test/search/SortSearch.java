package com.scott.test.search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by huo on 2018/7/25.
 */
public class SortSearch {

    public static void main(String[] args) throws InterruptedException {
        int count=0;
        while (true){
            //System.out.println("count:"+count);
            if(count==10000){
                break;
            }
            count++;
            List<Integer> list=new ArrayList<Integer>();
            for (int i=0;i<2232;i++){
                list.add(i);
            }
            BlockingQueue<Integer> blockingQueue=new LinkedBlockingQueue<Integer>(list);
            TestQueue a=new TestQueue(blockingQueue, "A");
            a.start();
            TestQueue b=new TestQueue(blockingQueue, "B");
            b.start();
            TestQueue c=new TestQueue(blockingQueue, "C");
            c.start();
            a.join();
            b.join();
            c.join();
            if(a.getSize()+b.getSize()+c.getSize()!=2232){
                System.out.println("总和"+(a.getSize()+b.getSize()+c.getSize()));
            }
        }

    }

    static class TestQueue extends Thread{

        BlockingQueue<Integer> blockingQueue;
        String name;
        int size=0;

        public int getSize() {
            return size;
        }

        public TestQueue(BlockingQueue<Integer> blockingQueue, String name) {
            this.blockingQueue = blockingQueue;
            this.setName(name);
        }

        @Override
        public void run() {
            while (!blockingQueue.isEmpty()){
                List<Integer> l=new ArrayList<Integer>();
                blockingQueue.drainTo(l,10);
                size+=l.size();
                if(l.size()!=10){
                    //System.out.println("l.size(): "+l.size());
                }
            }
            //System.out.println(this.getName() + " " + size);

        }
    }
}
