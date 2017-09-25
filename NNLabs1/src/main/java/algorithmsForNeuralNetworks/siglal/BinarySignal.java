package algorithmsForNeuralNetworks.siglal;

/**
 * Created by Alex on 24.09.2017.
 */
public class BinarySignal extends Signal {


    public int ruleOfHebb(int x, int y){
        if(x == 1 && y == 1){ return 1; }
        if(x == 0){ return 0; }
            return -1;
    }

    public int activationFunction(int s) {
        if(s>0){
            return 1;
        }else{
            return 0;
        }
    }

    public BinarySignal(){
        numberForClassA=1;
        numberForClassB=0;
    }



}
