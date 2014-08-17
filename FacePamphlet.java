/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	private FacePamphletProfile ActiveProfile = null;
	private FacePamphletDatabase Database = new FacePamphletDatabase();
	private FacePamphletCanvas Canvas = new FacePamphletCanvas();
	/*
	 * JTextFields
	 */
	private JTextField NameField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField StatusField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField PicField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField FriendsField = new JTextField(TEXT_FIELD_SIZE);
	
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		addActionComponents();
    }
    
	private void addActionComponents() {
		add(Canvas, CENTER);
		add(new JLabel("Name"), NORTH);
		add(NameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		StatusField.setActionCommand("Update Status");
		add(StatusField, WEST);
		add(new JButton("Update Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		PicField.setActionCommand("Change Pic");
		add(PicField, WEST);
		add(new JButton("Change Pic"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		FriendsField.setActionCommand("Add Friend");
		add(FriendsField, WEST);
		add(new JButton("Add Friend"), WEST);
		addActionListeners();
	}
	
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "Add"){
			String Name = NameField.getText();
			Database.addProfile(new FacePamphletProfile(Name));
			ActiveProfile = Database.getProfile(Name);
			Canvas.displayProfile(ActiveProfile);
			Canvas.showMessage("Added " + Name);
		}	else if(cmd == "Delete") {
			String Name = NameField.getText();
			Database.deleteProfile(Name);
		}	else if(cmd == "Lookup") {
			String Name = NameField.getText();
			ActiveProfile = Database.getProfile(Name);
			Canvas.displayProfile(ActiveProfile);
		}	else if(cmd == "Update Status") {
			String Status = StatusField.getText();
			ActiveProfile.setStatus(Status);
			Canvas.displayProfile(ActiveProfile);
		}	else if(cmd == "Change Pic") {
			GImage Image  = new GImage(PicField.getText());
			Image.setSize(200, 200);
			ActiveProfile.setImage(Image);
			Canvas.displayProfile(ActiveProfile);
		}	else if(cmd == "Add Friend") {
			ActiveProfile.addFriend(FriendsField.getText());
			Canvas.displayProfile(ActiveProfile);
		}
	}

}
