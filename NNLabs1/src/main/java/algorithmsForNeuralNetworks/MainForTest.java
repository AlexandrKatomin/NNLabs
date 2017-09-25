package algorithmsForNeuralNetworks;

import algorithmsForNeuralNetworks.siglal.BipolarSignal;
import algorithmsForNeuralNetworks.siglal.Signal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.09.2017.
 */
public class MainForTest {

    static int countItem=9;

    static List<ArrayList<Integer>> arrayA= new ArrayList<ArrayList<Integer>>();
    static List<ArrayList<Integer>> arrayB= new ArrayList<ArrayList<Integer>>();




    public static void main(String[] args) {
        int[] imjA = {1,-1,1,1,1,1,-1,-1,1};
        int[] imjB = {1,1,1,1,-1,1,1,-1,1 };

        int[] imjA2 = {1,0,1,1,1,1,0,0,1};
        int[] imjB2 = {1,1,1,1,0,1,1,0,1 };

        ArrayList<Integer> listA = new ArrayList<Integer>();
        ArrayList<Integer> listB = new ArrayList<Integer>();
        for(int i=0;i<imjA.length;i++){
            listA.add(imjA[i]);
            listB.add(imjB[i]);
        }

        arrayA.add(listA);
        arrayB.add(listB);

        Signal signal= new BipolarSignal();
        Neuron neuron = new Neuron(signal,countItem,1);
        neuron.algHebb( arrayA,arrayB);







    }
}
