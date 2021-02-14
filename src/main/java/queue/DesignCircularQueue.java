package queue;

/**
 * @author dinglingfeng
 * @version 2020/7/29
 * @since JDK1.7
 * <p>
 * leetCode 622
 * //设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”
 * //。
 * // 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环
 * //队列，我们能使用这些空间去存储新的值。
 * //
 * // 你的实现应该支持如下操作：
 * // MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * // Front: 从队首获取元素。如果队列为空，返回 -1 。
 * // Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * // enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * // deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * // isEmpty(): 检查循环队列是否为空。
 * // isFull(): 检查循环队列是否已满。
 * //
 * // 示例：
 * // MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * //circularQueue.enQueue(1);  // 返回 true
 * //circularQueue.enQueue(2);  // 返回 true
 * //circularQueue.enQueue(3);  // 返回 true
 * //circularQueue.enQueue(4);  // 返回 false，队列已满
 * //circularQueue.Rear();  // 返回 3
 * //circularQueue.isFull();  // 返回 true
 * //circularQueue.deQueue();  // 返回 true
 * //circularQueue.enQueue(4);  // 返回 true
 * //circularQueue.Rear();  // 返回 4
 * //
 * // 提示：
 * //
 * // 所有的值都在 0 至 1000 的范围内；
 * // 操作数将在 1 至 1000 的范围内；
 * // 请不要使用内置的队列库。
 */
public class DesignCircularQueue {
    public static void main(String[] args) {
//        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//        System.out.println(circularQueue.enQueue(1));// 返回 true
//        System.out.println(circularQueue.enQueue(2)); // 返回 true
//        System.out.println(circularQueue.enQueue(3)); // 返回 true
//        System.out.println(circularQueue.enQueue(4)); // 返回 false，队列已满
//        System.out.println(circularQueue.Rear()); // 返回 3
//        System.out.println(circularQueue.isFull()); // 返回 true
//        System.out.println(circularQueue.deQueue()); // 返回 true
//        System.out.println(circularQueue.enQueue(4)); // 返回 true
//        System.out.println(circularQueue.Rear()); // 返回 4

        MyCircularQueue myCircularQueue = new MyCircularQueue(6);
        System.out.println(myCircularQueue.enQueue(6));
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(5));
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.Front());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.deQueue());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class MyCircularQueue {
        //        存储
        int[] array;
        //        头指针
        int head;
        //        尾指针
        int tail;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            array = new int[k + 1];
            head = tail = 0;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {
            if (!isFull()) {
//                不满时可以加,入之后tail++;
                array[tail] = value;
                tail = tail == array.length - 1 ? 0 : tail + 1;
                return true;
            }
            return false;
        }

//        0,1,2,3

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {
            if (!isEmpty()) {
//                不为空时可以出，出了之后head++;
                head = head == array.length - 1 ? 0 : head + 1;
                return true;
            }
            return false;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {
            return isEmpty() ? -1 : array[head];
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {
            return isEmpty() ? -1 : array[tail == 0 ? array.length - 1 : tail - 1];
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return head == tail;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            return (1 + tail) % array.length == head;
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
