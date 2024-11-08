Import java.util.ArrayList; 

public class Maximos<T> {
    private int maximo;
    private ArrayList<T> res;

    public Maximos(){
        this.maximo = 0;
        this.res = new ArrayList<T>[0]
    }

    public void agregar(T id, int cant){
        if (this.sec.lenght == 0){
            this.maximo = cant;
            res.append(id);
        }else{
            if (cant > this.maximo){
                this.res = new ArrayList<T>[0];
                this.res.append(id);
                this.maximo = cant;
            }else if(cant == this.maximo){
                this.res.append(id);
            } else {
                return;
            }
        }
    }

    public ArrayList<T> devolver(){
        return this.res;
    }
}
