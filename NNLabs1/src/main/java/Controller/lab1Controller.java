package Controller;

import algorithmsForNeuralNetworks.siglal.BinarySignal;
import algorithmsForNeuralNetworks.siglal.BipolarSignal;
import algorithmsForNeuralNetworks.siglal.Signal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.09.2017.
 */
public class lab1Controller
{
    @FXML Pane paneWithBtm;

    @FXML Slider sliderLength;
    @FXML Slider sliderWidth;
    @FXML Slider sliderCountItem;

    @FXML private Label labelLength;
    @FXML private Label labelWidth;
    @FXML private Label lableCount;

    @FXML private VBox vboxClassA;
    @FXML private VBox vboxClassB;

    @FXML private  CheckBox checkBox1;
    @FXML private  CheckBox checkBox2;

    @FXML private  RadioButton radioBtn1;
    @FXML private  RadioButton radioBtn2;
    ToggleGroup groupForRadioBatton = new ToggleGroup();

    Signal signal= new BipolarSignal();

    List<Integer> imageinPane= new ArrayList<Integer>();
    List<ArrayList<Integer>> listImagesClassA= new ArrayList<ArrayList<Integer>>();
    List<ArrayList<Integer>> listImagesClassB=new ArrayList<ArrayList<Integer>>();

    List<Integer> imageC= new ArrayList<Integer>();

    private ImageView fields[][] = null;

    int countBtmInHeight=4;
    int countBtmInWidth=4;
    int countImageInClass=1;
    int lengthBtm=30;

    URL imgUrl=getClass().getProtectionDomain().getClassLoader().getResource("img1.png");
    URL imgUrl2=getClass().getProtectionDomain().getClassLoader().getResource("img2.jpg");
    Image image = new Image(imgUrl.toString());
    Image image2 = new Image(imgUrl2.toString());



    void resizeImageInPane(){
        signal=(Signal) groupForRadioBatton.getSelectedToggle().getUserData();
        imageinPane= new ArrayList<Integer>();
        for(int i=0;i<countBtmInHeight*countBtmInWidth;i++){
            imageinPane.add(signal.getNumberForClassB());
        }
    }
    @FXML
    void onSelectRadioBtn(){
        Signal newSignal=(Signal)groupForRadioBatton.getSelectedToggle().getUserData();
        Signal aldSignal;
        if(newSignal instanceof BipolarSignal){
            aldSignal= new BinarySignal();
        }else{
            aldSignal= new BipolarSignal();
        }
        for(int i=0;i<countBtmInHeight*countBtmInWidth;i++){
            if(imageinPane.get(i) == aldSignal.getNumberForClassB()){
                imageinPane.set(i,newSignal.getNumberForClassB());
            }
        }
    }



    HBox createNewHBox(int n, char classImg){
        HBox hBox= new HBox();
        Button btn1= new Button();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(10,0, 0,0) );

        btn1.setText("Запомнить "+classImg+n);
        btn1.setOnAction(new NumberButtonHandler(btn1));
        Button btn2= new Button();
        btn2.setText(""+classImg+n);
        hBox.getChildren().addAll(btn1,btn2);

        return hBox;
    }

    void addButtons(VBox vBox, int n,char classImg){
        vBox.getChildren().clear();
        for(int i=0;i<n;i++){
            HBox newHBox=createNewHBox(i+1,classImg);
            vBox.getChildren().add(newHBox);
        }

    }


    @FXML public void action(ActionEvent event) {

    }
    class NumberButtonHandler implements EventHandler<ActionEvent> {
        Button btn;
        char classImg;
        int number;
        NumberButtonHandler(Button btn) {
            this.btn =btn;
        }
        @Override
        public void handle(ActionEvent event) {
            classImg=btn.getText().charAt(btn.getText().length()-2);
            number=Integer.parseInt(String.valueOf(btn.getText().charAt(btn.getText().length()-1)))-1;
            System.out.println(classImg);
            System.out.println(number);
        }
    }

    class ImageViewHandler implements EventHandler<MouseEvent> {
        ImageView imageView;
        ImageViewHandler(ImageView imageView) {
            this.imageView=imageView;
        }
        @Override
        public void handle(MouseEvent event) {
            if(imageView.getImage() == image){
                imageView.setImage(image2);
            }else{
                imageView.setImage(image);
            }
        }


    }

    String createIdForBtm (int i,int j){
        return "field[" + i + ","  + j + "]";
    }
    void createFields(){
        resizeImageInPane();
        paneWithBtm.setPrefWidth(25*2 + lengthBtm*countBtmInWidth);
        paneWithBtm.setPrefHeight(25*2 + lengthBtm* countBtmInHeight);
        paneWithBtm.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fields=new ImageView[countBtmInWidth][countBtmInHeight];

        for(int i=0; i<countBtmInWidth;i++){
            for(int j=0;j<countBtmInHeight;j++){
                fields[i][j]= new ImageView(image);
                fields[i][j].setLayoutX(25 + 30 * i);
                fields[i][j].setLayoutY(25 + 30 * j);
                fields[i][j].setFitHeight(30);
                fields[i][j].setFitWidth(30);
                fields[i][j].setOnMouseClicked(new ImageViewHandler(fields[i][j]));
                paneWithBtm.getChildren().add(fields[i][j]);
            }
        }
    }


    void setMarksAndLabels(Slider slider){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1f);
        //slider.setBlockIncrement(10f);
    }

    int foo( Number newValue, Label label){
        label.setText("" + Math.round(newValue.doubleValue()));
        paneWithBtm.getChildren().clear();
        createFields();
        return (int)Math.round(newValue.doubleValue());
    }

    @FXML
    public void initialize(){

        radioBtn1.setUserData(new BipolarSignal());
        radioBtn1.setToggleGroup(groupForRadioBatton);
        radioBtn2.setUserData(new BinarySignal());
        radioBtn2.setToggleGroup(groupForRadioBatton);
        radioBtn1.setSelected(true);
        System.out.println(groupForRadioBatton.getSelectedToggle().getUserData());
        createFields();
        addButtons(vboxClassA,countImageInClass,'A');
        addButtons(vboxClassB,countImageInClass,'B');
        sliderLength.setValue(4);
        sliderWidth.setValue(4);
        sliderCountItem.setValue(1);
        setMarksAndLabels(sliderWidth);
        setMarksAndLabels(sliderLength);
        setMarksAndLabels(sliderCountItem);

        labelLength.setText("4");
        labelWidth.setText("4");
        lableCount.setText("1");

        sliderLength.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {
                countBtmInHeight=foo(newValue,labelLength);
            }
        });

        sliderWidth.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {
                countBtmInWidth=foo(newValue,labelWidth);
            }
        });

        sliderCountItem.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {
                lableCount.setText("" + Math.round(newValue.doubleValue()));
                countImageInClass=(int)Math.round(newValue.doubleValue());
                addButtons(vboxClassA,countImageInClass,'A');
                addButtons(vboxClassB,countImageInClass,'B');
            }
        });
       /* private void createFields() {
            gamePane.setPrefWidth(34 * 2 + 24 * Model.getIntance().getGame().getGameSize());
            gamePane.setPrefHeight(25 * 2 + 20 + 25 * Model.getIntance().getGame().getGameSize());
            if(Model.getIntance().getGame().getGameSize() > 0)
                field = new Button[Model.getIntance().getGame().getGameSize()][Model.getIntance().getGame().getGameSize()];
            for(int x = 0; x < Model.getIntance().getGame().getGameSize(); x++) {
                for(int y = 0; y < Model.getIntance().getGame().getGameSize(); y++) {
                    field[x][y] = new Button();
                    field[x][y].setLayoutX(34 + 24 * x);
                    field[x][y].setLayoutY(25 + 25 * y);
                    field[x][y].setPrefWidth(24);
                    field[x][y].setPrefHeight(25);
                    field[x][y].setMnemonicParsing(false);
                    field[x][y].setId("field[" + x + "," + y + "]");
                    field[x][y].setOnAction(this::action);
                    gamePane.getChildren().add(field[x][y]);
                }
            }
            updateFields();
        }
*/
    }




}
