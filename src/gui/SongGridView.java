package gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SongGridView {
    private GridPane gridPane;
    private int measures;

    public SongGridView(int measures){
        gridPane = new GridPane();
        for(int i = 0; i < measures; i++){
            gridPane.addColumn(i, new Text("Column " + i));
        }
    }

     Node getElement(){
        return gridPane;
    }
}
