public class Main {
    public static void main(String[] args) {
        FAAQueue<Integer> q = new FAAQueue<>();
        q.enqueue(2);
//        q.enqueue(6);
//        q.enqueue(-6);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
