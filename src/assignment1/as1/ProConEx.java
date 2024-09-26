package assignment1.as1;

import java.util.LinkedList;
import java.util.Queue;

public class ProConEx {
    public static void main(String args[]) {
        Queue<Integer> que = new LinkedList<Integer>();

        // 생산자 스레드 생성 및 시작
        Thread proTh = new Producer(que);
        proTh.start();

        // 5개의 소비자 스레드 생성
        Thread[] conThreads = new Thread[5];
        int[] priorities = {1, 3, 5, 8, 10};

        for (int i = 0; i < 5; i++) {
            conThreads[i] = new Consumer(que, i + 1);
            conThreads[i].setPriority(priorities[i]); // 우선순위 설정
            conThreads[i].start();
        }

        // 5초 대기
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}

        // 모든 스레드에 인터럽트 걸기
        proTh.interrupt();
        for (Thread conTh : conThreads) {
            conTh.interrupt();
        }

        // 스레드 종료 대기
        try {
            proTh.join();
            for (Thread conTh : conThreads) {
                conTh.join();
            }
        } catch (InterruptedException e) {}

        System.out.println("메인 스레드 종료");
    }
}

// 생산자 스레드
class Producer extends Thread {
    Queue<Integer> q;
    public Producer(Queue<Integer> q) {
        this.q = q;
    }

    public void run() {
        int item = 0;
        while (!Thread.currentThread().isInterrupted()) { // 무한 루프
            synchronized(q) {
                q.offer(item);
                System.out.println("생산 : " + item);
                item++; // 다음 아이템 생성
                q.notify(); // 대기중인 소비자에게 알림
            }
            try {
                Thread.sleep((int)(Math.random() * 10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 인터럽트 상태 복원
            }
        }
        System.out.println("생산자 스레드 종료");
    }
}

// 소비자 스레드
class Consumer extends Thread {
    Queue<Integer> q;
    int number;

    public Consumer(Queue<Integer> q, int i) {
        this.q = q;
        number = i;
    }

    public void run() {
        int item;
        int totalItem = 0; // 누적 소비 개수

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!Thread.currentThread().isInterrupted()) {
            synchronized(q) {
                while (q.peek() == null) { // 큐가 비어있으면 대기
                    try {
                        System.out.println("\t 소비자 " + number + " 대기");
                        q.wait();
                    } catch (InterruptedException e){
                        System.out.println("\t 소비자 " + number + " 총 누적 소비: " + totalItem);
                        Thread.currentThread().interrupt(); // 인터럽트 상태 복원
                        return; // 스레드 종료
                    }
                }
                // 큐에서 아이템 소비
                item = q.poll();
                System.out.println("\t 소비자 " + number + " 소비 : " + item);
                totalItem++;
            }
        }
        System.out.println("\t 소비자 " + number + " 총 누적 소비: " + totalItem);
    }
}

