package algorithmsForNeuralNetworks;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Alex on 21.09.2017.
 */


public class TrainingNeuron {
    @Setter @Getter List<int[]> arrayOfImagesByClassA;
    @Setter @Getter List<int[]> arrayOfImagesByClassB;
    int numberForClassA;
    int numberForClassB;

    int countImageInClass;
    int countItemInImage;

    TypeOfSignal typeOfSignal;


    @Getter int[] neuron;

    void zeroingNeuron(){
        for(int i=0;i<countItemInImage+1;i++){
            neuron[i]=0;
        }
    }
    void printNeuron(int[] array){
        System.out.print(array[0]+" ");
        int n=(int)Math.sqrt(countItemInImage);
        int index;
        for(int i=1;i<n+1;i++){
            if(i != 1){
                System.out.print("  ");
            }
            for(int j=1;j<n+1;j++){
                index=i*n+j-n;//
                    System.out.print(array[index]);
            }
            System.out.println();
        }
    }

    void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public TrainingNeuron(int countItemInImage, int countImageInClass, TypeOfSignal typeOfSignal){
        this.countImageInClass=countImageInClass;
        this.countItemInImage=countItemInImage;
        neuron= new int[countItemInImage+1];
        this.typeOfSignal=typeOfSignal;
        if(typeOfSignal == TypeOfSignal.binary){
            numberForClassA=1;
            numberForClassB=0;
        }else{
            numberForClassA=1;
            numberForClassB=-1;
        }


    }
    void cheakOnCountItem(int[] array){
        try {
            if(array.length != countItemInImage){
                throw new Exception("Count item in image shoid be equals " + countItemInImage);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
}

    int ruleOfHebb(int x, int y){
        if(typeOfSignal == TypeOfSignal.bipolar){
            return x*y;
        }
        else
        {
            if(x == 1 && y == 1){ return 1; }
            if(x == 0){ return 0; }
            return -1;
        }
    }
    void trainNeuronForOneImage(int[] image,int numberOfClass){
        cheakOnCountItem(image);
        neuron[0]+=ruleOfHebb(1,numberOfClass);
        for(int j=0;j<countItemInImage;j++){
            neuron[j+1]+=ruleOfHebb(image[j],numberOfClass);
        }
    }
    void trainNeuronForOneClass(List<int[]> arrayOfImagesByClass, int numberForClass){
        try {
            if (arrayOfImagesByClass.size() != countImageInClass) {
                throw new Exception("Count image in class shoid be equals " + countImageInClass);
            }
            for (int i = 0; i < countImageInClass; i++) {

            trainNeuronForOneImage(arrayOfImagesByClass.get(i),numberForClass);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }        
    }


    public int[] trainNeuron(){
        trainNeuronForOneClass(arrayOfImagesByClassA,numberForClassA);
        trainNeuronForOneClass(arrayOfImagesByClassB,numberForClassB);
        return neuron;
    }

    public int bipolarActivationFunction(int s){
        if(s>0){
            return 1;
        }else{
            return -1;
        }
    }
    public int binaryActivationFunction(int s){
        if(s>0){
            return 1;
        }else{
            return 0;
        }
    }

    int getClass(int s){
        if(typeOfSignal == TypeOfSignal.bipolar){
            return bipolarActivationFunction(s);
        }
        else { return binaryActivationFunction(s); }
    }

    int getClassOfImage(int[] image){
        cheakOnCountItem(image);
        int summ=neuron[0];
        for(int i=0;i<countItemInImage;i++){
            summ+=neuron[i+1]*image[i];
        }
        System.out.println(summ);
        return getClass(summ);
    }

    boolean checkOfImageArray(List<int[]> arrayOfImages,int numberForClass){
        for(int i=0;i<countImageInClass;i++){
            int result=getClassOfImage(arrayOfImages.get(i));

            if(numberForClass !=result  )
            {
                return false;
            }
        }
        return true;

    }

    public void algHebb(List<int[]> arrayOfImagesByClassA,List<int[]> arrayOfImagesByClassB){
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

            flag1 = checkOfImageArray(arrayOfImagesByClassA, numberForClassA);
            flag2 = checkOfImageArray(arrayOfImagesByClassB, numberForClassB);


            System.out.println("Check classes images array A: " + flag1);
            System.out.println("Check classes images array B: " + flag2);
        }


    }



















  
    
}
