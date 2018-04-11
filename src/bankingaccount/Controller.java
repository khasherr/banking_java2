/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingaccount;

import javafx.scene.layout.Pane;

/**
 *
 * @author leedy
 */
public abstract class Controller {
    private Controller parent;
    private MainViewController manage;
    
    public MainViewController getManagingController() {
        return manage;
    }
    
    public void setManagingController(MainViewController manage){
        this.manage = manage;
    }
    public Controller getParentController(){
        return parent;
    }
    
    public void setParentController(Controller parent){
        this.parent = parent;
    }
    
    public abstract Pane getView();
}
