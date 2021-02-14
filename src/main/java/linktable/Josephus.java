package linktable;

import base.CGlibProxyFactory;

/**
 * @author dinglingfeng
 * @version 2020/7/17
 * @since JDK1.7
 * 约瑟夫问题
 * <p>
 * 人们站在一个等待被处决的圈子里。 计数从圆圈中的指定点开始，并沿指定方向围绕圆圈进行。
 * 在跳过指定数量的人之后，处刑下一个人。 对剩下的人重复该过程，从下一个人开始，
 * 朝同一方向跳过相同数量的人，直到只剩下一个人，并被释放。
 * <p>
 * 约瑟夫问题是个有名的问题：N个人围成一圈，从第一个开始报数，
 * 第M个将被杀掉，最后剩下一个，其余人都将被杀掉。
 * 例如N=6，M=5，被杀掉的顺序是：5，4，6，2，3。 活 1
 * 例如N=10，M=3，被杀掉的顺序是：3,6,9,2,7,1,8,5,10。 活 4
 */
public class Josephus {

    public static void main(String[] args) throws InterruptedException {
        Josephus josephus = (Josephus) new CGlibProxyFactory().createProxyInstance(new Josephus());
//        循环链表解法 -- 可以输出每次编号
        final int[] ints = josephus.josephusOneWayCycle(10, 3);
        showAnswer(ints);
        final int[] solves = josephus.solve(10, 3);
        showAnswer(solves);
        final int[] solves2 = josephus.josephusOneWayCycleImprovement(10, 3);
        showAnswer(solves2);


//        递归解法 -- 只会输出最后存活人员编号
        System.out.println(josephus.f(6, 5));
    }

    private static void showAnswer(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            if (i != ints.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("");
    }

    /*单向循环链表解法*/
    public int[] josephusOneWayCycle(int total, int num) {
        if (total <= 1) {
            return new int[]{0};
        }
//        1.构建链表
        final IOWCLinkList<Integer> onwWayCycleList = buildOneWayCycle(total);
//        2.递归 链表、num做参数，走num次杀掉一个人，返回一次下标
        return killPeopleByOneWayList(onwWayCycleList, num);
    }

    private int[] killPeopleByOneWayList(IOWCLinkList<Integer> cycleList, int num) {
        final int[] result = new int[cycleList.list() - 1];
        int resultOffset = 0;
        LinkListElement<Integer> nextDataElement = cycleList.getElementByOffset(0);
        while (cycleList.list() != 1) {
//            往下报num-1个，把num个作为下个循环的开始，将第num-1个出列
            for (int i = 0; i < num; i++) {
                if (i == num - 1) {
                    final LinkListElement<Integer> needPOP = nextDataElement;
                    cycleList.popElement(needPOP);
                    nextDataElement = needPOP.getNextElement();
                    result[resultOffset] = needPOP.getData();
                    resultOffset++;
                } else {
                    nextDataElement = nextDataElement.getNextElement();
                }
            }
        }
        return result;
    }

    private IOWCLinkList<Integer> buildOneWayCycle(int total) {
        IOWCLinkList result = new OneWayCycleNoHeadLinkList<Integer>();
        for (int i = 1; i <= total; i++) {
            result.add(i);
        }
        return result;
    }

    interface IOWCLinkList<T> {
        public void add(T data);

        public LinkListElement<T> popElement(LinkListElement element);

        public LinkListElement<T> getElementByOffset(int offset);

        public Integer list();
    }

    class OneWayCycleNoHeadLinkList<T> implements IOWCLinkList<T> {
        LinkListElement<T> firstElement;
        Integer length = 0;

        /*添加数据*/
        @Override
        public void add(T data) {
            if (firstElement == null) {
                firstElement = new LinkListElement<T>();
                firstElement.setData(data);
                firstElement.setNextElement(firstElement);
            } else {
                final LinkListElement<T> nextElement = new LinkListElement<>();
                nextElement.setData(data);
                nextElement.setNextElement(firstElement);
                final LinkListElement<T> lastElement = getElementByOffset(length - 1);
                lastElement.setNextElement(nextElement);
            }
            length++;

        }

        /*弹出某个结点*/
        @Override
        public LinkListElement<T> popElement(LinkListElement element) {
            LinkListElement result = new LinkListElement();
            if (firstElement.equals(element)) {
//            若第一个节点就是要弹出的节点,将第一个节点的数据给到要返回的节点
                result.setData(element.getData());
                result.setNextElement(element.getNextElement());
//                将最后一个结点指向第二个节点
                final LinkListElement<T> lastElement = getElementByOffset(length - 1);
                lastElement.setNextElement(firstElement.getNextElement());
//                将第一个节点替换为第二个节点
                firstElement = firstElement.getNextElement();
            } else {
                LinkListElement preElement = firstElement;
                for (int i = 1; i < length; i++) {
//                    从第二个结点开始遍历
                    final LinkListElement<T> nextElement = preElement.getNextElement();
                    if (nextElement.equals(element)) {
//                        若是要弹出的节点
//                        1.将result赋值为next节点的内容
                        result.setData(nextElement.getData());
                        result.setNextElement(nextElement.getNextElement());
//                        2.将pre节点指向next.next节点
                        preElement.setNextElement(nextElement.getNextElement());
                    } else {
//                        若不是要弹出的节点，记录当前节点为pre节点，继续遍历下一个
                        preElement = nextElement;
                    }
                }
            }
            length--;
            return result;
        }

        /*根据下标获取节点*/
        @Override
        public LinkListElement<T> getElementByOffset(int offset) {
            if (offset == 0) {
                return firstElement;
            }
            /*由于是循环链表可以一直往下获取，不用判断offset是否超出list长度*/
            LinkListElement result = firstElement;
            for (int i = 0; i < offset; i++) {
                result = result.getNextElement();
            }

            return result;
        }

        @Override
        public Integer list() {
            return length;
        }
    }

    class LinkListElement<T> {
        /*下一个节点*/
        LinkListElement nextElement;
        /*当前节点的下标*/
        T data;

        public LinkListElement<T> getNextElement() {
            return nextElement;
        }

        public void setNextElement(LinkListElement nextElement) {
            this.nextElement = nextElement;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    /*网上循环链表解法*/
    int[] solve(int total, int num) {
        if (total <= 1) {
            return new int[]{0};
        }
        int[] result = new int[total];
        int resultOffset = 0;
        // 创建环形链表
        Node head = createLinkedList(total);
        int count = 1;
        Node cur = head;
        Node pre = null;
        while (cur.next != cur) {
            if (count == num) {
                count = 1;
                pre.next = cur.next;
                result[resultOffset] = cur.date;
                resultOffset++;
                cur = pre.next;
            } else {
                count++;
                pre = cur;
                cur = cur.next;
            }
        }
        return result;
    }

    private Node createLinkedList(int total) {
        Node head = new Node(1);
        Node next = head;
        for (int i = 2; i <= total; i++) {
            Node temp = new Node(i);
            next.next = temp;
            next = temp;
        }
        next.next = head;
        return head;
    }

    class Node {
        int date;
        Node next;

        public Node(int date) {
            this.date = date;
        }
    }

    /*改进链表解法*/
    int[] josephusOneWayCycleImprovement(int total, int num) {
//        构建循环双向链表
        TwoWayNode firstNode = createLinke(total);
//        开始出人
        int count = 1;
        int resultOffset = 0;
        TwoWayNode curNode = firstNode;
        final int[] result = new int[total - 1];
        while(curNode.next != curNode){
            if(count == num){
//                需要出人
                count = 1;
                curNode.pre.next = curNode.next;
                curNode.next.pre = curNode.pre;
                result[resultOffset] = curNode.data;
                resultOffset ++;
                curNode = curNode.next;
            }else{
//                继续遍历
                count++;
                curNode = curNode.next;
            }
        }
        return result;
    }

    private TwoWayNode createLinke(int total) {
        final TwoWayNode firstNode = new TwoWayNode(1);
        firstNode.pre = firstNode;
        firstNode.next = firstNode;
        TwoWayNode curNode = firstNode;
        for (int i = 2; i <= total; i++) {
            TwoWayNode newNode = new TwoWayNode(i);
            firstNode.pre = newNode;
            curNode.next = newNode;
            newNode.pre = curNode;
            newNode.next = firstNode;
            curNode = newNode;
        }
        return firstNode;
    }

    class TwoWayNode {
        int data;
        TwoWayNode pre;
        TwoWayNode next;

        public TwoWayNode(int data) {
            this.data = data;
        }
    }

    /*网上递归解法--只会输出最后存活的编号，
    是通过递归计算出最后存活那个人的当时编号，再通过计算找到前一次编号是多少，一直倒推会第一次的编号
    所以只会算出最后存活人员*/
    int f(int n, int m) {
        return n == 1 ? n : (f(n - 1, m) + m - 1) % n + 1;
    }
}
