/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vasouv.javaee7thesis.courses.dynamicfieldset;

import javax.faces.component.html.HtmlOutputText;
import org.primefaces.component.button.Button;
import org.primefaces.component.column.Column;
import org.primefaces.component.datalist.DataList;
import org.primefaces.component.fieldset.Fieldset;
import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;

/**
 *
 * @author vasouv
 */
public class DBFieldset {
    
    Fieldset fs = new Fieldset();
    GraphicImage gi = new GraphicImage();
    HtmlOutputText desc = new HtmlOutputText();
    HtmlOutputText price = new HtmlOutputText();
    DataList dl = new DataList();
    Button buy = new Button();
    
    PanelGrid priceButton = new PanelGrid();
    
    Column right = new Column();
    Column picture = new Column();
    Column desciption = new Column();
    
    Row topRow = new Row();
    Row bottomRow = new Row();
    
    Column list = new Column();
    
    PanelGrid whole = new PanelGrid();
    
    public DBFieldset(){}
    
    public Fieldset createStuff(String imagePath, String descr, String pr, String lecs, String legend) {
        gi.setName(imagePath);
        desc.setValue(descr);
        price.setValue(pr);
        dl.setValue(lecs);
        buy.setValue("Buy");
        priceButton.getChildren().add(price);
        priceButton.getChildren().add(buy);
        right.getChildren().add(priceButton);
        picture.getChildren().add(gi);
        desciption.getChildren().add(desc);
        topRow.getChildren().add(picture);
        topRow.getChildren().add(desciption);
        topRow.getChildren().add(right);
        list.getChildren().add(dl);
        bottomRow.getChildren().add(list);
        whole.getChildren().add(topRow);
        whole.getChildren().add(bottomRow);
        fs.getChildren().add(whole);
        fs.setLegend(legend);
        return fs;
    }
}
