package assignment1;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 1.생산자 스레드가 무한히 상품을 생산
 * 2.소비자 스레드를 5개 생성
 * 3.소비자 스레드의 우선순위를 1,3,5,8,10으로 설정
 * 4.모든 스레드가 시작되고 5초 뒤 메인메소드에서 다른 스레드들을 종료
 * 5.각 소비자 스레드는 자신의 총 소비 개수 표시
 * **/

public class ProConEx {


	public static void main(String args[]) {
		Queue<Integer> que = new LinkedList<Integer>();	
		Thread proTh = new Producer(que);
		Thread conTh = new Consumer(que,1);
		proTh.start();
		conTh.start();
		try {
			proTh.join();
			conTh.interrupt();
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
		int item; 
		for (int i = 0; i < 100; i++) { 
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
	}
}




// 소비자 스레드
class Consumer extends Thread {
	Queue<Integer> q; int number;
	public Consumer(Queue<Integer> q, int i) {
		this.q = q; number = i;
	}  
	public void run() {
		int item; 
		int totalItem = 0; // 누적 소비 개수
		while(!Thread.currentThread().isInterrupted()) {
			synchronized(q){
				if(q.peek() == null) { // 큐가 비어있으면 대기 
					try {
						System.out.println("\t 소비자 " + number + "대기");
						q.wait(); 
					} catch (InterruptedException e){
						System.out.println("\t 총 누적 소비: " + totalItem);
						Thread.currentThread().interrupt();
					}
				}
				else {
					item = q.poll(); 
					System.out.println("\t 소비 : " + item);
					totalItem++; 
				}
			}
		}
	}
}