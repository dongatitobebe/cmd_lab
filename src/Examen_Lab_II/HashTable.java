
package Examen_Lab_II;


public class HashTable {
    
    Entry lista;
  public void add(String username, long position){
         Entry entry=new Entry(username,position);
         if (search(username) != -1) {
             System.out.println("Usuario Existente");
            return; 
        }
        if (lista==null){
            lista=entry;
            return;
        }
        Entry tmp=lista;
        
            
            
            while(tmp.siguiente!=null){
                    tmp=tmp.siguiente;
                }
                tmp.siguiente=entry;
        
        
             
    }
     
     public void remove(String username) {
        if (lista == null) 
            return;
        if(lista.user.equals(username)) {
            lista = lista.siguiente;
            return;
        }
        Entry temp = lista;
        while (temp.siguiente != null) {
            if (temp.siguiente.user.equals(username)){
                temp.siguiente = temp.siguiente.siguiente;
                return;
            }
            temp = temp.siguiente;
        }
    }
    
    public long search(String username){
        Entry temp = lista;
        while (temp != null) {
            if (temp.user.equals(username)) {
                return temp.posicion;
            }
            temp = temp.siguiente;
        }
        return -1;
    }   
}
