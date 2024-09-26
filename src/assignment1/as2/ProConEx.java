package assignment1.as2;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 1.생산자 스레드가 무한히 상품을 생산
 * 2.소비자 스레드를 5개 생성
 * 3.소비자 스레드의 우선순위를 1,3,5,8,10으로 설정
 * 4.모든 스레드가 시작되고 5초 뒤 메인메소드에서 다른 스레드들을 종료
 * 5.각 소비자 스레드는 자신의 총 소비 개수 표시
 **/

public class ProConEx {


    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();

        Queue<Integer> que = new LinkedList<Integer>();


        Thread proTh = new Producer(que);
        // Thread conTh = new Consumer(que, 1);



        Thread[] consumers = new Thread[5];
        int[] priorities = {1, 3, 5, 8, 10};

        //int i = 0;

        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(que, i + 1);
            consumers[i].setPriority(priorities[i]);
            consumers[i].start();
        }
        proTh.start();

        //

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            proTh.interrupt();

            for (Thread thread : consumers) {
                thread.interrupt();
            }

            proTh.join();

            for (Thread thread : consumers) {
                thread.join();
            }

        } catch (InterruptedException e) {
        }

        System.out.println("==========");
        System.out.print("메인 스레드 종료");
        long endTime = System.currentTimeMillis();

        System.out.println("( Total execution time: " + (endTime - startTime) + " milliseconds )");
        System.out.println("202012340 김승세\n");


    }
}


// 생산자 스레드
class Producer extends Thread {
    Queue<Integer> q;

    public Producer(Queue<Integer> q) {
        this.q = q;
    }


    @Override
    public void run() {
        int item;
        int zero = 0;

        while (!Producer.currentThread().isInterrupted()) {
            item = zero;
            synchronized (q) {
                q.offer(item);
                System.out.println("생산 : " + item);
                q.notify();
                zero++;
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("===== Producer !? =====");


		/*
		* for (int i = 0; i < 100; i++) {
			item = i;
			synchronized(q){
				q.offer(item);
				System.out.println("생산 : " + item);
				q.notify(); // 대기중인 소비자에게 알림
			}
			try {
				Thread.sleep((int)(Math.random() * 10));
			} catch (InterruptedException e) {}
		}
		* */

    }
}


// 소비자 스레드
class Consumer extends Thread {
    Queue<Integer> q;
    int number;

    // Constructor
    public Consumer(Queue<Integer> q, int i) {
        this.q = q;
        number = i;
    }


    public void run() {
        int item;
        int totalItem = 0; // 누적 소비 개수


        while (!Thread.currentThread().isInterrupted()) {
            synchronized (q) {
                while (q.peek() == null) { // 큐가 비어있으면 대기
                    try {
                        System.out.println("\t 소비자 " + number + "대기");
                        q.wait();
                    } catch (InterruptedException e) {
                        System.out.println("\t 소비자 " + number + "의 총 소비량: " + totalItem);
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                item = q.poll();
                System.out.println("\t 소비 : " + item);
                totalItem++;

            }
        }
        System.out.println("\t 소비자 " + number + "의 총 소비량: " + totalItem);
    }
}