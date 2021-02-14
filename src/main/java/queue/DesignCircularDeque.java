package queue;

/**
 * @author dinglingfeng
 * @version 2020/7/30
 * @since JDK1.7
 * <p>
 * leetCode 641
 * //设计实现双端队列。
 * //你的实现需要支持以下操作：
 * //
 * // MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * // insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * // insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * // deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * // deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * // getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * // getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * // isEmpty()：检查双端队列是否为空。
 * // isFull()：检查双端队列是否满了。
 * //
 * // 示例：
 * // MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * //circularDeque.insertLast(1);			        // 返回 true
 * //circularDeque.insertLast(2);			        // 返回 true
 * //circularDeque.insertFront(3);			        // 返回 true
 * //circularDeque.insertFront(4);			        // 已经满了，返回 false
 * //circularDeque.getRear();  				// 返回 2
 * //circularDeque.isFull();				        // 返回 true
 * //circularDeque.deleteLast();			        // 返回 true
 * //circularDeque.insertFront(4);			        // 返回 true
 * //circularDeque.getFront();				// 返回 4
 * // 
 * // 提示：
 * //
 * // 所有值的范围为 [1, 1000]
 * // 操作次数的范围为 [1, 1000]
 * // 请不要使用内置的双端队列库。
 */
public class DesignCircularDeque {
    public static void main(String[] args) {
//        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
//        System.out.println(circularDeque.insertLast(1));                    // 返回 true
//        System.out.println(circularDeque.insertLast(2));                    // 返回 true
//        System.out.println(circularDeque.insertFront(3));                    // 返回 true
//        System.out.println(circularDeque.insertFront(4));                    // 已经满了，返回 false
//        System.out.println(circularDeque.getRear());                // 返回 2
//        System.out.println(circularDeque.isFull());                        // 返回 true
//        System.out.println(circularDeque.deleteLast());                    // 返回 true
//        System.out.println(circularDeque.insertFront(4));                    // 返回 true
//        System.out.println(circularDeque.getFront());                // 返回 4
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());//错
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class MyCircularDeque {
        int[] array;
        int head;
        int tail;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            array = new int[k + 1];
            head = tail = 0;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (!isFull()) {
//                未满，头插，head--
                head = head == 0 ? head = array.length - 1 : head - 1;
                array[head] = value;
                return true;
            }
            return false;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (!isFull()) {
//                未满，尾插，tail++
                array[tail] = value;
                tail = tail == array.length - 1 ? 0 : tail + 1;
                return true;
            }
            return false;
        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (!isEmpty()) {
//                不为空，头删，head++
                head = head == array.length - 1 ? 0 : head + 1;
                return true;
            }
            return false;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (!isEmpty()) {
//                不为空，尾删，tail--
                tail = tail == 0 ? array.length - 1 : tail - 1;
                return true;
            }
            return false;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            return isEmpty() ? -1 : array[head];
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            return isEmpty() ? -1 : tail == 0 ? array[array.length - 1] : array[tail - 1];
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return tail == head;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return (tail + 1) % array.length == head;
        }
    }

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
