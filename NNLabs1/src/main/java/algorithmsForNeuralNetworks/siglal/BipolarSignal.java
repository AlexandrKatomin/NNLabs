package algorithmsForNeuralNetworks.siglal;

/**
 * Created by Alex on 24.09.2017.
 */
public class BipolarSignal extends Signal {


    public int  ruleOfHebb(int x, int y){
       return x*y;
    }

    public int activationFunction(int s) {
        if(s>0){
            return 1;
        }else{
            return -1;
        }
    }

    public BipolarSignal(){
        numberForClassA=1;
        numberForClassB=-1;
    }


}
