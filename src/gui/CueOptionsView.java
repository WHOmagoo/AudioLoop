package gui;

import audio.groupings.CueOptions;
import audio.groupings.Music;
import audio.operators.Operand;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Map;

public class CueOptionsView {
    VBox items;
    CueOptions options;

    public CueOptionsView(CueOptions options){
        this.options = options;
    }

    private void initialize(){
        items = new VBox();

        Map<Music, Operand> musicSet = options.getOptions();


        for (Map.Entry<Music, Operand> music : musicSet.entrySet()) {
            TextField text = new TextField();

            text.setText(music.getKey().getName());
            items.getChildren().add(text);

        }
    }
}
