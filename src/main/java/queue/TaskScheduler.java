package queue;


import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author dinglingfeng
 * @version 2020/7/30
 * @since JDK1.7
 * <p>
 * leetCode 621
 * //给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
 * //都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * // 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * // 你需要计算完成所有任务所需要的最短时间。
 * //
 * // 示例 ： 输入：tasks = ["A","A","A","B","B","B"], n = 2  输出：8
 * //解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * //     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * //
 * // 提示：
 * // 任务的总个数为 [1, 10000]。
 * // n 的取值范围为 [0, 100]。
 */
public class TaskScheduler {
    public static void main(String[] args) {
        Solution solution = new TaskScheduler().new Solution();
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'C', 'C', 'B', 'B'}, 2));//6
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));//16
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G'}, 2));//14
        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G','H','H',
                'I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R','S','S',
                'T','T','U','U','V','V','W','W','X','X','Y','Y','Z','Z'}, 2));//52
    }

    //A,B,C,Z,B,C,D,E,Y,D,E,F,G,H,I,G,H,I,J,K,L,J,K,L,M,N,O,M,N,O,P,Q,R,P,Q,R,S,T,U,S,T,U,V,W,X,V,W,X,F,A,Y,Z
    // A - B - C - A - D - E - A - F - G - A - 等待 - 等待 - A - 等待 - 等待 - A
    //leetcode submit region begin(Prohibit modification and deletion)
        class Solution {
            class Node {
                int num;
                char taskName;
                Node pre;
                Node next;

                public Node(int num, char taskName) {
                    this.num = num;
                    this.taskName = taskName;
                }

                public Node() {
                }
            }

            public int leastInterval(char[] tasks, int n) {
                Deque<Character> runQueue = new LinkedBlockingDeque<>();
    //            Queue<Character> waitQueue = new ArrayBlockingQueue<>(tasks.length);
                Map<Character, Node> taskNum = new HashMap<>();
                Node head = new Node();
                Node last = head;
                for (char c : tasks) {
                    Node node = taskNum.get(c);
                    if (node == null) {
                        node = new Node();
                        node.num = 1;
                        node.taskName = c;
    //                    新增的节点，若不是空链，由于次数肯定最小，直接丢进尾节点，空链直接入链
                        if (last == head) {
                            head.next = node;
                            node.pre = head;
                            last = node;
                        } else {
                            last.next = node;
                            node.pre = last;
                            last = node;
                        }
                        taskNum.put(c, node);
                    } else {
    //                    已存在节点
                        node.num++;
                        if (node.num > node.pre.num && node.pre != head) {
    //                        已经比前节点大了，交换节点(由于每次num只+1，本身链表既是有序的，所以只需要和后一个节点判断即可，不用往后判断）
                            if (node == last) {
    //                            是尾结点的话，更新尾结点为前一个节点
                                last = node.pre;
                            }
                            swapToForward(node);
                        }
    //                    否则不做变动
                    }
                }
    //            得到有序链表head;
                Map<Character, Integer> taskStop = new HashMap<>();
                runQueue.add(pollLink(head.next));
                taskStop.put(runQueue.getLast(), 0);
                while (head.next != null) {
    //                当前符合条件，且耗时最久（重复次数最多的任务）
                    startTask(runQueue, head, taskStop, n);
                }
                int result = runQueue.size();
                while (!runQueue.isEmpty()) {
                    System.out.print(runQueue.poll() + ",");
                }
                return result;
            }

            /*向前交换节点*/
            private void swapToForward(Node node) {
                node.pre.next = node.next;
                if (node.next != null) {
    //              node.next == null 是尾结点，若是尾结点的话不需要改node.next的pre
                    node.next.pre = node.pre;
                }
                node.pre = node.pre.pre;
                node.next = node.pre.next;
                node.pre.next.pre = node;
                node.pre.next = node;
            }

            /*任务移出（1.任务数--，2.判断任务数若为0将节点移出链表*/
            private Character pollLink(Node pollNode) {
                if (pollNode.num == 1) {
                    pollNode.pre.next = pollNode.next;
                    if (pollNode.next != null) {
    //                    尾结点的next为null，不需要操作
                        pollNode.next.pre = pollNode.pre;
                    }
                } else {
                    pollNode.num--;
                    Node p = pollNode;
                    while (p != null) {
                        p = p.next;
                        if (p == null || pollNode.num >= p.num) {
                            break;
                        } else {
                            swapToForward(p);
                            p = p.next;
                        }
                    }
                }
                return pollNode.taskName;
            }

            //            一次找出一个任务进runQueue
            private void startTask(Deque<Character> runQueue, Node nodeHead, Map<Character, Integer> taskStop, int n) {
                Set<Character> taskFlag = new LinkedHashSet<>();
                int beforeLength = runQueue.size();
                Node p = nodeHead;
                while (p.next != null) {
                    p = p.next;
                    final Character tmpChar = p.taskName;
                    if (taskFlag.contains(tmpChar) || tmpChar.equals(runQueue.getLast())) {
    //                    如果已经判断过该任务 || 判断是否和上个任务是同个任务  就跳过
                        continue;
                    } else {
    //                    不是上个任务，且没有判断过，判断冷却
                        final Integer cd = taskStop.get(tmpChar);
                        if (cd == null || cd >= n) {
    //                        没有冷却||冷却好了，可以入队
                            runQueue.add(tmpChar);
    //                +任务时，处理冷却问题（当前任务置为0，其余所有任务冷却+1），其余时刻不处理
                            taskStop.keySet().stream().forEach(key -> {
                                taskStop.put(key, taskStop.get(key) + 1);
                            });
                            taskStop.put(tmpChar, 0);
    //                        找到任务入队了，将p出链，直接跳出循环
                            pollLink(p);
                            break;
                        } else {
    //                        冷却没好，继续循环,添加到已判断列表，省去多次判断
                            taskFlag.add(tmpChar);
                        }
                    }
                }
                if (beforeLength == runQueue.size()) {
    //                没有复合条件的task入队，直接增加等待任务入队，所有任务冷却刷新
                    runQueue.add('-');
                    taskStop.keySet().stream().forEach(key -> {
                        taskStop.put(key, taskStop.get(key) + 1);
                    });
                }
            }
        }
//leetcode submit region end(Prohibit modification and deletion)
}
