/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dgtc1_000
 */
import java.lang.*;

public class DLBtrie<T> {
    private DLBNode firstNode;
    private char terminator = '%';
    
    
    public DLBtrie(String newWord){
        insert(newWord);
    }
    
    public DLBtrie(){
        firstNode = null;
    }
    
    public void insert(String newWord){
        if(firstNode == null){
            firstNode = new DLBNode(null, null, newWord.charAt(0));
            DLBInsert(newWord, 0, firstNode);
        }
        else{
            DLBInsert(newWord, 0, firstNode);
        }
    }
    
    private void DLBInsert(String newWord, int stringPos, DLBNode currentNode){
        if(stringPos < newWord.length()){
            
            while(currentNode.next != null){
                //System.out.println(currentNode.data);
                if(Character.toLowerCase(currentNode.data) == Character.toLowerCase(newWord.charAt(stringPos))){
                    if(currentNode.child == null && stringPos+1 < newWord.length()){
                        currentNode.child = new DLBNode(null, null, newWord.charAt(stringPos+1));
                        //System.out.println(currentNode.data);
                        DLBInsert(newWord, stringPos+1, currentNode.child);
                        break;
                    }
                    else if(currentNode.child == null){
                        //System.out.println(currentNode.data);
                        currentNode.child = new DLBNode(null, null, terminator);
                        break;
                    }
                    else if(currentNode.child != null){
                        //System.out.println(currentNode.data);
                        DLBInsert(newWord, stringPos+1, currentNode.child);
                        break;
                    }
                }
                currentNode = currentNode.next;
            }
            
            if(currentNode.next == null && Character.toLowerCase(currentNode.data) != Character.toLowerCase(newWord.charAt(stringPos))){
                currentNode.next = new DLBNode(null, null, newWord.charAt(stringPos));
                //System.out.println(currentNode.data);
                DLBInsert(newWord, stringPos, currentNode.next);
            }
            
            if(currentNode.next == null && Character.toLowerCase(currentNode.data) == Character.toLowerCase(newWord.charAt(stringPos))){
                if(currentNode.child == null && stringPos+1<newWord.length()){
                    currentNode.child = new DLBNode(null, null, newWord.charAt(stringPos+1));
                    //System.out.println(currentNode.data);
                    DLBInsert(newWord, stringPos+1, currentNode.child);
                }
                else if(currentNode.child == null){
                    currentNode.child = new DLBNode(null, null, terminator);
                    //System.out.println(currentNode.data);
                }
                else if(currentNode.child != null){
                    //System.out.println(currentNode.data);
                    DLBInsert(newWord, stringPos +1, currentNode.child);
                }
            }
        }
        else{
            if(currentNode.child == null){
                //System.out.println("null child of "+currentNode.data);
                currentNode.child = new DLBNode(null, null, terminator);
            }
            else if(currentNode.child != null /*&& currentNode.child.data != terminator*/ && currentNode.next != null){
                //System.out.println("no null child, no null sib: "+currentNode.data);
                DLBInsert(newWord, stringPos, currentNode.next);
                //create a new terminator node, set the current child as the sibling of the new node, then set the new node as the child of the current node
                //DLBNode newNode = new DLBNode(null, null, terminator);
                //newNode.next = currentNode.child;
                //currentNode.child = newNode;
            }   
            else if(currentNode.child != null /*&& currentNode.child.data != terminator*/ && currentNode.next == null){ 
                //System.out.println("No null child, yes null sib "+currentNode.data);
                currentNode.next = new DLBNode(null, null, terminator);
            }
        }
    }
    
    public int findString(String word){
        return(DLBFindString(word, 0, firstNode));
    }
    
    private int DLBFindString(String word, int stringPos, DLBNode currentNode){
        int current;
        
        
        if(stringPos < word.length()){
            
            while(currentNode.next !=null){
                if(Character.toLowerCase(currentNode.data) == Character.toLowerCase(word.charAt(stringPos))){
                    //System.out.println(currentNode.data);
                    current = DLBFindString(word, stringPos+1, currentNode.child);
                    if (current == 0){
                        return 0;
                    }
                    else if(current == 1){
                                //System.out.println(currentNode.data);
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                currentNode = currentNode.next;
            }
            
            if(currentNode.next == null && Character.toLowerCase(currentNode.data) == Character.toLowerCase(word.charAt(stringPos))){
                //System.out.println(currentNode.data);
                current = DLBFindString(word, stringPos+1, currentNode.child);
                if(current == 0){
                    return 0;
                }
                else if(current == 1){
                            //System.out.println(currentNode.data);
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else{
                return -1;
            }
        }
        else{
            if(currentNode.data == terminator){
                        //System.out.println(currentNode.data);
                return 1;
            }
            else{
                if(currentNode.next == null){
                    return 0;
                }
                else{
                    //System.out.println(currentNode.data);
                    current = DLBFindString(word, stringPos, currentNode.next);
                    
                    if(current == 0){
                    return 0;
                    }
                    else if(current == 1){
                                //System.out.println(currentNode.data);
                        return 1;
                    }
                    else{
                        return -1;
                    }
            
                }
            }
        }
    }
    
   
    
//    public int delete(String word){
 //       return (DLBDelete(word, 0, firstNode));
//    }
    
//    private int DLBDelete(String word, int stringPos, DLBNode currentNode){
//        
//    }
    
    private class DLBNode<T> {
        private char data;
        private DLBNode next;
        private DLBNode child;
        
        private DLBNode(DLBNode nextNode, DLBNode childNode, char nodeData){
            data = nodeData;
            next = nextNode;
            child = childNode;
        }
    }
}
