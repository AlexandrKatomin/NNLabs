package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
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

/**
 * Created by Alex on 21.09.2017.
 */
public class lab1Controller
{
    @FXML Pane paneWithBtm;
    @FXML Button btmCreate;
    @FXML Button btmRemove;

    @FXML Slider sliderLength;
    @FXML Slider sliderWidth;
    @FXML Slider sliderCountItem;

    @FXML private Label labelLength;
    @FXML private Label labelWidth;
    @FXML private Label lableCount;

    @FXML private VBox vboxClassA;
    @FXML private VBox vboxClassB;

   // private Button fields[][] = null;
    private ImageView fields[][] = null;


    int countBtmInHeight=4;
    int countBtmInWidth=4;
    int lengthBtm=30;

    URL imgUrl=getClass().getProtectionDomain().getClassLoader().getResource("img1.png");
    URL imgUrl2=getClass().getProtectionDomain().getClassLoader().getResource("img2.jpg");
    Image image = new Image(imgUrl.toString());
    Image image2 = new Image(imgUrl2.toString());

    HBox createNewHBox(){
        HBox hBox= new HBox();
        Button btn1= new Button();
        Button btn2= new Button();
        hBox.getChildren().addAll(btn1,btn2);
        return hBox;
    }

    void addButtons(VBox vBox, int n){
        vBox.getChildren().clear();
        for(int i=0;i<n;i++){
            HBox newHBox=createNewHBox();
            vBox.getChildren().add(newHBox);
        }

    }

    @FXML
    void onRemovePane(){
        paneWithBtm.getChildren().clear();
    }
    @FXML
    void onCreatePane(){
      createFields();

        //paneWithBtm.getChildren().add(new Button());
    }
    @FXML public void action(ActionEvent event) {

    }
    class NumberButtonHandler implements EventHandler<ActionEvent> {
        Button btn;
        NumberButtonHandler(Button btn) {
            this.btn =btn;
            if (btn.getText() == "X") {
                btn.setText("");
            } else {
                btn.setText("X");
            }
        }
        @Override
        public void handle(ActionEvent event) {
            if (btn.getText() == "X") {
                btn.setText("");
            } else {
                btn.setText("X");
            }
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
        URL imgUrl=getClass().getProtectionDomain().getClassLoader().getResource("img1.png");
        URL imgUrl2=getClass().getProtectionDomain().getClassLoader().getResource("img2.jpg");

        String str= imgUrl.toString();
        String str2= imgUrl2.toString();
        System.out.println(str);

        Image image = new Image(str);
        Image image2 = new Image(str2);



        paneWithBtm.setPrefWidth(34*2 + lengthBtm*countBtmInWidth);
        paneWithBtm.setPrefHeight(25*2 + lengthBtm* countBtmInHeight);
        paneWithBtm.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fields=new ImageView[countBtmInWidth][countBtmInHeight];

        for(int i=0; i<countBtmInWidth;i++){
            for(int j=0;j<countBtmInHeight;j++){
                fields[i][j]= new ImageView(image);
                fields[i][j].setLayoutX(34 + 30 * i);
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
    public void changed(ObservableValue<? extends Number> observable, //
                        Number oldValue, Number newValue){

    }

    @FXML
    public void initialize(){
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
                labelLength.setText("" + Math.round(newValue.doubleValue()));
                countBtmInHeight=(int)Math.round(newValue.doubleValue());
                paneWithBtm.getChildren().clear();
                createFields();
            }
        });

        sliderWidth.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {
                labelWidth.setText("" + Math.round(newValue.doubleValue()));

                countBtmInWidth = (int)Math.round(newValue.doubleValue());
                paneWithBtm.getChildren().clear();
                createFields();
            }
        });

        sliderCountItem.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, //
                                Number oldValue, Number newValue) {
                lableCount.setText("" + Math.round(newValue.doubleValue()));
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
