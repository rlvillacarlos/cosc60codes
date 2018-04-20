package edu.rlv.cosc60;

/**
 *
 * @author russel
 */
public class AVLMap<K extends Comparable<K>,V> extends TreeMap<K, V>{
    
    protected class AVLNode extends BSTNode{
        int height;
        AVLNode left;
        AVLNode right;
        
        public AVLNode(K key, V value) {
            super(key,value);
            this.height = 0;
        }

        @Override
        public AVLNode getLeft() {
            return left;
        }
        
        @Override
        public AVLNode getRight() {
            return right;
        }

        @Override
        public void setLeft(BSTNode left) {
            super.setLeft(left);
            this.left = (AVLNode)left;
        }

        @Override
        public void setRight(BSTNode right) {
            super.setRight(right); 
            this.right = (AVLNode)right;
        }
    }
    
    @Override
    protected BSTNode add(BSTNode r, K k, V v){
        if(r==null){
            return new AVLNode(k,v);
        }
        
        int result = r.key.compareTo(k);

        if (result > 0) {
            r.setLeft(add(r.getLeft(), k, v));
            r.getLeft().setParent(r);
        } else if (result < 0) {
            r.setRight(add(r.getRight(), k, v));
            r.getRight().setParent(r);
        }

        setSize(r);
        return makeAVL((AVLNode)r);
    }
    
    @Override
    protected BSTNode delete(BSTNode r,K k){
        if (r == null) {
            return null;
        }

        int result = r.key.compareTo(k);

        if (result == 0) {
            if (r.getLeft() == null) {
                BSTNode right = r.getRight();
                r.setParent(null);
                r.setRight(null);
                return right;
            } else if (r.getRight() == null) {
                BSTNode left = r.getLeft();
                r.setParent(null);
                r.setLeft(null);
                return left;
            } else {
                BSTNode pred = max(r.getLeft());
                r.key = pred.key;
                r.value = pred.value;
                r.setLeft(delete(r.getLeft(), pred.key));
                if (r.getLeft() != null) {
                    r.getLeft().setParent(r);
                }
            }
        } else if (result > 0) {
            r.setLeft(delete(r.getLeft(), k));
            if (r.getLeft() != null) {
                r.getLeft().setParent(r);
            }
        } else {
            r.setRight(delete(r.getRight(), k));
            if (r.getRight() != null) {
                r.getRight().setParent(r);
            }
        }

        setSize(r);
        return makeAVL((AVLNode)r);
    }
    
    private AVLNode makeAVL(AVLNode r){    
        if(r==null){
            return null;
        }
        
        if(height(r.getRight()) - height(r.getLeft()) > 1){
            if(height(r.getRight().getRight()) < height(r.getRight().getLeft())){
                
                AVLNode right = (AVLNode)rightRotate(r.getRight());
//                AVLNode rightRight = ((AVLNode)right.getRight());
                
                r.setRight(right);
                right.setParent(r);                
                setSize(r);
                setHeight(r);
                
            }
            
            AVLNode newR = (AVLNode)leftRotate(r);
            
//            setSize(r);
//            setSize(newR);
//            
//            setHeight(r);
//            setHeight(newR);
            
            return newR;
            
        }else if (height(r.getLeft()) - height((r.getRight()))>1){
            if(height(r.getLeft().getLeft()) < height(r.getLeft().getRight())){

                AVLNode left = (AVLNode)rightRotate(r.getLeft());
//                AVLNode leftLeft = ((AVLNode)left.getLeft());
                
                r.setLeft(left);
                left.setParent(r);
                setSize(r);
                setHeight(r);
            }
            
            AVLNode newR = (AVLNode)rightRotate(r);
            
//            setSize(r);
//            setSize(newR);
//            
//            setHeight(r);
//            setHeight(newR);
            
            return newR;
        }
        return r;
    }
    
    protected AVLNode leftRotate(AVLNode r){
        if(r.getRight()==null){
            return r;
        }
        
        AVLNode right = r.getRight();
        AVLNode rightLeft = r.getRight().getLeft();
        
        r.setRight(rightLeft);
        if(rightLeft!=null){
            rightLeft.setParent(r);
        }
        r.setParent(right);
        right.setLeft(r);
        
        
        setSize(r);
        setSize(right);
        
        setHeight(r);
        setHeight(right);
        
        return right;
    }
    
    protected AVLNode rightRotate(AVLNode r){
        if(r.getLeft()==null){
            return r;
        }
        
        AVLNode left = r.getLeft();
        AVLNode leftRight = r.getLeft().getRight();

        r.setLeft(leftRight);
        
        if(leftRight!=null){
            leftRight.setParent(r); 
        }
        
        r.setParent(left);
        left.setRight(r);
        
        setSize(r);
        setSize(left);
        
        setHeight(r);
        setHeight(left);
        
        return left;
    }
    
    private int height(AVLNode r){
        return r!=null? r.height : -1;
    }
    
    private void setHeight(AVLNode r){
        if(r!=null){
            r.height =  Math.max(height(r.getLeft()),height(r.getRight())) + 1;
        }
    }
    
}
