package edu.rlv.cosc60;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author russel
 */
public class TreeMap<K extends Comparable<K>,V> implements Map<K, V>{
    
    public static enum TreeOrder{INORDER,PREORDER,POSTORDER};
    
    protected class BSTNode{
        K key;
        V value;
        int size;
        BSTNode left;
        BSTNode right;
        BSTNode parent;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.size = 1;
        }

        public void setLeft(BSTNode left) {
            this.left = left;
        }

        public void setRight(BSTNode right) {
            this.right = right;
        }

        public void setParent(BSTNode parent) {
            this.parent = parent;
        }

        public BSTNode getLeft() {
            return left;
        }

        public BSTNode getRight() {
            return right;
        }

        public BSTNode getParent() {
            return parent;
        }
        
        
        
    }
    
    private class InOrderIterator implements Iterator<BSTNode>{
        int count = size();
        BSTNode cur = null;
        Stack<BSTNode> stack;

        public InOrderIterator() {
            stack = new ArrayStack<BSTNode>();
            addToStack(root);
        }
        
        
        
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public BSTNode next() {
            if(hasNext()){
                cur = stack.pop();
                addToStack(cur.right);
                return cur;
            }
            
            throw new NoSuchElementException();
        }
        
        private void addToStack(BSTNode r){
            if (r != null) {
                stack.push(r);
                if(r.getLeft() != null){
                    addToStack(r.getLeft());
                }
            }
        }
        
        
    }
    
    BSTNode root = null;
    
    @Override
    public V put(K key, V value) {
        BSTNode cur = search(root, key);
        V oldValue = null;
        
        if(cur == null){
            root = add(root, key, value);
        }else{
            oldValue = cur.value;
            cur.value = value;
        }
        return oldValue;
    }

    @Override
    public boolean remove(K key) {
        int size = size(root);
//        
//        System.out.print(key);
//        System.out.print(": ");
//        System.out.print(size);
//        System.out.print(" -> ");
//        
        root = delete(root, key);
        
//        System.out.println(size());
        
        return size > size(root);
    }

    @Override
    public boolean contains(K key) {
        return search(root, key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode cur = search(root,key);
        
        if(cur != null){
            return cur.value;
        }
        
        return null;
    }

    @Override
    public Iterable<K> getKeys() {
        return new Iterable<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    InOrderIterator itr = new InOrderIterator();
                    
                    @Override
                    public boolean hasNext() {
                        return itr.hasNext();
                    }

                    @Override
                    public K next() {
                        return itr.next().key;
                    }
                };
                
            };
            
        };
    }

    @Override
    public Iterable<V> getValues() {
        return new Iterable<V>() {
            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    InOrderIterator itr = new InOrderIterator();
                    
                    @Override
                    public boolean hasNext() {
                        return itr.hasNext();
                    }

                    @Override
                    public V next() {
                        return itr.next().value;
                    }
                };
                
            };
            
        };    
    
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return "{" + inOrderVisit(root) + "}";
    }
    
    public String toString(TreeOrder order){
        switch(order){
            case INORDER: return "{" + inOrderVisit(root) + "}";
            case PREORDER: return "{" + preOrderVisit(root) + "}";
            case POSTORDER: return "{" + postOrderVisit(root) + "}";
            default: return "";
        }
    }
    
    
    
    public K minKey(){
        BSTNode m = min(root);
        return m != null? m.key:null;
    }
    
    public K maxKey(){
        BSTNode m = max(root);
        return m != null? m.key:null;
    }
    
    protected BSTNode add(BSTNode r, K k, V v){
        if(r==null){
            return new BSTNode(k,v);
        }
        
        int result  = r.key.compareTo(k);
        
        if(result >= 0){
            r.setLeft(add(r.getLeft(),k,v));
            r.getLeft().setParent(r);
        }else if(result < 0){
            r.setRight(add(r.getRight(),k,v));
            r.getRight().setParent(r);
        }
        
        setSize(r);
        return r;
    }
    
    protected BSTNode delete(BSTNode r,K k){
        if(r==null){
            return null;
        }
        
        int result  = r.key.compareTo(k);

        if(result == 0) {
            if(r.getLeft() == null){
                BSTNode right = r.getRight();
                r.setParent(null);
                r.setRight(null);
                return right;
            }else if(r.getRight() == null){
                BSTNode left = r.getLeft();
                r.setParent(null);
                r.setLeft(null);
                return left;
            }else{
                BSTNode pred = max(r.getLeft());
                r.key = pred.key;
                r.value = pred.value;
                r.setLeft(delete(r.getLeft(),pred.key));
                if (r.getLeft() != null) {
                    r.getLeft().setParent(r);
                }
            }
        }else if(result > 0){
            r.setLeft(delete(r.getLeft(),k));
            if(r.getLeft()!=null){
                r.getLeft().setParent(r);
            }
        }else {
            r.setRight(delete(r.getRight(),k));
            if(r.getRight()!=null){
                r.getRight().setParent(r);
            }
        }
        
        setSize(r);
        return r;
    }
    
    protected BSTNode search(BSTNode r, K k){
        if(r==null){
            return null;
        }
        
        int result  = r.key.compareTo(k);

        if(result == 0) {
            return r;
        }else if(result > 0){
            return search(r.getLeft(),k);
        }else {
            return search(r.getRight(),k);
        }
        
    }
    
    protected BSTNode min(BSTNode r){
        if(r != null && r.getLeft()!=null){
            return min(r.getLeft());
        }
        return r;
    }
    
    protected BSTNode max(BSTNode r){
        if(r != null && r.getRight()!=null){
            return max(r.getRight());
        }
        return r;
    }
    
    protected int size(BSTNode r){
        if(r==null){
            return 0;
        }
        return r.size;
    }
    
    protected void setSize(BSTNode r){
        r.size = size(r.getLeft()) + size(r.getRight()) + 1;
    }
    
    protected String inOrderVisit(BSTNode r){
        if(r==null){
            return "";
        }
        
        String left = inOrderVisit(r.left);
        String right = inOrderVisit(r.right);
        StringBuilder tmp = new StringBuilder("");
        
        if(left.length() > 0){
            tmp.append(left).append(", ");
        }
        
        tmp.append(r.key).append(": ").append(r.value);
        
        if(right.length() > 0){
            tmp.append(", ").append(right);
        }
        
        return tmp.append("").toString();
    }
    
    protected String preOrderVisit(BSTNode r){
        if(r==null){
            return "";
        }
        
        String left = preOrderVisit(r.left);
        String right = preOrderVisit(r.right);
        StringBuilder tmp = new StringBuilder("");
        
        tmp.append(r.key).append(": ").append(r.value);
        
        if(left.length() > 0){
            tmp.append(", ").append(left);
        }
        
        
        if(right.length() > 0){
            tmp.append(", ").append(right);
        }
        
        return tmp.append("").toString();
    }
    
    protected String postOrderVisit(BSTNode r){
        if(r==null){
            return "";
        }
        
        String left = postOrderVisit(r.left);
        String right = postOrderVisit(r.right);
        StringBuilder tmp = new StringBuilder("");
                
        if(left.length() > 0){
            tmp.append(left).append(", ");
        }
        
        
        if(right.length() > 0){
            tmp.append(right).append(", ");
        }
        
        tmp.append(r.key).append(": ").append(r.value);

        
        return tmp.append("").toString();
    }
    
}
