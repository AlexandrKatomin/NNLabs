package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Alex on 21.09.2017.
 */
public class lab1Controller
{
    @FXML Pane paneWithBtm;
    @FXML Button btmCreate;
    @FXML Button btmRemove;
    private Button fields[][] = null;


    int countBtmInHeight=3;
    int countBtmInWidth=3;
    int lengthBtm=25;

    @FXML
    void onRemovePane(){
        paneWithBtm.getChildren().remove(0,countBtmInHeight*countBtmInWidth - 1);
    }
    @FXML
    void onCreatePane(){
      createFields();

        //paneWithBtm.getChildren().add(new Button());
    }


    String createIdForBtm (int i,int j){
        return "field[" + i + ","  + j + "]";
    }
    void createFields(){
        paneWithBtm.setPrefWidth(34*2 + lengthBtm*countBtmInWidth);
        paneWithBtm.setPrefHeight(25*2 + lengthBtm* countBtmInHeight);
        fields=new Button[countBtmInHeight][countBtmInWidth];

        for(int i=0; i<countBtmInWidth;i++){
            for(int j=0;j<countBtmInHeight;j++){

                fields[i][j]= new Button();
                fields[i][j].setLayoutX(34 + lengthBtm * i);
                fields[i][j].setLayoutY(25 + lengthBtm*j);
                fields[i][j].setPrefHeight(lengthBtm);
                fields[i][j].setPrefWidth(lengthBtm-1);
                fields[i][j].setId(createIdForBtm(i,j));

              //  fields[i][j].setOnAction(this::action);
                paneWithBtm.getChildren().add(fields[i][j]);
            }
        }
    }

    @FXML
    public void initialize(){

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
