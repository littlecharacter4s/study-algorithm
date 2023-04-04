package com.lc.structure.tree.trie;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * AC 自动机
 *
 * @author gujixian
 * @since 2022/12/28
 */
public class ACAutomaton {
    private final Node root = new Node();

    public void add(String words) {
        if (words == null) {
            return;
        }
        char[] cs = words.toCharArray();
        Node node = root;
        node.pass++;
        for (char c : cs) {
            node.nexts.putIfAbsent(c, new Node());
            node = node.nexts.get(c);
            node.pass++;
        }
        node.end = words;
    }

    public void delete(String word) {
        if (word == null) {
            return;
        }
        char[] cs = word.toCharArray();
        // 先查询一下是否存在
        Node node = root;
        for (char c : cs) {
            node = node.nexts.get(c);
            if (node == null) {
                return;
            }
        }
        if (node.end == null) {
            return;
        }
        // 再执行删除
        node = root;
        for (char c : cs) {
            if (--node.nexts.get(c).pass == 0) {
                node.nexts.remove(c);
                return;
            }
            node = node.nexts.get(c);
        }
        if (node != null) {
            node.end = null;
        }
    }

    public void build() {
        Queue<Node> queue = new LinkedList<>();
        // 先清除 fail 指针
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.fail = null;
            for (Node next : node.nexts.values()) {
                queue.offer(next);
            }
        }
        // 再重新设置 fail 指针
        root.fail = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.nexts.forEach((c, next) -> {
                next.fail = root;
                Node fail = node.fail;
                while (fail != null) {
                    if (fail.nexts.containsKey(c)) {
                        next.fail = fail.nexts.get(c);
                        break;
                    }
                    fail = fail.fail;
                }
                queue.offer(next);
            });
        }
    }

    public List<String> match(String content) {
        List<String> result = new LinkedList<>();
        if (content == null || "".equals(content)) {
            return result;
        }
        char[] cs = content.toCharArray();
        Node node = root;
        // 每个字符的匹配都会沿着 fail 走一圈
        for (int i = 0; i < cs.length; i++) {
            // 当前字符匹配成功 ? 跳出 while 执行下面匹配成功的逻辑 : 沿着 其父节点的 fail 走向下一条路径（另一个字符串）继续匹配，直到匹配成功或遇到 root
            while (!node.nexts.containsKey(cs[i]) && node != root) {
                node = node.fail;
            }
            node = node.nexts.getOrDefault(cs[i], root);
            Node follow = node;
            // 如果 node 来到了 root，其实在上个 while 已经沿着 fail 指针走完了，所以这里也就不会再进 while 循环了
            // 只有当前字符匹配成功，才会进入此循环
            while (follow != root) {
                if (follow.endMatched) {
                    break;
                }
                if (follow.end != null) {
                    result.add(follow.end);
                    follow.endMatched = true;
                }
                follow = follow.fail;
            }
        }
        return result;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        private int pass;
        private String end;
        private boolean endMatched = false;
        private Map<Character, Node> nexts = new HashMap<>();
        private Node fail;

    }

    public static void main(String[] args) {
        ACAutomaton acAutomaton = new ACAutomaton();
        acAutomaton.add("日本");
        acAutomaton.add("美国");
        acAutomaton.add("澳大利亚");
        acAutomaton.build();
        acAutomaton.delete("澳大利亚");
        acAutomaton.add("武汉");
        acAutomaton.build();
        String content = "中华人民共和国严禁与日本、美国、澳大利亚交往，他们不讲武德！";
        System.out.println(JSON.toJSONString(acAutomaton.match(content)));
    }
}
