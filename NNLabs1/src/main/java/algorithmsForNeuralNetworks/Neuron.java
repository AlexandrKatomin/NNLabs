package algorithmsForNeuralNetworks;

import algorithmsForNeuralNetworks.siglal.Signal;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 24.09.2017.
 */
public class Neuron {
    @Setter @Getter List<ArrayList<Integer>> arrayOfImagesByClassA;
    @Setter @Getter List<ArrayList<Integer>> arrayOfImagesByClassB;

    Signal signal;

    int countImageInClass;
    int countItemInImage;

    @Getter
    List<Integer> neuron;

    public void setCountItemInImage(int newCountItemInImage){
        countItemInImage=newCountItemInImage;
        if(neuron.size()> newCountItemInImage){
            while(neuron.size()>newCountItemInImage){
                neuron.remove(neuron.size()-1);
            }
        }
        if(neuron.size()<newCountItemInImage){
            while (neuron.size()<newCountItemInImage){
                neuron.add(0);
            }
        }
    }

    public Neuron(Signal signal, int countItemInImage, int countImageInClass){
        this.signal=signal;
        this.countImageInClass=countImageInClass;
        this.countItemInImage=countItemInImage;
        neuron= new ArrayList<Integer>();
        for(int i=0;i<countItemInImage+1;i++){
            neuron.add(0);
        }

}
    void printNeuron(List<Integer> array){
        System.out.print(array.get(0)+" ");
        int n=(int)Math.sqrt(countItemInImage);
        int index;
        for(int i=1;i<n+1;i++){
            if(i != 1){
                System.out.print("  ");
            }
            for(int j=1;j<n+1;j++){
                index=i*n+j-n;//
                System.out.print(array.get(index));
            }
            System.out.println();
        }
    }

    void printArray(List<Integer> arr){
        for(int i=0;i<arr.size();i++){
            System.out.print(arr.get(i));
        }
        System.out.println();
    }

    void zeroingNeuron(){
        for(int i=0;i<countItemInImage+1;i++){

            neuron.set(i,0);
        }
    }
    void cheakOnCountItem(List<Integer> array){
        try {
            if(array.size() != countItemInImage){
                throw new Exception("Count item in image shoid be equals " + countItemInImage);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    void cheakOnCountImageInClass(List<ArrayList<Integer>> arrayOfImagesByClass){
        try {
            if (arrayOfImagesByClass.size() != countImageInClass) {
                throw new Exception("Count image in class shoid be equals " + countImageInClass);
            }
        }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }

    void trainNeuronForOneImage(List<Integer> image,int numberOfClass){
        cheakOnCountItem(image);
        int tmp=neuron.get(0)+signal.ruleOfHebb(1,numberOfClass);
        neuron.set(0,tmp);
        for(int j=0;j<countItemInImage;j++){
            tmp=neuron.get(j+1)+signal.ruleOfHebb(image.get(j),numberOfClass);
            neuron.set(j+1,tmp);
        }
    }
    void trainNeuronForOneClass(List<ArrayList<Integer>> arrayOfImagesByClass, int numberForClass){
        cheakOnCountImageInClass(arrayOfImagesByClass);
        for (int i = 0; i < countImageInClass; i++) {
            trainNeuronForOneImage(arrayOfImagesByClass.get(i),numberForClass);
        }
    }

    public List<Integer> trainNeuron(){
        trainNeuronForOneClass(arrayOfImagesByClassA,signal.getNumberForClassA());
        trainNeuronForOneClass(arrayOfImagesByClassB,signal.getNumberForClassB());
        return neuron;
    }

    int getClass(int s){
        return signal.activationFunction(s);
    }

    int getClassOfImage(List<Integer> image){
        cheakOnCountItem(image);
        int summ=neuron.get(0);
        for(int i=0;i<countItemInImage;i++){
            summ+=neuron.get(i+1)*image.get(i);
        }
        System.out.println(summ);
        return getClass(summ);
    }

    boolean checkOfImageArray(List<ArrayList<Integer>> arrayOfImages,int numberForClass){
        for(int i=0;i<countImageInClass;i++){
            int result=getClassOfImage(arrayOfImages.get(i));
            if(numberForClass !=result  )  return false;
        }
        return true;
    }

    public void algHebb(List<ArrayList<Integer>> arrayOfImagesByClassA,List<ArrayList<Integer>> arrayOfImagesByClassB){
        printArray(arrayOfImagesByClassA.get(0));
        printArray(arrayOfImagesByClassB.get(0));
        this.arrayOfImagesByClassA=arrayOfImagesByClassA;
        this.arrayOfImagesByClassB=arrayOfImagesByClassB;
        boolean flag1=false;
        boolean flag2=false;

        zeroingNeuron();
        while(flag1 == false || flag2==false ) {

            //Step 2
            trainNeuron();
            printNeuron(neuron);

            //Step 3
            flag1 = checkOfImageArray(arrayOfImagesByClassA,signal.getNumberForClassA());
            flag2 = checkOfImageArray(arrayOfImagesByClassB,signal.getNumberForClassB());

            System.out.println("Check classes images array A: " + flag1);
            System.out.println("Check classes images array B: " + flag2);
        }
    }
}
