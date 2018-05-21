/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.i.datos.ii;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author ofortich
 */
public class Nodo extends DefaultMutableTreeNode {
    Date fecha;
    Boolean paquete;//si es un paquete va a ser verdadero
    private String nombre;
    private String data;
    private Nodo rLink;
    private Nodo lLink;
    File archivoEntregable;

    public String getNombre() {
        return nombre;
    }

    
    
    public Nodo getrLink() {
        return rLink;
    }

    public Nodo getlLink() {
        return lLink;
    }

    public void setrLink(Nodo rLink) {
        this.rLink = rLink;
    }

    public void setlLink(Nodo lLink) {
        this.lLink = lLink;
    }
   
    
    public String getData() {
        return data;
    }

    public Nodo(String nombre, Boolean paquete, Date fecha) {
        this.nombre = nombre;
        this.paquete=paquete;
        this.data=data;
        this.fecha=fecha;
        if (!paquete) {
            //agregar archivo
        }
    }
    public Nodo(String nombre, Boolean paquete) {
        this.nombre = nombre;
        this.paquete=paquete;
        this.data=null;
    }
    
    /*
    void add(int i) {
        Nodo temp=this,ant=null;
        while(temp!=null){
            if(temp.data==i)break;
            else if(i>temp.data){
                ant=temp;
                temp=temp.Rlink;
            }else{
                ant=temp;
                temp=temp.Llink;
            }
        }
        if(temp==null){//no esta repetido
            if(i>ant.data) ant.rLink= new Nodo(i);
            else ant.Llink= new Nodo(i);
        }
    }
    */
    String getNombre(Nodo nodo){
        return this.nombre;
    }
    void add(Nodo nodo ){
        if (nodo.paquete) {
            this.ultPaquete().lLink=new Nodo(nodo.nombre, true);
        }else{
            this.ultEntregable().rLink=new Nodo(nodo.nombre, false, nodo.fecha);
        }
    }
    void add(String nombre, Boolean paquete){
        if (paquete) {
            this.ultPaquete().lLink=new Nodo(nombre, true);
        }else{
            this.ultEntregable().rLink=new Nodo(nombre, false);
        }
    }
    public void eliminar(String nombre,boolean paquete,Nodo pa){
        if (paquete) {
            if (buscarPaquete(nombre,pa).lLink!=null) {
                pa.buscarPaquete(buscarPaquete(nombre,pa).lLink.nombre, pa).lLink=buscarPaquete(nombre,pa).lLink;
            }else{
                pa.buscarPaquete(buscarPaquete(nombre,pa).lLink.nombre, pa).lLink=null;
            }
        }else{
            if (buscarEntregable(nombre,pa).rLink!=null) {
                pa.buscarEntregable(buscarEntregable(nombre,pa).rLink.nombre, pa).rLink=buscarPaquete(nombre,pa).rLink;
            }
        }
    }
     Nodo ultPaquete(){
        Nodo p=this;
        while(p.lLink != null){
            p=p.lLink;
        }
        return p;
    }
     
    boolean existe(String nombre, Nodo p){
        if (p==null) {
            return false;
        }else{
            if (p.nombre.equals(nombre)) {
                return true;
            }else{
               return existe(nombre, p.lLink)||existe(nombre, p.rLink);
            }
        }
    }
    Nodo buscarPaquete(String nombre, Nodo p) {
        if (p != null) {
            if (p.nombre.equals(nombre)) {
                return p;
            } else {
                return buscarPaquete(nombre, p.lLink);
            }
        }else{
            return p;
        }
    }
    /*
    String nodosTerminales(Nodo ptr, String nodos){
        if (ptr!=null) {
            nodosTerminales(ptr.lLink,nodos);
            nodosTerminales(ptr.rLink,nodos);
            if (ptr.lLink!=null&&ptr.rLink!=null) {
                return ptr.nombre+", "+nodos;
                
            }else{
                return ""+nodos;
            }
        }else{
            return ""+nodos;
        }
    }
*/
    String hojas(Nodo ptr){
        String nodos="";
        Nodo p=ptr.lLink;
        int c=0;
        while(p.lLink != null){
            nodos=nodos+p.ultEntregable().nombre;
            p=p.lLink;
            c++;
        }
        
        return nodos;
    }
    String nodosHoja(Nodo p){
        if (p==null) {
            return "";
        }else{
            if ((p.lLink == null) && (p.rLink == null)) {
                return p.nombre + " ";
            }else{
                return nodosHoja(p.rLink) + nodosHoja(p.lLink);
            }
        }
    }
    String unSoloEntregable(Nodo ptr){
        String nodos="";
        Nodo p=ptr;
        int c=0;
        p=ptr.lLink;
        while(p!=null){
            if (p.rLink!=null) {
                if (p.rLink.rLink==null) {
                    nodos=nodos+", "+p.nombre;
                }
            }
            p=p.lLink;
        }
        return nodos;
    }
    Nodo buscarEntregable(String nombre, Nodo p){
        if (p != null) {
            if (p.nombre.equals(nombre)) {
                return p;
            } else {
                return buscarEntregable(nombre, p.rLink);
            }
        }else{
            return p;
        }
    }
    
    int altura(Nodo p){
        int a=0;
        Nodo h=null;
        int c=0;
        int c2=0;
        if (p==null) {
            return 0;
        }else{
            if (p.lLink==null&&p.rLink==null) {
                return 1;
            }else{
                while(p.lLink!=null){
                    h=p;
                    c++;
                    p=p.lLink;
                    while(h.rLink!=null){
                        h=h.rLink;
                        c2++;
                    }
                    if (a<c2+c) {
                            a=c2+c;
                    }
                    c2=0;
                }
            }
        }
        return  a-1;   
        
    }
    public Nodo ultEntregable(){
        Nodo p=this;
        while(p.rLink != null){
            p=p.rLink;
        }
        return p;
    }
}
