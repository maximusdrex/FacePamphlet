/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	double StatusLabelY;
	GLabel CurrentLabel = null;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		if(CurrentLabel != null) remove(CurrentLabel);
		CurrentLabel = new GLabel(msg);
		CurrentLabel.setFont(MESSAGE_FONT);
		add(CurrentLabel, (getWidth() / 2) - (CurrentLabel.getWidth() / 2), getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given Profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given Profile is displayed.  The Profile display includes the 
	 * name of the user from the Profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile Profile) {
		removeAll();
		if(Profile != null) {
		GLabel NameLabel = new GLabel(Profile.getName());
		addNameLabel(Profile, NameLabel);
		addImage(Profile, NameLabel);
		showStatus(Profile, StatusLabelY);
		showFriends(Profile);
		}
	}
	
	private void addNameLabel(FacePamphletProfile Profile, GLabel NameLabel) {
		NameLabel.setFont(PROFILE_NAME_FONT);
		NameLabel.setColor(Color.BLUE);
		add(NameLabel, LEFT_MARGIN, TOP_MARGIN + NameLabel.getHeight());
		StatusLabelY = (TOP_MARGIN + NameLabel.getHeight()) + IMAGE_HEIGHT + IMAGE_MARGIN + STATUS_MARGIN;
	}
	
	private void addImage(FacePamphletProfile Profile, GLabel NameLabel) {
		if(Profile.getImage() == null) {
			// REVIEWNOTE: You are creating the picture twice.
			NullPicture Pic = new NullPicture(200, 200);
			add(new NullPicture(200, 200), LEFT_MARGIN, TOP_MARGIN + NameLabel.getHeight() + IMAGE_MARGIN);
		}	else {
			// REVIEWNOTE: You did it again ;)
			GImage Pic = Profile.getImage();
			// REVIEWNOTE: Why can't this add call be common with the one above?
			add(Profile.getImage(), LEFT_MARGIN, TOP_MARGIN + NameLabel.getHeight() + IMAGE_MARGIN);
		}
	}
	
	private void showStatus(FacePamphletProfile Profile, double StatusY) {
		GLabel Status;
		
		// REVIEWNOTE: Duplicate code
		if(Profile.getStatus() == null) {
			Status = new GLabel("No current status");
			Status.setFont(PROFILE_STATUS_FONT);
			add(Status, LEFT_MARGIN, StatusY);
		}	else {
			Status = new GLabel(Profile.getName() + " is " + Profile.getStatus());
			Status.setFont(PROFILE_STATUS_FONT);
			add(Status, LEFT_MARGIN, StatusY);
		}
	}
	
	private void showFriends(FacePamphletProfile Profile) {
		GLabel FriendLabel = new GLabel("Friends:");
		FriendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(FriendLabel, LEFT_MARGIN + IMAGE_WIDTH + 100, TOP_MARGIN);
		
		// REVEIWNOTE: This is a bad variable name, it looks just like the iterator
		int Friend = 1;
		Iterator<String> Friends = Profile.getFriends();
		while(Friends.hasNext()) {
			add(new GLabel(Friends.next()), LEFT_MARGIN + IMAGE_WIDTH + 100, TOP_MARGIN + (FriendLabel.getHeight() * Friend));
			Friend++;
		}
	}

	
}